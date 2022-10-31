package org.example;

import org.openqa.selenium.By;

public class stringCleaner {
    public String cleanString(String string) {
        return "Description: " + string.replace("\n", "").replace("\"", "");
    }
}
