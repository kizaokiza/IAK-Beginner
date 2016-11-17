package com.example.justjava.model;

import com.orm.SugarRecord;

/**
 * Created by pratama on 11/6/2016.
 */

public class HistoryModel extends SugarRecord {

    private int quantity;
    private String name;
    private String menu;
    private String price;

    public HistoryModel() {

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }
}
