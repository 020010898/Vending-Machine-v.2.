package com.acarasiov.vending_machine.model;


import java.util.List;

public class Config {

    public static int rows;

    public static String columns;

    private List<Item> items;

    public Config() {
    }

    public Config(int rows, String columns, List<Item> items) {
        this.rows = rows;
        this.columns = columns;
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public static int intColumn() {
        return Integer.parseInt(columns);
    }

    public static int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getColumns() {
        return columns;
    }

    public void setColumns(String columns) {
        this.columns = columns;
    }

    @Override
    public String toString() {
        return "Product{" +
                "rows=" + rows +
                ", columns='" + columns + '\'' +
                '}';
    }
}
