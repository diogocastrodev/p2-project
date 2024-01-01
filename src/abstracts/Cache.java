package abstracts;

import java.util.HashMap;
import java.util.Map;

/**
 * Cache class to store data and be able to access it from anywhere.
 */
public abstract class Cache<E,V> {

    /**
     * HashMap to store the cache.
     */
    private Map<E, V> cache;

    public Cache(Map<E,V> cache) {
        this.cache = cache;
    }

    /**
     * Put a value in the cache.
     * @param key - The key to store the value.
     * @param value - The value to store.
     */
    public void put(E key, V value) {
        cache.put(key, value);
    }

    /**
     * Get a value from the cache.
     * @param key - The key to get the value.
     * @return - The value.
     */
    public V get(E key) {
        return cache.get(key);
    }

    /**
     * Remove a value from the cache.
     * @param key - The key to remove the value.
     */
    public void remove(E key) {
        cache.remove(key);
    }

    /**
     * Clear the cache.
     */
    public void clear() {
        cache.clear();
    }

    /**
     * Check if the cache contains a key.
     * @param key - The key to check.
     * @return - True if the cache contains the key, false otherwise.
     */
    public boolean containsKey(E key) {
        return cache.containsKey(key);
    }

    /**
     * Check if the cache contains a value.
     * @param value - The value to check.
     * @return - True if the cache contains the value, false otherwise.
     */
    public boolean containsValue(V value) {
        return cache.containsValue(value);
    }

    /**
     * Get the size of the cache.
     * @return
     */
    public int size() {
        return cache.size();
    }

    /**
     * Check if the cache is empty.
     * @return - True if the cache is empty, false otherwise.
     */
    public boolean isEmpty() {
        return cache.isEmpty();
    }

    /**
     * Get the cache.
     * @return - The cache.
     */
    public Map<E, V> getCache() {
        return cache;
    }
}
