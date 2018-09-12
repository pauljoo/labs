package info.xpanda.labs.agent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * java -javaagent:labs-agent.jar=mode=test Test
 */
public class App {
    public static void agentmain(String agentArgs, Instrumentation inst){
        System.err.println("装载成功 方法 agentmain 参数：" + agentArgs);
        inst.addTransformer(new ClassFileTransformer() {
            public byte[] transform(ClassLoader loader, String className, Class classBeingRedefined,
                                    ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
                System.out.println("agentmain:" + className);
                return classfileBuffer;
            }
        });
    }


//        System.out.println("=====start premain");
//        AgentBuilder.Transformer transformer = new AgentBuilder.Transformer() {
//            public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule) {
//                return builder
//                        .method(ElementMatchers.<MethodDescription>any())//拦截任意方法
//                        .intercept(MethodDelegation.to(TimeInterceptor.class));//委托
//            }
//        };
//
//        AgentBuilder.Listener listener = new AgentBuilder.Listener() {
//            public void onDiscovery(String s, ClassLoader classLoader, JavaModule javaModule, boolean b) {
//
//            }
//
//            public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean b, DynamicType dynamicType) {
//
//            }
//
//            public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean b) {
//
//            }
//
//            public void onError(String s, ClassLoader classLoader, JavaModule javaModule, boolean b, Throwable throwable) {
//
//            }
//
//            public void onComplete(String s, ClassLoader classLoader, JavaModule javaModule, boolean b) {
//
//            }
//        };
//
//        new AgentBuilder
//                .Default()
//                .type(ElementMatchers.<TypeDescription>nameStartsWith("info.xpanda.labs.agent"))//指定需要拦截的类
//        .transform(transformer)
//                .with(listener)
//                .installOn(inst);
//        System.out.println("=====end premain");
}
