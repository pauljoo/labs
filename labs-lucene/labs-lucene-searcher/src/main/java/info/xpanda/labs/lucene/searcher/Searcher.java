package info.xpanda.labs.lucene.searcher;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;

public class Searcher {
    public static void main(String[] args) throws Exception{
        long start = System.currentTimeMillis();

        Directory indexDir = FSDirectory.open(new File("D://test//lucene//index"));
        IndexSearcher searcher = new IndexSearcher(indexDir);

        //要搜索的字段
        QueryParser parse = new QueryParser(Version.LUCENE_30, "name", new StandardAnalyzer(Version.LUCENE_30));
        Query query = parse.parse("hello world");

        TopDocs hits = searcher.search(query, 10);
        for(ScoreDoc scoreDoc : hits.scoreDocs){
            System.out.println(searcher.doc(scoreDoc.doc).get("name"));
        }
        searcher.close();
    }
}
