package info.xpanda.labs.lucene.analyzer.synonym;

import info.xpanda.labs.lucene.analyzer.AnalyzerUtils;
import junit.framework.TestCase;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.store.RAMDirectory;

public class SynonymAnalyzerTest extends TestCase{
    private IndexSearcher searcher;

    private static SynonymAnalyzer synonymAnalyzer = new SynonymAnalyzer(new TestSynonymEngine());

    @Override
    public void setUp() throws Exception {
        RAMDirectory directory = new RAMDirectory();

        IndexWriter writer = new IndexWriter(directory, synonymAnalyzer, IndexWriter.MaxFieldLength.UNLIMITED);

        Document doc = new Document();
        doc.add(new Field("content", "the quick brown fox jumps over the lazy dog",
                Field.Store.YES, Field.Index.ANALYZED));
        writer.addDocument(doc);
        writer.close();

        searcher = new IndexSearcher(directory);
    }

    @Override
    public void tearDown() throws Exception {
        searcher.close();
    }

    public void testDisplayToken(){
        AnalyzerUtils.displayTokens(new SynonymAnalyzer(new TestSynonymEngine()), "the quick brown fox jumps over the lazy dog");
    }
}
