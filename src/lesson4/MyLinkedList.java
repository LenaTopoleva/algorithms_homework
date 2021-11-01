package lesson4;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public final class MyLinkedList<T> implements Iterable<T> {
    private Node first;
    private Node last;
    private int size;

    private class Node {
        Node prev;
        T value;
        Node next;

        public Node(T value, Node next) {
            this.value = value;
            this.next = next;
        }

        public Node(Node prev, T value, Node next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }
    }

    public void insertFirst(T item) {
        Node newNode = new Node(item, first);
        if (isEmpty()) {
            last = newNode;
        } else {
            first.setPrev(newNode);
        }
        first = newNode;
        size++;
    }

    public void insertLast(T item) {
        Node newNode = new Node(last, item, null);
        if (isEmpty()) {
            first = newNode;
        } else {
            last.setNext(newNode);
        }
        last = newNode;
        size++;
    }

    public T deleteFirst() {
        T temp = getFirst();
        first = first.getNext();
        if (isEmpty()) {
            last = null;
        } else {
            first.setPrev(null);
        }
        size--;
        return temp;
    }

    public T deleteLast() {
        T temp = getLast();
        if (last.getPrev() == null) {
            first = null;
        } else {
            last.getPrev().setNext(null);
        }
        last = last.getPrev();
        size--;
        return temp;
    }

    public T getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("empty");
        }
        return first.getValue();
    }

    public T getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("empty");
        }
        return last.getValue();
    }

    public int indexOf(T item) {
        Node current = first;
        int insex = 0;
        while (current != null) {
            if (item.equals(current.getValue())) {
                return insex;
            }
            current = current.getNext();
            insex++;
        }
        return -1;
    }

    public boolean contains(T item) {
        return indexOf(item) > -1;
    }

    public void insert(int index, T item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index " + index);
        }
        if (index == 0) {
            insertFirst(item);
            return;
        }
        if (index == size) {
            insertLast(item);
            return;
        }
        Node current = first;
        for (int i = 0; i < index - 1; i++) {
            current = current.getNext();
        }
        Node newNode = new Node(current, item, current.getNext());
        current.setNext(newNode);
        current.getNext().setPrev(newNode);
        size++;
    }

    public boolean delete(T item) {
        if (isEmpty()) {
            return false;
        }
        if (item.equals(first.getValue())) {
            deleteFirst();
            return true;
        }

        Node current = first;
        while (current != null && !item.equals(current.getValue())) {
            current = current.getNext();
        }
        if (current == null) {
            return false;
        }

        if (current == last) {
            deleteLast();
            return true;
        }

        current.getNext().setPrev(current.getPrev());
        current.getPrev().setNext(current.getNext());
        size--;
        return true;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return first == null;
    }

    private T unlink(Node node){
        // assert x != null;
        final T element = node.value;
        final Node next = node.next;
        final Node prev = node.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            node.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            node.next = null;
        }

        node.value = null;
        size--;
        return element;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node current = first;
        for (int i = 0; i < size; i++) {
            sb.append(current.getValue()).append(", ");
            current = current.getNext();
        }
        if (size > 0) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iter();
    }

    private class Iter implements Iterator<T> {
        Node current = first;

        @Override
        public boolean hasNext() {
            return current.getNext() != null;
        }

        @Override
        public T next() {
            current = current.getNext();
            return current.getValue();
        }
    }

    public ListIterator<T> listIterator() {
        return new ListIter();
    }

    private class ListIter implements ListIterator<T> {
        Node lastReturned;
        Node next;
        Integer index = 0;

        ListIter(){
            next = first;
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();

            lastReturned = next;
            next = next.getNext();
            index++;
            return lastReturned.getValue();
        }

        @Override
        public boolean hasPrevious() {
            return index > 0;
        }

        @Override
        public T previous() {
            if (!hasPrevious())
                throw new NoSuchElementException();

            lastReturned = next = (next == null)? last : next.getPrev();
            index--;
            return lastReturned.getValue();
        }

        @Override
        public int nextIndex() { return index; }

        @Override
        public int previousIndex() { return index - 1; }

        @Override
        public void remove() {
            if (lastReturned == null)
                throw new IllegalStateException();

            Node lastReturnedNext = lastReturned.next;
            unlink(lastReturned);

            if(next == lastReturned)
                next = lastReturnedNext;
            else
                index--;
            lastReturned = null;
        }

        @Override
        public void set(T t) {
            if (lastReturned == null)
                throw new IllegalStateException();

            lastReturned.value = t;
        }

        @Override
        public void add(T t) {
            insert(index, t);
            lastReturned = null;
            index++;
        }
    }
}

class TestMyLinkedList {
    public static void main(String[] args) {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        myLinkedList.insertFirst(1);
        myLinkedList.insertFirst(2);
        myLinkedList.insertFirst(3);
        System.out.println(myLinkedList);

        ListIterator<Integer> listIterator = myLinkedList.listIterator();

        Integer first = listIterator.next();
        System.out.println("First:" + first);

        Integer second = listIterator.next();
        System.out.println("Second:" + second);

        listIterator.remove();
        System.out.println(myLinkedList);

        while(listIterator.hasPrevious()){
            System.out.print(listIterator.previous() + " ");
        }
        System.out.println();

        while(listIterator.hasNext()){
            System.out.print(listIterator.next() + " ");
        }
        System.out.println();

        listIterator.set(4);
        System.out.println(myLinkedList);

        listIterator.add(5);
        System.out.println(myLinkedList);
    }
}
