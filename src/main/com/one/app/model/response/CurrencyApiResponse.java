package model.response;

public class CurrencyApiResponse {

    private String time_last_update_utc;
    private java.util.Map<String, Double> conversion_rates;

    public java.util.Map<String, Double> getConversionRates() {
        return conversion_rates;
    }

    public String getTime_last_update_utc() {
        return time_last_update_utc;
    }
}
