package com.georgiev;

public class MainChallenge {
    private static StockList stockList = new StockList();

    public static void main(String[] args) {
        StockItem temp = new StockItem("bread", 0.65, 10);
        stockList.addStock(temp);
        temp = new StockItem("brain", 3.50, 50);
        stockList.addStock(temp);
        temp = new StockItem("soul", 119.99, 12);
        stockList.addStock(temp);
        temp = new StockItem("skyrim", 59.99, 10);
        stockList.addStock(temp);
        temp = new StockItem("whey", 29.95, 30);
        stockList.addStock(temp);
        temp = new StockItem("creatine", 10.35, 50);
        stockList.addStock(temp);

        Basket basket = new Basket("George");
        sellItem(basket, "whey", 2);
        sellItem(basket, "creatine", 10);
        sellItem(basket, "cell-tech", 350);
        basket.checkOut();

        System.out.println("=====================================");

        Basket basket1 = new Basket("Tim");
        sellItem(basket1, "creatine", 40);
        stockList.unReserve("creatine", 5);
        basket1.checkOut();

        System.out.println("=====================================");

        Basket basket2 = new Basket("Edi");
        sellItem(basket2, "soul", 1);
        sellItem(basket2, "brain", 1);
        basket2.checkOut();
        System.out.println(stockList);

    }

    public static int sellItem(Basket basket, String item, int quantity) {
        StockItem stockItem = stockList.get(item);
        if (stockItem == null) {
            System.out.println("We don't have " + item);
            return 0;
        }
        if(stockList.sellStock(item, quantity) != 0) {
            basket.addToBasket(stockItem, quantity);
            return quantity;
        }
        return 0;
    }
}
