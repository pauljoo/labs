package info.xpanda.labs.lucene.analyzer.synonym;

import org.apache.lucene.analysis.*;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.util.Version;

import java.io.Reader;

/**
 * 索引期间扩展同义词，可以通过占用磁盘空间来加快搜索速度
 * 同时，已经被编入索引，不能再搜索期间进行改变
 *
 * 搜索期间扩展同义词，可以随时改变同义词
 */
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
