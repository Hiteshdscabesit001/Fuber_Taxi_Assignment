package com.taxi.section1;

public class DigitSumExtractor {

//    Question1:
//    Write a function that extracts the first and last digit in a string that can have any number of
//    digits, and returns the sum of those digits. If there are no digits, return null. (E.g.
//    ABC1YU5HG8BH returns 9).

    public static Integer sumFirstAndLastDigit(String inputString) {
        // Find all digits in the string
        String[] digitStrings = inputString.replaceAll("\\D", "").split("");

        // Check if there are any digits
        if (digitStrings.length == 0) {
            return null;
        }

        // Calculate the sum of the first and last digit
        int firstDigit = Integer.parseInt(digitStrings[0]);
        int lastDigit = Integer.parseInt(digitStrings[digitStrings.length - 1]);
        return firstDigit + lastDigit;
    }

    public static void main(String[] args) {
        String inputStr = "ABC1YU5HG8BH";
        Integer result = sumFirstAndLastDigit(inputStr);

        if (result != null) {
            System.out.println(result);
        } else {
            System.out.println("No digits found in the input string.");
        }
    }
}

