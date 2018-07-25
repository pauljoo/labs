package info.xpanda.labs.lucene.indexer;

import org.apache.lucene.analysis.*;

import java.io.IOException;
import java.io.Reader;

public class SimpleAnalyzer extends Analyzer {
    /***
     * 递归处理所有词汇单元
     * TokenStream 用于将文本逐字转换为词汇单元流
     *
     * @param s
     * @param reader
     * @return
     */
    @Override
    public TokenStream tokenStream(String s, Reader reader) {
        //构建词汇单元处理链路
        //使用小写转换单元处理器
        return new LowerCaseTokenizer(reader);
    }

    /**
     * 重复利用对应线程的同一个TokenStream
     * @param fieldName
     * @param reader
     * @return
     * @throws IOException
     */
    @Override
    public TokenStream reusableTokenStream(String fieldName, Reader reader) throws IOException {
        //获取线程的TokenStream
        Tokenizer tokenizer = (Tokenizer)getPreviousTokenStream();
        if(null == tokenizer){
            tokenizer = new LowerCaseTokenizer(reader);
            //设置线程的TokenStream
            setPreviousTokenStream(tokenizer);
        }else{
            //分配新的reader
            tokenizer.reset(reader);
        }
        return tokenizer;
    }
}
