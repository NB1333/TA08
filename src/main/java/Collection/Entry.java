package Collection;

import java.util.Objects;

public class Entry<K extends Comparable<K>, V> implements Comparable<Entry<K, V>> {
    private final K key;
    private V value;

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return this.key;
    }

    public V getValue() {
        return this.value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public boolean hasSameKey(K key) {
        return Objects.equals(this.key, key);
    }

    public int compareTo(Entry<K, V> o) {
        return this.getKey().compareTo(o.getKey());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Entry<?, ?> entry = (Entry<?, ?>) o;

        return Objects.equals(key, entry.key);
    }

}
