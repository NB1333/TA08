package Container.LinkedList;

public class Node<T> {
    protected final T value;
    protected Node<T> next;
    protected Node<T> previous;

    Node(T value) {
        this.value = value;
    }
}
