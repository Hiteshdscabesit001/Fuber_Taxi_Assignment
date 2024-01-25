package com.taxi.section1;

import java.util.ArrayList;
import java.util.List;

public class FilterStrings {

//    Question4:
//    Write a function that takes two arguments. Argument 1 is a list of strings, and
//    Argument 2 is a string to match against. The function returns a new list of strings that dont have
//    any matching character from argument 2. (E.g. if arguments are
//    Argument 1 - [“this”, “place” “is ” “an” “island” ]
//    Argument 2 - “is”
//    The function will return -
//            [“place” “an”] )

    public static List<String> filterStrings(List<String> strings, String matchString) {
        List<String> filteredList = new ArrayList<>();

        // Iterate through each string in the list
        for (String str : strings) {
            // Check if the string contains any character from the matchString
            if (!containsAnyCharacter(str, matchString)) {
                filteredList.add(str);
            }
        }

        return filteredList;
    }

    private static boolean containsAnyCharacter(String str, String matchString) {
        // Check if the string contains any character from the matchString
        for (char c : matchString.toCharArray()) {
            if (str.contains(String.valueOf(c))) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // Example usage
        List<String> stringList = List.of("this", "place", "is", "an", "island");
        String matchString = "is";

        List<String> filteredList = filterStrings(stringList, matchString);
        System.out.println(filteredList);  // Output: [place, an]
    }
}
