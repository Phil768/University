package org.example;

public class stringCleaner {
    public String cleanString(String string) {
        //Returning the cleaned string, after removing all the characters which could not be passed to the JSON request.
        return "Description: " + string.replace("\n", "").replace("\"", "");
    }
}
