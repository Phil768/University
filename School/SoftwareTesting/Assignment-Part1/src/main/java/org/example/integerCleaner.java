package org.example;

public class integerCleaner {
    public int cleanInteger(String string) {

        if (string.equals("€ ---")) {
            string = "0";
        }
        int price = (Integer.parseInt(string.replace(",", "").replace("€", "").replace(" ", "").replace(".", "").replaceAll("[a-zA-Z]", "")));
        price = price * 100;

        return price;
    }
}
