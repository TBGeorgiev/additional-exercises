package com.georgiev;

import java.util.LinkedHashMap;
import java.util.Map;

public class Basket {
    private final String name;
    private final Map<StockItem, Integer> list;

    public Basket(String name) {
        this.name = name;
        this.list = new LinkedHashMap<>();
    }

    public int addToBasket(StockItem item, int quantity) {
        if (item != null && quantity > 0) {
            int inBasket = list.getOrDefault(item, 0);
            list.put(item, inBasket + quantity);
            return inBasket;
        }
        return 0;
    }

    public void checkOut() {
        if (list.isEmpty()) {
            System.out.println("Can't check-out an empty cart!");
            return;
        }
        double totalCost = 0.0;
        System.out.println("Commencing checkout procedure: ");
        System.out.println(name + "'s basket contains: " + list.size() + " items.");
        for (Map.Entry<StockItem, Integer> item : list.entrySet()) {
            int reserved = item.getKey().getReserved();
            item.getKey().adjustStock( - reserved);
            item.getKey().adjustReserved( - reserved);
            int amount = item.getValue();
            System.out.println("\t" + amount + "x " + item.getKey().getName() + " - " + item.getKey().getPrice());
            totalCost += amount * item.getKey().getPrice();
        }
        this.list.clear();
        System.out.println("Total: $" + totalCost);
        System.out.println();
    }

    @Override
    public String toString() {
        String s = "\nShopping basket " + name + " contains: " + list.size() + " items.\n";
        double totalCost = 0.0;
        for (Map.Entry<StockItem, Integer> item : list.entrySet()) {
            s = s + item.getKey() + ". " + item.getValue() + " purchased.\n";
            totalCost += item.getKey().getPrice() * item.getValue();
        }
        return s + "Total cost " + totalCost;
    }
}
