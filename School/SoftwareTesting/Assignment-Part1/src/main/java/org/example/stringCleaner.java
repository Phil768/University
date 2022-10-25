package org.example;

import org.openqa.selenium.By;

public class stringCleaner {
    public String cleanString(String string) {
        String stringDescription = "Description: " + string.replace("\n", "").replace("\"", "");
        return string;
    }
}
