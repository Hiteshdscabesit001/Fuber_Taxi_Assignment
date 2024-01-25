package com.taxi.section1;

import java.math.BigInteger;

public class LargeNumberSum {

//    Question3:
//    Write a function that accepts two strings, where each string is a very large number (around
//    50 digits), and returns a sum of those two numbers.

    public static String addLargeNumbers(String num1, String num2) {
        // Create BigIntegers from the input strings
        BigInteger bigInt1 = new BigInteger(num1);
        BigInteger bigInt2 = new BigInteger(num2);

        // Add the two BigIntegers
        BigInteger sum = bigInt1.add(bigInt2);

        // Convert the result back to a string
        return sum.toString();
    }

    public static void main(String[] args) {
        // Example usage
        String num1 = "12345678901234567890123456789012345678901234567890";
        String num2 = "98765432109876543210987654321098765432109876543210";

        String result = addLargeNumbers(num1, num2);
        System.out.println(result);
    }
}

