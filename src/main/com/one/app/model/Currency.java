package model;

public record Currency(String code, int quantity, String currencyToConvert, double conversion_rate, String date) {
}
