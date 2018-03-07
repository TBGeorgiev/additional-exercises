package com.georgiev;

import java.util.LinkedHashMap;
import java.util.Map;

public class StockList {
    private final Map<String, StockItem> list;

    public StockList() {
        this.list = new LinkedHashMap<>();
    }

    public int addStock(StockItem item) {
        if (item != null) {
            StockItem inStock = list.get(item.getName());
            if (inStock != null) {
                item.adjustStock(inStock.getQuantityInStock());
            }
            list.put(item.getName(), item);
            return item.getQuantityInStock();
        }
        return 0;
    }

    public int sellStock(String item, int amount) {
        StockItem toSell = list.get(item);
        if (toSell != null && list.get(item).getQuantityInStock() >= amount + list.get(item).getReserved() && amount > 0) {
            toSell.adjustReserved(amount);
            //toSell.adjustStock(-amount);
            return amount;
        }
        System.out.println("Could not add to basket!");
        return 0;
    }

    public void unReserve(String item, int amount) {
        StockItem toUnReserve = list.get(item);
        if (toUnReserve != null && toUnReserve.getReserved() >= amount) {
            toUnReserve.adjustReserved( - amount);
        }
        else {
            System.out.println("Can't do that!");
        }
    }

    public StockItem get(String key) {
        return list.get(key);
    }

    @Override
    public String toString() {
        String s = "\nStock List\n";
        double totalCost = 0;
        for (Map.Entry<String, StockItem> item : list.entrySet()) {
            StockItem stockItem = item.getValue();

            double itemValue = stockItem.getPrice();

            s = s + stockItem + ". There are " + stockItem.getQuantityInStock() + " in stock. Value of items: ";
            s = s + itemValue + "\n";
            totalCost += itemValue;

        }

        return s + "Total stock value " + totalCost;
    }
}
