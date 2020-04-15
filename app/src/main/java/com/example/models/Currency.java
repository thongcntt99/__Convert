package com.example.models;

public class Currency {
    private String name;
    private String symbol;
    private double value;

    public Currency() {
    }

    public Currency(String name, String symbol, double value) {
        this.name = name;
        this.symbol = symbol;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getRateTo(Currency currency){
        return (double)Math.round(this.value/currency.value*1000)/1000  ;
    }
}
