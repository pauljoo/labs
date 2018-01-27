package info.xpanda.speedcode;

import org.junit.Test;

import info.xpanda.speedcode.engine.Generator;
import info.xpanda.speedcode.engine.GeneratorImpl;

public class GeneratorFactoryTest {
	@Test
	public void testGetInstance(){
		try {
			Generator generator = new GeneratorImpl();
			generator.load("D:/work/speedcode/configuration.xml");
			generator.generator();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
