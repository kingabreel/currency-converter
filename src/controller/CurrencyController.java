package controller;

public class CurrencyController {
    private String[] availableCurrencies;

    public CurrencyController(){
        availableCurrencies = new String[8];
        availableCurrencies[0] = "USD";
        availableCurrencies[1] = "BRL";
        availableCurrencies[2] = "BOB";
        availableCurrencies[3] = "CNY";
        availableCurrencies[4] = "INR";
        availableCurrencies[5] = "ARS";
        availableCurrencies[6] = "EUR";
        availableCurrencies[7] = "AUD";
    }

    public boolean isCurrencyAvailable(String currency){
        for (int i = 0; i < availableCurrencies.length; i++){
            if (currency.equalsIgnoreCase(availableCurrencies[i])){
                return true;
            }
        }
        return false;
    }
}
