package cache;

import abstracts.SetCache;
import classes.addresses.Mac;

import java.util.HashSet;
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
