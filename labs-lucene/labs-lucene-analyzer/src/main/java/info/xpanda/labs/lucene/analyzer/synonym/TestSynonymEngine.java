package info.xpanda.labs.lucene.analyzer.synonym;

import java.util.HashMap;

public class TestSynonymEngine implements SynonymEngine {
    private static HashMap<String, String[]> map = new HashMap<String, String[]>();

    static{
        map.put("quick", new String[]{"fast", "speedy"});
        map.put("jumps", new String[]{"leaps", "hops"});
    }

    public String[] getSynonyms(String s) {
        return map.get(s);
    }
}
