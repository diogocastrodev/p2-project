package cache;

import abstracts.AbsDevice;
import abstracts.MapCache;
import classes.addresses.Mac;

import java.util.HashMap;
import java.util.Map;

public class DevicesCache extends MapCache<String, AbsDevice> {
    private static final Map<String, AbsDevice> cache = new HashMap<>();

    public DevicesCache() {
        super(cache);
    }

    /**
     * Get a device from the cache.
     * @param mac - The mac of the device.
     * @return - The device.
     */
    public static AbsDevice getDevice(String mac) {
        return cache.get(mac);
    }

    /**
     * Add a device to the cache.
     * @param device - The device to add.
     */
    public static void addDevice(AbsDevice device) {
        cache.put(device.getMac().toString(), device);
    }

    /**
     * Remove a device from the cache.
     * @param mac - The mac of the device to remove.
     */
    public static void removeDevice(String mac) {
        cache.remove(mac);
    }

}
