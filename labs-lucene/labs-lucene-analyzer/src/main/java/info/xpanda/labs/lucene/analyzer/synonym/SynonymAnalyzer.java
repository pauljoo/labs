package info.xpanda.labs.lucene.analyzer.synonym;

import org.apache.lucene.analysis.*;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.util.Version;

import java.io.Reader;

public class SynonymAnalyzer extends Analyzer{
    private SynonymEngine engine;

    public SynonymAnalyzer(SynonymEngine engine) {
        this.engine = engine;
    }

    public TokenStream tokenStream(String s, Reader reader) {
        TokenStream result = new SynonymFilter(
                new StopFilter(true,
                        new LowerCaseFilter(new StandardFilter(new StandardTokenizer(Version.LUCENE_30, reader))),
                        StopAnalyzer.ENGLISH_STOP_WORDS_SET
                ),
                engine);
        return result;
    }
}
