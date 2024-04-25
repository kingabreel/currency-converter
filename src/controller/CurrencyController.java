package controller;

import com.google.gson.Gson;
import model.Currency;
import model.response.CurrencyApiResponse;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CurrencyController {
    private String[] availableCurrencies;
    private String apiKey = "6b072b2993a83b81ce679b58";
    private String currency;
    private String currencyToConvert;
    private int quantity;
    private String endpoint;

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

    public Currency startConvertion(String currency, int quantity, String currencyToConvert){
        this.currency = currency;
        this.currencyToConvert = currencyToConvert;
        this.quantity = quantity;
        endpoint = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + currency;

        return GetCurrency();
    }

    private Currency GetCurrency(){
        try {
            URL url = new URL(endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();

            Gson gson = new Gson();
            CurrencyApiResponse currencyApiResponse = gson.fromJson(response.toString(), CurrencyApiResponse.class);

            double conversionRate = currencyApiResponse.getConversionRates().get(currencyToConvert);
            String lastUpdateDate = currencyApiResponse.getTime_last_update_utc();

            return new Currency(currency, quantity, currencyToConvert, conversionRate, lastUpdateDate);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public double getValueConverted(Currency currencyConverted){
        return currencyConverted.quantity() * currencyConverted.conversion_rate();
    }
}
