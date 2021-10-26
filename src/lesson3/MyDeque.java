package lesson3;

public class MyDeque<T> {
    private T[] list;
    private int size;
    private final int DEFAULT_CAPACITY = 10;
    private int capacity;
    private int head;
    private int tail;

    public MyDeque(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
        this.capacity = capacity;
        list = (T[]) new Object[capacity];
    }

    public MyDeque() {
        capacity = DEFAULT_CAPACITY;
        list = (T[]) new Object[DEFAULT_CAPACITY];
    }

    // добавляет элемент в конец очереди
    public void insertLast(T item) {
        if (isFull()) {
            grow(1);
        }
        size++;
        tail = (size == 1)? 0 : nextIndex(tail);
        list[tail] = item;
    }

    // добавляет элемент в начало очереди
    public void insertFirst(T item) {
        if (isFull()) {
            grow(1);
        }
        size++;
        head = (size == 1)? 0 : previousIndex(head);
        list[head] = item;
    }

    // возвращает с удалением элемент из начала очереди.
    public T removeFirst() {
        T temp = peekFirst();
        list[head] = null;
        head = nextIndex(head);
        size--;
        return temp;
    }

    // возвращает с удалением последний элемент очереди.
    public T removeLast() {
        T temp = peekLast();
        list[tail] = null;
        tail = previousIndex(tail);
        size--;
        return temp;
    }

    public T peekFirst() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        return list[head];
    }

    public T peekLast() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        return list[tail];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == list.length;
    }


    private int nextIndex(int index) {
        return (index + 1) % capacity;
    }

    private int previousIndex(int index) {
        return (index - 1 >= 0) ? (index - 1) : capacity - 1;
    }

    private void grow(int needed){
        capacity += needed;
        T[] newList = (T[]) new Object[capacity];
        for (int i = head, j = 0; i < list.length; i++, j++) {
            newList[j] = list[i];
        }
        for (int i = 0, j = list.length - head; i <= tail && tail < head; i++, j ++) {
            newList[j] = list[i];
        }
        head = 0;
        tail = list.length - 1;
        list = newList;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        for (int i = head, j = 0; j < size; i = nextIndex(i), j++) {
            sb.append(list[i]).append(", ");
        }
        sb.setLength(sb.length() - 2);
        sb.append("]");
        return sb.toString();
    }

    public int getCapacity(){
        return capacity;
    }

}


class TestMyDeque {

    public static void main(String[] args) {
        MyDeque<Integer> myDeque = new MyDeque<>(5);
        System.out.println("Create deque with capacity " + myDeque.getCapacity() + ": " + myDeque.toString());

        myDeque.insertFirst(1);
        myDeque.insertFirst(2);
        myDeque.insertFirst(3);
        myDeque.insertFirst(4);
        myDeque.insertFirst(5);
        myDeque.insertFirst(6);
        System.out.println("insertFirst() 1, 2, 3, 4, 5, 6: " + myDeque.toString());

        myDeque.removeFirst();
        myDeque.removeFirst();
        System.out.println("removeFirst() x2: " + myDeque.toString());

        myDeque.insertLast(100);
        myDeque.insertLast(200);
        myDeque.insertLast(300);
        System.out.println("insertLast() 100, 200, 300: " + myDeque.toString());

        myDeque.removeFirst();
        myDeque.removeFirst();
        System.out.println("removeFirst() x2: " + myDeque.toString());

        myDeque.removeLast();
        System.out.println("removeLast() x1: " + myDeque.toString());

        myDeque.insertFirst(14);
        myDeque.insertFirst(144);
        myDeque.insertFirst(1444);
        System.out.println("insertFirst() 14, 144, 1444: " + myDeque.toString());

        myDeque.insertLast(7);
        myDeque.insertLast(0);
        System.out.println("insertLast() 7, 0: " + myDeque.toString());

        myDeque.removeLast();
        myDeque.removeLast();
        myDeque.removeLast();
        System.out.println("removeLast() x3 : " + myDeque.toString());
    }

}
