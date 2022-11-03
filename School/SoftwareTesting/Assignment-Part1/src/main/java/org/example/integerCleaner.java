package org.example;

public class integerCleaner {
    public int cleanInteger(String string) {

        //If the integer is an incorrect string.
        if (string.equals("€ ---")) {
            //Set it to 0.
            string = "0";
        }
        //Clean the price string by replacing the unwanted characters which would not allow it to be parsed.
        int price = (Integer.parseInt(string.replace(",", "").replace("€", "").replace(" ", "").replace(".", "").replace("\n", "").replaceAll("[a-zA-Z]", "")));
        //Multiply it by 100 since MarketAlertUM recognizes the numbers in cents.
        price = price * 100;

        //Returning the price.
        return price;
    }
}
