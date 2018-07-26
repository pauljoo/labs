package info.xpanda.labs.lucene.searcher;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.TermFreqVector;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;

public class Searcher {
    public static void main(String[] args) throws Exception{
        long start = System.currentTimeMillis();

        Directory indexDir = FSDirectory.open(new File("D://test//lucene//index"));
        //提供底层文件索引读取操作
        IndexReader reader = IndexReader.open(indexDir);
        //重新打开Reader获取索引中变动情况
        IndexReader newReader = reader.reopen();

        //IndexSearch-->IndexReader
        IndexSearcher searcher = new IndexSearcher(newReader);

        //查询解析器，用于生成查询用的Query
        QueryParser parse = new QueryParser(Version.LUCENE_30, "name", new StandardAnalyzer(Version.LUCENE_30));
        Query query = parse.parse("QQ hello world");

        System.out.println(query.toString());

        //自定义名字过滤器
        Filter nameFilter = new QueryWrapperFilter(new TermQuery(new Term("name", "jake")));
        TopDocs hits = searcher.search(query, nameFilter, 10, Sort.RELEVANCE);
        for(ScoreDoc scoreDoc : hits.scoreDocs){
            //解释评分结果
            Explanation explanation = searcher.explain(query, scoreDoc.doc);

            TermFreqVector vector = reader.getTermFreqVector(scoreDoc.doc, "name");
            System.out.println(vector.getTerms());
            System.out.println(searcher.doc(scoreDoc.doc).get("name"));
        }
        searcher.close();
    }
}
