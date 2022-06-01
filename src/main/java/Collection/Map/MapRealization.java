package Collection.Map;

import Container.IContainer;
import Container.LinkedList;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class MapRealization<K extends Comparable<K>, V> implements IMap<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;
    private final double loadFactor;
    private int capacity;
    private int size;
    private IContainer<Entry<K, V>>[] elements;

    public MapRealization(Object obj) {
        this(obj, DEFAULT_LOAD_FACTOR, DEFAULT_CAPACITY);
    }

    public MapRealization(Object obj, int loadFactor) {
        this(obj, DEFAULT_CAPACITY, loadFactor);
    }

    public MapRealization(Object obj, double loadFactor, int capacity) {
        this.capacity = capacity;
        this.loadFactor = loadFactor;

        try {
            if (obj instanceof IContainer) {
                this.elements = new LinkedList[capacity];
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Wrong container type");
        }
    }

    @Override
    public void put(K key, V value) {
        extension();
        int index = getBucketByHash(key);

        if (elements[index] == null) {
            elements[index] = new LinkedList<>();
        }

        if (this.contains(key)) {
            Entry<K, V> current = elements[index].getFirst();

            while (current != null) {
                if (current.hasSameKey(key)) {
                    current.setValue(value);
                    break;
                }
                current = elements[index].getNext(current);
            }
        } else {
            elements[index].add(new Entry<>(key, value));
        }
        size++;
    }

    @Override
    public boolean contains(K key) {
        int index = getBucketByHash(key);

        if (elements[index] == null) {
            return false;
        }
        return elements[index].contains(new Entry<>(key, null));
    }

    @Override
    public void remove(K key) {
        int index = getBucketByHash(key);
        removeByKey(key, elements[index]);

        size--;
    }

    private void removeByKey(K key, @NotNull IContainer<Entry<K, V>> element1) {
        Entry<K, V> current = element1.getFirst();

        while (current != null) {
            if (current.hasSameKey(key)) {
                element1.remove(current);
                break;
            }
            current = element1.getNext(current);
        }
    }

    private int getBucketByHash(K key) {
        return key == null ? 0 : Math.abs(key.hashCode()) % capacity;
    }

    private void extension() {
        if (Double.compare(size / loadFactor, capacity) >= 0) {
            elements = Arrays.copyOf(elements, capacity <<= 1);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int k = 0; k < capacity; k++) {
            if (elements[k] != null) {
                sb.append("[").append(k).append("] -> {");
                Entry<K, V> current = elements[k].getFirst();

                while (current != null) {
                    sb.append(current.getKey()).append(" = ").append(current.getValue());
                    current = elements[k].getNext(current);
                }

                sb.append("}\n");
            }
        }

        return sb.toString();
    }

}
