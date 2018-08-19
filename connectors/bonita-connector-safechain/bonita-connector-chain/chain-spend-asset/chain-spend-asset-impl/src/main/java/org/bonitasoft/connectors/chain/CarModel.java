package org.bonitasoft.connectors.chain;

public class CarModel {

    private  String model;
    private String color;
    private String weight;
    private int amount;
    private String currency;

    public CarModel() {
    }

    /**
     *
     * @param model
     * @param color
     * @param weight
     * @param amount
     * @param currency
     */
    public CarModel(String model, String color, String weight, int amount, String currency) {
        this.model = model;
        this.color = color;
        this.weight = weight;
        this.amount = amount;
        this.currency = currency;
    }
}