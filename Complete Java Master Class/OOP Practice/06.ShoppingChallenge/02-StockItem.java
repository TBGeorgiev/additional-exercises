package com.georgiev;

public class StockItem implements Comparable<StockItem> {
    private final String name;
    private double price;
    private int quantityInStock = 0;
    private int reserved = 0;

    public StockItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public StockItem(String name, double price, int quantityInStock) {
        this.name = name;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }

    public int getReserved() {
        return reserved;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void adjustReserved(int quantity) {
        int newQuantity = this.reserved + quantity;
        if (newQuantity <= this.quantityInStock) {
            this.reserved = newQuantity;
        }
    }

    public void adjustStock(int quantity) {
        int newQuantity = this.quantityInStock + quantity;
        if (newQuantity >= 0) {
            this.quantityInStock = newQuantity;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if((obj == null) || obj.getClass() != this.getClass()) {
            return false;
        }

        String objName = ((StockItem) obj).getName();
        return this.name.equals(objName);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode() + 31;
    }

    @Override
    public int compareTo(StockItem o) {
        if (this == o) {
            return 0;
        }
        if (o != null) {
            return this.name.compareTo(o.getName());
        }
        throw new NullPointerException();
    }

    @Override
    public String toString() {
        return this.name + " price: " + this.price;
    }
}
