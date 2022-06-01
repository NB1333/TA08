package Container.LinkedList;

import Container.IContainer;

import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkedList<T> implements IContainer<T> {
    private Node<T> first;
    private Node<T> last;
    private int size;

    @Override
    public void add(T element) {
        Node<T> newNode = new Node<>(element);

        if (size == 0) {
            first = last = newNode;
        } else {
            addAsTail(newNode);
        }

        size++;
    }

    private Node<T> findNodeByIndex(int index) {
        Objects.checkIndex(index, size);

        if (index == size - 1) {
            return last;
        } else {
            return nodeAt(index);
        }
    }

    public void remove(int index) {
        if (index == 0) {
            removeHead();
        } else if (index == size - 1) {
            removeTail();
        } else {
            Node<T> previousNode = findNodeByIndex(index - 1);
            previousNode.next = previousNode.next.next;
        }

        size--;
    }

    @Override
    public boolean contains(T element) {
        Node<T> currentNode = first;

        while (currentNode != null) {
            if (currentNode.value.equals(element)) {
                return true;
            }
            currentNode = currentNode.next;
        }

        return false;
    }

    @Override
    public void remove(T element) {
        remove(getByValue(element));
    }

    @Override
    public T getFirst() {
        if (first != null) {
            return first.value;
        }

        return null;
    }

    @Override
    public T getNext(T element) {
        Node<T> node = nodeAt(getByValue(element));

        return node.next != null ? node.next.value : null;
    }

    public int getByValue(T element) {
        try {
            checkElementsExist();

            Node<T> currentNode = first;
            int index = 0;

            while (currentNode != null) {
                if (currentNode.value.equals(element)) {
                    return index;
                }
                currentNode = currentNode.next;
                index++;
            }
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }

        return 0;
    }

    private void addAsTail(Node<T> newNode) {
        last.next = newNode;
        newNode.previous = last;
        last = newNode;
    }

    private void removeTail() {
        last = last.previous;
        if (last == null) {
            first = null;
        } else {
            last.next = null;
        }
    }

    private void removeHead() {
        first = first.next;
        if (first == null) {
            last = null;
        } else {
            first.previous = null;
        }
    }

    private void checkElementsExist() {
        if (first == null) {
            throw new NoSuchElementException();
        }
    }

    private Node<T> nodeAt(int index) {
        Node<T> currentNode = first;

        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Node<T> current = first;

        while (current != null) {
            result.append(current.value);
            result.append(", ");
            current = current.next;
        }

        return ("The doubly linked list: " + result.substring(0, result.length() - 2));
    }
}
