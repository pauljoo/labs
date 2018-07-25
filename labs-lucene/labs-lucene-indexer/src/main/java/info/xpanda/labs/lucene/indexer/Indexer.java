package info.xpanda.labs.lucene.indexer;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;

public class Indexer {
    public static void main(String[] args) throws Exception{
        //分析器(analyzer)，将将域文本转化为索引表示单元（项）
        //term = 域名 + 词汇单元(token)
        long start = System.currentTimeMillis();
        //提取文档
        //TODO Read From Txt File
        Directory indexDir = FSDirectory.open(new File("D://test//lucene//index"));
        //分析器，用于提取词汇单元
        IndexWriter writer = new IndexWriter(indexDir,
                new StandardAnalyzer(Version.LUCENE_30),
                true,
                IndexWriter.MaxFieldLength.UNLIMITED);
        Document doc = new Document();
        // norms ??
        //Field.Index.ANALYZED 分解成词汇单元并索引
        //Field.NOT_ANALYZED 整体索引，不分解
        Field field = new Field("name","hello world", Field.Store.YES, Field.Index.ANALYZED);
        field.setOmitNorms(true);//在不加权的时候可以关闭加权基准，防止占用大量RAM
        field.setBoost(1.5f);//域加权
        doc.add(field);
        doc.setBoost(1.5f);//文档加权
        //分析文档Analyzer(Filter1, Filter2...)
        //写入索引
        /*
         * 索引文档结构Segments_N
         * segments_N(指向其他文件)
         * _X.<ext>
         *     X代表段名称
         *     ext标识该文件对应的索引的某个部分(项向量，存储的域，倒排索引等等)
         */
        writer.addDocument(doc);

        //标记删除，不释放磁盘空间
        //调用optimize或者expungeDeletes删除
        writer.deleteDocuments(new Term("id", "1"));

        //更新文档，Lucene无法更新部分域，只能先删除文档，再新建文档
        writer.updateDocument(new Term("id", "1"), doc);
        System.out.println("Indexed File Number: " +  writer.numDocs() + ", Take Time: " + (System.currentTimeMillis() -start));

        //LUCENE拥有ACID特性，不会因为程序崩溃导致索引毁坏
        //必要情况下需要禁止IO设备的写缓存
        writer.commit();

        //创建近实时搜索Reader
        IndexReader reader = writer.getReader();
        reader.close();
        //索引优化，多个段合并成一个段，加快搜索速度
        //优化期间会消耗大量的CPU和IO，并占用磁盘空间用于保存新的段，直到合并完成删除旧段，大约为3倍的磁盘空间
        writer.optimize();
        writer.close();
    }
}
