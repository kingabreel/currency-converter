package view;

import controller.CurrencyController;

import java.util.Scanner;

public class CurrencyView {
    private CurrencyController controller;
    private Scanner scanner;
    private String currency;
    private String currencyToConvert;

    public CurrencyView(){
        controller = new CurrencyController();
        scanner = new Scanner(System.in);
        startApplication();
    }

    private void startApplication(){
        System.out.println("Welcome to the currency converter application!");
        System.out.println("===================");
        System.out.println("""
            The options for today are:
            ______________________
            |Code \t| \tCountry  |
            ______________________
            |USD  \t| \tUSA      |
            |BRL  \t| \tBrazil   |
            |BOB  \t| \tBolivia  |
            |CNY  \t| \tChina    |
            |INR  \t| \tIndia    |
            |ARS  \t| \tArgentine|
            |EUR  \t| \tEurope   |
            |AUD  \t| \tAustralia|
            ______________________
            """);
        System.out.print("Please input one country code: ");
        currency = scanner.nextLine();

        if(!controller.isCurrencyAvailable(currency)) throw new RuntimeException();

        System.out.print("Please select another currency to " + currency + " be converted");
        currencyToConvert = scanner.nextLine();

        if (!controller.isCurrencyAvailable(currencyToConvert)) throw new RuntimeException();

    }
}
