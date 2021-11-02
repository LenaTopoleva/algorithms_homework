package lesson3;

import java.util.Stack;

// Создать программу, которая переворачивает вводимые строки (читает справа налево), используя стек.
public class LineReverser {

    public static String reverse(String str){
        Stack<Character> stack = new Stack<>();
        StringBuilder result = new StringBuilder();
        str.chars()
            .mapToObj(i -> (char)i)
            .forEach(stack::push);
        for (int i = 0; i < str.length(); i++) {
            result.append(stack.pop());
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(reverse("Mama myla ramu"));
    }
}
