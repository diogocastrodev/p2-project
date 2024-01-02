package cache;

import abstracts.AbsDevice;
import abstracts.MapCache;
import classes.addresses.Mac;

import java.util.HashMap;
import java.util.Map;

public class DevicesCache extends MapCache<Mac, AbsDevice> {
    private static final Map<Mac, AbsDevice> cache = new HashMap<>();

    public DevicesCache() {
        super(cache);
    }
}
