package info.xpanda.labs.lucene.analyzer.synonym;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;

import java.io.IOException;
import java.util.Stack;

public class SynonymFilter extends TokenFilter {
    public static final String TOKEN_TYPE_SYNONYM = "SYNONYM";

    private Stack<String> synonymStack;
    private SynonymEngine engine;
    private State current;

    private TermAttribute termAttr;
    private PositionIncrementAttribute posIncrAttr;

    public SynonymFilter(TokenStream input, SynonymEngine engine) {
        super(input);
        this.engine = engine;
        synonymStack = new Stack<String>();

        this.termAttr = addAttribute(TermAttribute.class);
        this.posIncrAttr = addAttribute(PositionIncrementAttribute.class);
    }

    public boolean incrementToken() throws IOException {
        if(synonymStack.size() > 0){
            //弹出缓冲区的同义词
            String syn = synonymStack.pop();
            restoreState(current);
            termAttr.setTermBuffer(syn);
            posIncrAttr.setPositionIncrement(0);
            return true;
        }

        if(!input.incrementToken())
            return false;

        if(addAliasesToStack()){
            //有同义词，保存当前状态
            current = captureState();
        }

        return true;
    }

    private boolean addAliasesToStack(){
        String[] synonyms = engine.getSynonyms(termAttr.term());
        if(null == synonyms)
            return false;

        for(String synonym : synonyms){
            synonymStack.push(synonym);
        }
        return true;
    }
}
