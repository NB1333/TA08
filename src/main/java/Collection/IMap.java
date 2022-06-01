package Collection;

public interface IMap<K, V> {
    void put(K key, V value);

    boolean contains(K key);

    void remove(K key);
}
