package abstracts;

import java.util.Set;

/**
 * Cache class to store data and be able to access it from anywhere.
 * @param <E> - The type of the cache.
 */
public abstract class SetCache <E> {

    /**
     * List to store the cache.
     */
    private Set<E> cache;

    /**
     * Constructor
     * @param cache List to store the cache.
     */
    public SetCache(Set<E> cache) {
        this.cache = cache;
    }

    /**
     * Add a value to the cache.
     * @param value - The value to add.
     */
    public void add(E value) {
        cache.add(value);
    }

    /**
     * Add a list of values to the cache.
     * @param values - The values to add.
     */
    public void addAll(Set<E> values) {
        cache.addAll(values);
    }

    /**
     * Remove a value from the cache.
     * @param value - The value to remove.
     */
    public void remove(E value) {
        cache.remove(value);
    }

    /**
     * Remove a list of values from the cache.
     * @param values - The values to remove.
     */
    public void removeAll(Set<E> values) {
        cache.removeAll(values);
    }

    /**
     * Clear the cache.
     */
    public void clear() {
        cache.clear();
    }

    /**
     * Check if the cache contains a value.
     * @param value - The value to check.
     * @return - True if the cache contains the value, false otherwise.
     */
    public boolean contains(E value) {
        return cache.contains(value);
    }

    /**
     * Check if the cache contains a list of values.
     * @param values - The values to check.
     * @return - True if the cache contains the values, false otherwise.
     */
    public boolean containsAll(Set<E> values) {
        return cache.containsAll(values);
    }

    /**
     * Get the size of the cache.
     * @return - The size of the cache.
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
    public Set<E> getCache() {
        return cache;
    }
}