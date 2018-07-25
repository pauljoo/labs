package info.xpanda.labs.lucene.indexer;

import org.apache.lucene.analysis.*;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;
import org.apache.lucene.util.Version;

import java.io.IOException;
import java.io.StringReader;

/**
 * Reader-->Tokenizer-->TokenFilter-->TokenFilter-->Tokens
 */
public class AnalyzerDemo {
    private static final String[] examples = {
            "hello world",
            "world hello"
    };

    private static final Analyzer[] analyzers = new Analyzer[]{
            new WhitespaceAnalyzer(),
            new SimpleAnalyzer(),
            new StopAnalyzer(Version.LUCENE_30),
            new StandardAnalyzer(Version.LUCENE_30)
    };

    public static void main(String[] args) {
        for(String text : examples){
            analyze(text);
        }
    }

    private static void analyze(String text){
        try {
            for(Analyzer analyzer : analyzers){
                String name = analyzer.getClass().getSimpleName();
                System.out.println(name + ":");
                //执行分析
                TokenStream stream = analyzer.tokenStream("content", new StringReader(text));

                //获取有用的属性
                TermAttribute term = stream.addAttribute(TermAttribute.class);
                PositionIncrementAttribute posIncr = stream.addAttribute(PositionIncrementAttribute.class);
                OffsetAttribute offset = stream.addAttribute(OffsetAttribute.class);
                TypeAttribute type = stream.addAttribute(TypeAttribute.class);

                int position = 0;
                //产生词汇单元流，递归处理所有词汇单元
                //一个词汇单元：单词本身，其他元数据（偏移量，词汇单元类型，位置增量，标识位，有效负载）
                while(stream.incrementToken()){
                    //输出词汇单元，修改属性的内部状态为下一个词汇单元

                    //计算并打印位置信息
                    int increment = posIncr.getPositionIncrement();
                    if(increment > 0){
                        position = position + increment;
                        System.out.println(position + ":");
                    }

                    System.out.println("[" + term.term() + ":" +
                    offset.startOffset() + "->" + offset.endOffset() + ":" + type.type() + "]");
                }
                System.out.println("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
