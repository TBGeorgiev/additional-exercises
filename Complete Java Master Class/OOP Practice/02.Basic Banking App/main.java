import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainBank2 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("BPA Alpha v0.01");
        System.out.print("Please enter the name of the Bank: ");
        String nameOfBank = reader.readLine();
        Bank2 bank2 = new Bank2(nameOfBank);
        System.out.printf("Please use one of the following commands:%nPress 1 to add a new Branch.%nPress 2 to add a Customer with an initial transaction to a Branch.%nPress 3 to add a new transaction to an existing Customer.%nPress 4 to list the customers and transactions of a Branch.%nPress 5 to quit the application.%n");
        boolean isQuit = false;

        while (!isQuit) {
            int input = Integer.parseInt(reader.readLine());
            switch (input) {
                case 1:
                    System.out.print("Enter the name of the Branch: ");
                    String nameOfBranch = reader.readLine();
                    bank2.addBranch2(nameOfBranch);
                    System.out.printf("Branch %s added successfully.%n", nameOfBranch);
                    break;
                case 2:
                    System.out.print("Enter Branch name: ");
                    String branchName = reader.readLine();
                    System.out.print("Enter Customer's name: ");
                    String custName = reader.readLine();
                    System.out.print("Enter initial transaction amount: ");
                    double amount = Double.parseDouble(reader.readLine());
                    bank2.addCustomerToBranchWithTrans2(branchName, custName, amount);
                    System.out.printf("Customer: %s added successfully.%n", custName);
                    break;
                case 3:
                    System.out.print("Enter Branch name: ");
                    String branchName2 = reader.readLine();
                    System.out.print("Enter Customer's name: ");
                    String custName2 = reader.readLine();
                    System.out.print("Enter Transaction's amount: ");
                    double amoun2 = Double.parseDouble(reader.readLine());
                    bank2.addTransToExistingCust(branchName2, custName2, amoun2);
                    System.out.printf("New transaction for Customer: %s added successfully.%n", custName2);
                    break;
                case 4:
                    System.out.print("Enter Branch name: ");
                    String branchName3 = reader.readLine();
                    bank2.listOfTransactions(branchName3);
                    break;
                case 5: isQuit = true; break;
                default:
                    System.out.println("Wrong command.");
                    System.out.printf("Please use one of the following commands:%nPress 1 to add a new Branch.%nPress 2 to add a Customer with an initial transaction to a Branch.%nPress 3 to add a new transaction to an existing Customer.%nPress 4 to list the customers and transactions of a Branch.%nPress 5 to quit the application.%n"); break;

            }
        }
        System.out.println("Program closed.");
    }
}
