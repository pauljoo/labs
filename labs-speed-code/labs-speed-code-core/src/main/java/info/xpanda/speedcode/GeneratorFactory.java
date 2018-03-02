package info.xpanda.speedcode;

import info.xpanda.speedcode.engine.Generator;
import info.xpanda.speedcode.engine.GeneratorImpl;

public class GeneratorFactory {
	public Generator getInstance(){
		return new GeneratorImpl();
	}
}
