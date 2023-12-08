package cache;

import java.util.HashMap;
import java.util.Map;

/**
 * Cache class to store data and be able to access it from anywhere.
 */
public class Cache {

    /**
     * HashMap to store the cache.
     */
    private static final Map<String, Object> cache = new HashMap<>();

    /**
     * Put a value in the cache.
     * @param key - The key to store the value.
     * @param value - The value to store.
     */
    public static void put(String key, Object value) {
        cache.put(key, value);
    }

    /**
     * Get a value from the cache.
     * @param key - The key to get the value.
     * @return - The value.
     */
    public static Object get(String key) {
        return cache.get(key);
    }

    /**
     * Remove a value from the cache.
     * @param key - The key to remove the value.
     */
    public static void remove(String key) {
        cache.remove(key);
    }

    /**
     * Clear the cache.
     */
    public static void clear() {
        cache.clear();
    }

    /**
     * Check if the cache contains a key.
     * @param key - The key to check.
     * @return - True if the cache contains the key, false otherwise.
     */
    public static boolean containsKey(String key) {
        return cache.containsKey(key);
    }

    /**
     * Check if the cache contains a value.
     * @param value - The value to check.
     * @return - True if the cache contains the value, false otherwise.
     */
    public static boolean containsValue(Object value) {
        return cache.containsValue(value);
    }

    /**
     * Get the size of the cache.
     * @return
     */
    public static int size() {
        return cache.size();
    }

    /**
     * Check if the cache is empty.
     * @return - True if the cache is empty, false otherwise.
     */
    public static boolean isEmpty() {
        return cache.isEmpty();
    }

    /**
     * Get the cache.
     * @return - The cache.
     */
    public static Map<String, Object> getCache() {
        return cache;
    }
}
