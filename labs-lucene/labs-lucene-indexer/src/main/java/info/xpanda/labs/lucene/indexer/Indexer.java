package info.xpanda.labs.lucene.indexer;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;

public class Indexer {
    public static void main(String[] args) throws Exception{
        long start = System.currentTimeMillis();

        Directory indexDir = FSDirectory.open(new File("D://test//lucene//index"));
        IndexWriter writer = new IndexWriter(indexDir,
                new StandardAnalyzer(Version.LUCENE_30),
                true,
                IndexWriter.MaxFieldLength.UNLIMITED);
        Document doc = new Document();
        doc.add(new Field("name","hello world", Field.Store.YES, Field.Index.ANALYZED));
        writer.addDocument(doc);
        System.out.println("Indexed File Number: " +  writer.numDocs() + ", Take Time: " + (System.currentTimeMillis() -start));
        writer.close();
    }
}
