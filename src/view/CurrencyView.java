package view;

import controller.CurrencyController;
import model.Currency;
import utils.CustomException;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CurrencyView {
    private CurrencyController controller;
    private Scanner scanner;
    private String currency;
    private String currencyToConvert;
    private boolean running;

    public CurrencyView(){
        controller = new CurrencyController();
        scanner = new Scanner(System.in);
        running = true;

        while (running) {
            startApplication();
        }
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
            |ARS  \t| \tArgentina|
            |EUR  \t| \tEurope   |
            |AUD  \t| \tAustralia|
            ______________________
            Type exit to stop the application
            """);
        System.out.print("Please input one country code: ");
        currency = scanner.nextLine();

        if (currency.equalsIgnoreCase("exit")) closeApp();
        if(!controller.isCurrencyAvailable(currency)) {
            throw new CustomException(currency + " is not available.");
        }
        System.out.print("Please select another currency that " + currency + " will be converted: ");
        currencyToConvert = scanner.nextLine();

        if (currencyToConvert.equalsIgnoreCase("exit")) closeApp();
        if (!controller.isCurrencyAvailable(currencyToConvert)) throw new CustomException(currencyToConvert + " is not available.");

        int quantity;
        try {
            System.out.print("How much " + currency.toUpperCase() + " do you want to convert? ");
            quantity = scanner.nextInt();
        } catch (InputMismatchException e){
            System.out.println("Invalid numeric value.");
            quantity = scanner.nextInt();
        }

        if (quantity <= 0) throw new CustomException("Quantity must be greater than 0");

        scanner.nextLine();
        Currency currencyConverted = controller.startConvertion(currency.toUpperCase(), quantity, currencyToConvert.toUpperCase());

        convertedValue(currencyConverted);
    }
    private void convertedValue(Currency currency){
        System.out.println("\n" + currency.quantity() + currency.code() + " to " + currency.currencyToConvert() + " is " +
                controller.getValueConverted(currency) + currency.currencyToConvert());

        controller.saveSearchHistory(currency);
        System.out.println("\n");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void closeApp(){
        running = false;
        System.exit(0);
    }
}
