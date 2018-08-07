package info.xpanda.labs.monitor.core;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class TestAll {
    public static void main(String[] args) {
        TestRunner.run(suite());
    }

    public static Test suite(){
        TestSuite suite = new TestSuite("");
        return suite;
    }
}
