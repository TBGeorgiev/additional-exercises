import java.util.ArrayList;

public class Customer2 {
    private String name;
    private ArrayList<Double> transactions;

    public Customer2(String name, double initialAmount) {
        this.name = name;
        this.transactions = new ArrayList<>();
        transactions.add(initialAmount);
    }

    public void addTransaction2(double amount) {
        this.transactions.add(amount);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Double> getTransactions() {
        return transactions;
    }
}
