package com.example.justjava.model;

import com.orm.SugarRecord;

public class CalculatorModel extends SugarRecord {

    private int quantity;
    private String price;

    public CalculatorModel(){

    }

    public CalculatorModel(int quantity, String price) {
        this.quantity = quantity;
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
