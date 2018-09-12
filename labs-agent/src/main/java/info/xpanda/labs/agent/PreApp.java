package info.xpanda.labs.agent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class PreApp {
    public static void premain(String agentOps, Instrumentation inst) {
        System.err.println("装载成功 方法 premain 参数：" + agentOps);
        inst.addTransformer(new ClassFileTransformer() {
            public byte[] transform(ClassLoader loader, String className, Class classBeingRedefined,
                                    ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
                System.out.println("premain:" + className);
                return classfileBuffer;
            }
        });
    }
}
