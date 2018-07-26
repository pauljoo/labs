package info.xpanda.labs.lucene.analyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;

import java.io.IOException;
import java.io.StringReader;

public class AnalyzerUtils {
    public static void displayTokens(Analyzer analyzer, String text){
        try {
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
                        System.out.println("position" + ":" + position);
                    }

                    System.out.println("[" + term.term() + ":" +
                            offset.startOffset() + "->" + offset.endOffset() + ":" + type.type() + "]");
                }
                System.out.println("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
