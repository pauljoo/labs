package info.xpanda.speedcode.engine;

public interface Generator {
	void load(String xmlPath) throws Exception;
	
	void generator() throws Exception;
}
