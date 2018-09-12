package info.xpanda.labs.license.web;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import java.io.IOException;

//@SpringBootApplication(scanBasePackages = {"info.xpanda.labs.license"})
public class LicenseWebApplication {
    public static void main(String[] args){
        // args[0]传入的是某个jvm进程的pid
        try {
            String pid = args[0];
            VirtualMachine vm = VirtualMachine.attach(pid);
            vm.loadAgent("D:\\openjdk\\build\\windows-x86_64-normal-server-release\\images\\j2sdk-image\\labs-agentmain-1.0-SNAPSHOT.jar", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
//        SpringApplication.run(LicenseWebApplication.class, args);
    }
}
