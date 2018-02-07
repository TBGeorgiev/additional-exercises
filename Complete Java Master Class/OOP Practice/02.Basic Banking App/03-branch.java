import java.util.ArrayList;

public class Branch2 {
    private String branchName;
    private ArrayList<Customer2> customers2;

    public Branch2(String branchName) {
        this.branchName = branchName;
        this.customers2 = new ArrayList<>();
    }

    public void addCustomer2(String name, double initialAmount) {
        if (findCustomer2(name) == null) {
            this.customers2.add(new Customer2(name, initialAmount));
        }
    }

    public void addTransactionToCustomer2(String name, double amount) {
        Customer2 customer2 = findCustomer2(name);
        if (customer2 != null) {
            customer2.addTransaction2(amount);
        }
    }


    private Customer2 findCustomer2(String name) {
        for (int i = 0; i < this.customers2.size(); i++) {
            Customer2 checkedCustomer2 = this.customers2.get(i);
            if (checkedCustomer2.getName().equals(name)) {
                return checkedCustomer2;
            }
        }
        return null;
    }

    public String getBranchName() {
        return branchName;
    }

    public ArrayList<Customer2> getCustomers2() {
        return customers2;
    }
}
