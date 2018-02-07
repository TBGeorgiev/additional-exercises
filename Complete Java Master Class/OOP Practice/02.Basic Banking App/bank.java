import java.util.ArrayList;

public class Bank2 {
    private String name;
    private ArrayList<Branch2> branch2s;

    public Bank2(String name) {
        this.name = name;
        this.branch2s = new ArrayList<>();
    }

    public void addBranch2(String name) {
        Branch2 branch2 = findBranch2(name);
        if (branch2 == null) {
            this.branch2s.add(new Branch2(name));
        }
    }

    public void addCustomerToBranchWithTrans2(String branchName, String custName, double initialAmount) {
        Branch2 branch2 = findBranch2(branchName);
        if (branch2 != null) {
            branch2.addCustomer2(custName, initialAmount);
        }
    }

    public void addTransToExistingCust(String branchName, String name, double amount) {
        Branch2 branch2 = findBranch2(branchName);
        if (branch2 != null) {
            branch2.addTransactionToCustomer2(name, amount);
        }
    }

    public void listOfTransactions(String branchName) {
        Branch2 branch2 = findBranch2(branchName);
        if (branch2 != null) {
            System.out.println("Showing details for branch: " + branch2.getBranchName());

            ArrayList<Customer2> customer2s = branch2.getCustomers2();
            for (int i = 0; i < customer2s.size(); i++) {
                Customer2 customer2 = customer2s.get(i);
                ArrayList<Double> amount = customer2.getTransactions();
                System.out.printf("Customer name: %s%n", customer2.getName());
                for (int j = 0; j < amount.size(); j++) {
                    System.out.printf("Transaction %d: %.2f%n", j+1, amount.get(j));
                }
            }
        }
    }

    public Branch2 findBranch2(String branchName) {
        for (int i = 0; i < branch2s.size(); i++) {
            Branch2 checkedBranch2 = branch2s.get(i);
            if (checkedBranch2.getBranchName().equals(branchName)) {
                return checkedBranch2;
            }
        }
        return null;
    }
}
