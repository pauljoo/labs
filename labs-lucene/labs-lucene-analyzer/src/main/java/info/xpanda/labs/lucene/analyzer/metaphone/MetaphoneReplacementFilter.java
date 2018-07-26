package info.xpanda.labs.lucene.analyzer.metaphone;

import org.apache.commons.codec.language.Metaphone;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;

import java.io.IOException;

/**
 * 近音词
 */
public class MetaphoneReplacementFilter extends TokenFilter {
    public static final String METAPHONE = "metaphone";

    private Metaphone metaphoner = new Metaphone();
    private TermAttribute termAttr;
    private TypeAttribute typeAttr;

    public MetaphoneReplacementFilter(TokenStream input) {
        super(input);
        termAttr = addAttribute(TermAttribute.class);
        typeAttr = addAttribute(TypeAttribute.class);
    }

    public boolean incrementToken() throws IOException {
        if(!input.incrementToken()){
            return false;
        }

        //使用Metaphone编码
        String encoded = metaphoner.encode(termAttr.term());
        //使用编码后的文本
        termAttr.setTermBuffer(encoded);
        typeAttr.setType(METAPHONE);
        return true;
    }
}
