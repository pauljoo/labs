package info.xpanda.speedcode.configuration.resolve;

import info.xpanda.speedcode.engine.ApplicationContext;

public interface ConfigurationResolve {
	ApplicationContext resolve() throws Exception;
}
