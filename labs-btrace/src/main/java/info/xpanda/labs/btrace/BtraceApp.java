package info.xpanda.labs.btrace;

import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.Kind;
import com.sun.btrace.annotations.Location;
import com.sun.btrace.annotations.OnMethod;

/**
 * windows下编译编码问题
 * chcp 查询当前编码，936为GBK
 * chcp 65001，设置为UTF-8
 *
 * linux下查询编译问题
 * echo $LANG
 * export LANG="en_US.UTF-8"
 *
 * 1.运行时
 * bin\btrace pid BtraceApp.java
 *
 * 2.启动时
 * 预编译 btracec BtraceApp.java
 * 启动 java -javaagent:btrace-agent.jar=script=Btrace.class -jar xxx.jar
 * 日志输出 BtraceApp.class-.default.1533025997938.btrace
 */
@BTrace
public class BtraceApp {
    @OnMethod(
            clazz="info.xpanda.labs.controller.SampleController",
            method = "testHome",
            location = @Location(Kind.ENTRY)
    )
    public static void onParameter(String prefix){
        BTraceUtils.println(prefix);
    }
}
