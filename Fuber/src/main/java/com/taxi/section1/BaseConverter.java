package com.taxi.section1;

public class BaseConverter {

//    Question2:
//    Write a function that takes a number in base 10, and a new base, then converts the number
//    in that base. (E.g. 123 in base 16 is 7B)

    public static String convertBase(int decimalNumber, int newBase) {
        // Check if the new base is valid (between 2 and 36)
        if (newBase < 2 || newBase > 36) {
            throw new IllegalArgumentException("Invalid base: " + newBase);
        }

        // Convert the decimal number to the specified base
        String result = Integer.toString(decimalNumber, newBase);

        return result.toUpperCase(); // Convert to uppercase for bases above 10 (hexadecimal and above)
    }

    public static void main(String[] args) {
        // Example usage
        int decimalNumber = 123;
        int newBase = 16;

        String result = convertBase(decimalNumber, newBase);
        System.out.println(result);  // Output: 7B
    }
}
