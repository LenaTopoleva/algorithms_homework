package lesson1;

/*
1. Is valid identifier?
    Given a string, determine if it's a valid identifier.

    Here is the syntax for valid identifiers:
    Each identifier must have at least one character.
    The first character must be picked from: alpha, underscore, or dollar sign. The first character cannot be a digit.
    The rest of the characters (besides the first) can be from: alpha, digit, underscore, or dollar sign.
    In other words, it can be any valid identifier character.
    Examples of valid identifiers:
        i
        wo_rd
        b2h
    Examples of invalid identifiers:
        1i
        wo rd
        !b2h

2. Sum of Digits / Digital Root
    Digital root is the recursive sum of all the digits in a number.
    Given n, take the sum of the digits of n.
    If that value has more than one digit, continue reducing in this way until a single-digit number is produced.
    The input will be a non-negative integer.

3. Vasya - Clerk
    The new "Avengers" movie has just been released! There are a lot of people at the cinema box office standing in a
    huge line. Each of them has a single 100, 50 or 25 dollar bill. An "Avengers" ticket costs 25 dollars.
    Vasya is currently working as a clerk. He wants to sell a ticket to every single person in this line.
    Can Vasya sell a ticket to every person and give change if he initially has no money and sells the tickets strictly
    in the order people queue?
    Return YES, if Vasya can sell a ticket to every person and give change with the bills he has at hand at that moment.
    Otherwise return NO.

4. Roman Numerals Encoder
    Create a function taking a positive integer as its parameter and returning a string containing
    the Roman Numeral representation of that integer.
    Modern Roman numerals are written by expressing each digit separately starting with the left most digit and
    skipping any digit with a value of zero.
    In Roman numerals 1990 is rendered: 1000=M, 900=CM, 90=XC; resulting in MCMXC.
    2008 is written as 2000=MM, 8=VIII; or MMVIII.
    1666 uses each Roman symbol in descending order: MDCLXVI.

5. Scramblies
    Complete the function scramble(str1, str2) that returns true if a portion of str1 characters can be rearranged
     to match str2, otherwise returns false.

    Notes:
    Only lower case letters will be used (a-z). No punctuation or digits will be included.
    Performance needs to be considered

    Input strings s1 and s2 are null terminated.

    Examples
    scramble('rkqodlw', 'world') ==> True
    scramble('cedewaraaossoqqyt', 'codewars') ==> True
    scramble('katas', 'steak') ==> False

*/


import java.util.ArrayList;
import java.util.HashMap;

public class Codewars {

    // 1.
    // Короткое решение:
    //    public static boolean isValid(String idn) {
    //        return idn.matches("[$_a-zA-Z]+[$\\w]*"); // Варианты паттерна: "[a-zA-Z_$][a-zA-Z\\d_$]*"
    //    }

    public static boolean isValid(String idn) {
        boolean result;

        if(idn.length() == 0) return false;

        String firstSymbol = String.valueOf(idn.charAt(0));
        String firstSymbolPattern = "[a-zA-Z_$]";

        result = firstSymbol.matches(firstSymbolPattern);
        if (!result) return false;

        if (idn.length() > 1) {
            String remainingSymbols = idn.substring(1);
            char[] remainingChars = remainingSymbols.toCharArray();
            String remainingSymbolsPattern = "[a-zA-Z_$0-9]";

            for (char symbol : remainingChars) {
                result = String.valueOf(symbol).matches(remainingSymbolsPattern);
                if (!result) break;
            }
        }

        return result;
    }

    // 2.
    // Короткий ответ:
    //    public static int digital_root(int n) {
    //        return (n != 0 && n%9 == 0) ? 9 : n % 9;
    //    }

    public static int digital_root(int n) {
        int digitalRoot = 0;
        while ((n / 10) > 0){
            digitalRoot += (n % 10);
            n = n / 10;
        }
        digitalRoot += n;
        if ((digitalRoot / 10) == 0) return digitalRoot;
        else return digital_root(digitalRoot);
    }

    // 3.
    // Line.Tickets(new int[] {25, 25, 50, 50, 100})
    public static String Tickets(int[] peopleInLine) {
        ArrayList<Integer> twentyFiveBanknoteArray = new ArrayList<>();
        ArrayList<Integer> fiftyBanknoteArray = new ArrayList<>();
        for (int banknote: peopleInLine) {
            switch (banknote) {
                case 25 -> twentyFiveBanknoteArray.add(banknote);
                case 50 -> {
                    if (twentyFiveBanknoteArray.size() > 0) {
                        twentyFiveBanknoteArray.remove(twentyFiveBanknoteArray.size() - 1);
                        fiftyBanknoteArray.add(banknote);
                    } else return "NO";
                }
                case 100 -> {
                    if (twentyFiveBanknoteArray.size() > 0 && fiftyBanknoteArray.size() > 0) {
                        twentyFiveBanknoteArray.remove(twentyFiveBanknoteArray.size() - 1);
                        fiftyBanknoteArray.remove(fiftyBanknoteArray.size() - 1);
                    } else if (twentyFiveBanknoteArray.size() >= 3) {
                        for (int i = 0; i < 3; i++) {
                            twentyFiveBanknoteArray.remove(twentyFiveBanknoteArray.size() - 1);
                        }
                    } else return "NO";
                }
            }
        }
        return "YES";
    }

    // 4.
    public static String solution(int n) {
        StringBuilder romeNumber = new StringBuilder();
        String num = String.valueOf(n);
        char[] numbers = num.toCharArray();
        for (int i = 0; i < numbers.length; i++) {
            int currentNum = Integer.parseInt(String.valueOf(numbers[i]));
            if(currentNum != 0){
                int order = numbers.length - i;
                switch (order){
                    case 4:
                        romeNumber.append("M".repeat(currentNum));
                        break;
                    case 3:
                        switch (currentNum) {
                            case 9 -> romeNumber.append("CM");
                            case 8, 7, 6, 5 -> {
                                romeNumber.append("D");
                                romeNumber.append("C".repeat(currentNum - 5));
                            }
                            case 4 -> romeNumber.append("CD");
                            case 3, 2, 1 -> romeNumber.append("C".repeat(currentNum));
                        }
                        break;
                    case 2: {
                        switch (currentNum) {
                            case 9 -> romeNumber.append("XC");
                            case 8, 7, 6, 5 -> {
                                romeNumber.append("L");
                                romeNumber.append("X".repeat(currentNum - 5));
                            }
                            case 4 -> romeNumber.append("XL");
                            case 3, 2, 1 -> romeNumber.append("X".repeat(currentNum));
                        }
                        break;
                    }
                    case 1: {
                        switch (currentNum) {
                            case 9 -> romeNumber.append("IX");
                            case 8, 7, 6, 5 -> {
                                romeNumber.append("V");
                                romeNumber.append("I".repeat(currentNum - 5));
                            }
                            case 4 -> romeNumber.append("IV");
                            case 3, 2, 1 -> romeNumber.append("I".repeat(currentNum));
                        }
                        break;
                    }
                }
            }
        }
        return romeNumber.toString();
    }

    // 5.
    public static boolean scramble(String str1, String str2) {
        HashMap<Integer, Character> string1HashMap = new HashMap<>();

        for (int i = 0; i < str1.length(); i++) {
            string1HashMap.put(i, str1.charAt(i));
        }

        for (Character character: str2.toCharArray()) {
            if (!string1HashMap.containsValue(character)) return false;
            else string1HashMap.values().remove(character);
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(scramble("scriptingjava","javascript"));
    }

}

