package lesson4;

public class MyLinkedStack<T> {
    private final MyLinkedList<T> myLinkedList;

    public MyLinkedStack(){
        this.myLinkedList = new MyLinkedList<>();
    }

    public void push(T item) {
        myLinkedList.insertFirst(item);
    }

    public T pop() {
      return myLinkedList.deleteFirst();
    }

    public T peek() {
       return myLinkedList.getFirst();
    }

    public int size() {
        return myLinkedList.size();
    }

    public boolean isEmpty() {
        return myLinkedList.isEmpty();
    }

    @Override
    public String toString() {
        return myLinkedList.toString();
    }

}

class TestMyLinkedStack {
    public static void main(String[] args) {

        MyLinkedStack<Integer> myLinkedStack = new MyLinkedStack<>();

        myLinkedStack.push(1);
        myLinkedStack.push(2);
        myLinkedStack.push(3);
        System.out.println(myLinkedStack);

        myLinkedStack.pop();
        myLinkedStack.pop();
        myLinkedStack.push(4);
        System.out.println(myLinkedStack);

        System.out.println(myLinkedStack.peek());
    }
}
