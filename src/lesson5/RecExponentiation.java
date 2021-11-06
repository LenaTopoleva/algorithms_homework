package lesson5;

// Написать программу по возведению числа в степень с помощью рекурсии.
public class RecExponentiation {
    public static long recExponentiate(Integer num, Integer exponent){
        long result;
        if(exponent == 1){
            result = num;
            return result;
        }
        return recExponentiate(num,exponent - 1) * num;
    }

    public static void main(String[] args) {
        System.out.println(recExponentiate(2, 10));
    }
}
