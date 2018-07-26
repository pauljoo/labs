package info.xpanda.labs.lucene.analyzer.metaphone;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.LetterTokenizer;
import org.apache.lucene.analysis.TokenStream;

import java.io.Reader;

public class MetaphoneReplacementAnalyzer extends Analyzer {
    public TokenStream tokenStream(String s, Reader reader) {
        return new MetaphoneReplacementFilter(new LetterTokenizer(reader));
    }
}
