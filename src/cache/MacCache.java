package cache;

import abstracts.AbsDevice;
import abstracts.MapCache;
import abstracts.SetCache;
import classes.addresses.Mac;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MacCache extends SetCache<String> {

    private static final Set<String> cache = new HashSet<>();
    public MacCache() {
        super(cache);
    }

    /**
     * Put a value in the cache.
     * @param key - The key to store the value. (Convert Mac to String)
     */
    public void put(Mac key) {
        super.add(key.getMac());
    }
}
