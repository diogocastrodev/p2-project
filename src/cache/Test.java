package cache;

import abstracts.Cache;

import java.util.HashMap;
import java.util.Map;

public class Test extends Cache<String, String> {

    private static final Map<String, String> cache = new HashMap<>();
    public Test() {
        super(cache);
    }
}
