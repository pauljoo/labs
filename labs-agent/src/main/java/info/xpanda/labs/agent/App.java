package info.xpanda.labs.agent;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

import java.lang.instrument.Instrumentation;

public class App {

    public static void premain(String agentOps, Instrumentation inst) {
        System.out.println("=====start premain");
        AgentBuilder.Transformer transformer = new AgentBuilder.Transformer() {
            public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule) {
                return builder
                        .method(ElementMatchers.<MethodDescription>any())//拦截任意方法
                        .intercept(MethodDelegation.to(TimeInterceptor.class));//委托
            }
        };

        AgentBuilder.Listener listener = new AgentBuilder.Listener() {
            public void onDiscovery(String s, ClassLoader classLoader, JavaModule javaModule, boolean b) {

            }

            public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean b, DynamicType dynamicType) {

            }

            public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean b) {

            }

            public void onError(String s, ClassLoader classLoader, JavaModule javaModule, boolean b, Throwable throwable) {

            }

            public void onComplete(String s, ClassLoader classLoader, JavaModule javaModule, boolean b) {

            }
        };

        new AgentBuilder
                .Default()
                .type(ElementMatchers.<TypeDescription>nameStartsWith("info.xpanda.labs.agent"))//指定需要拦截的类
        .transform(transformer)
                .with(listener)
                .installOn(inst);
        System.out.println("=====end premain");
    }
}
