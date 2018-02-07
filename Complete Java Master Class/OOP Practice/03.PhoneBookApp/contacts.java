import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Contacts {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private ArrayList<String> contactName;
    private ArrayList<String> contactNumber;

    public Contacts(ArrayList<String> contactName, ArrayList<String> contactNumber) {
        this.contactName = contactName;
        this.contactNumber = contactNumber;
    }

    public void addNameAndNumber() throws IOException {
        System.out.print("Enter name: ");
        String name = reader.readLine();
        if (contactName.contains(name)) {
            while (contactName.contains(name)) {
                System.out.print("Contact already exists. Please enter a new name or type 'back' to go back: ");
                name = reader.readLine();
                if (name.equals("back")) {
                    System.out.println("Please enter a new command: ");
                    return;
                }
            }
        }
        contactName.add(name);
        System.out.print("Enter number: ");
        String number = reader.readLine();
        contactNumber.add(number);
        System.out.println("Contact added. Please enter a new command: ");
    }
    public void modifyContact() throws IOException {
        if (contactName.isEmpty()) {
            System.out.println("List is empty. Please use a new command.");
            return;
        }
        System.out.print("Enter name of contact you want to modify: ");
        String name = reader.readLine();
        while (!contactName.contains(name)) {
            System.out.println("Contact doesn't exist. Please try again or enter 'back' to return to the main menu.");
            name = reader.readLine();
            if (name.equals("back")) {
                System.out.println("Now in main menu. Please enter a valid command or press 5 for a list of commands.");
                return;
            }
        }
        int index = contactName.indexOf(name);
        System.out.print("Enter the new number: ");
        String newNumber = reader.readLine();
        contactNumber.set(index, newNumber);
        System.out.println("New number set. Please enter a new command: ");
    }

    public void removeContact() throws IOException {
        if (contactName.isEmpty()) {
            System.out.println("List is empty. Please use a new command.");
            return;
        }
        System.out.print("Enter name of contact you want to remove: ");
        String nameToRemove = reader.readLine();
        while (!contactName.contains(nameToRemove)) {
            System.out.println("Contact doesn't exist. Please try again or enter 'back' to return to the main menu");
            nameToRemove = reader.readLine();
            if (nameToRemove.equals("back")) {
                System.out.println("Now in main menu. Please enter a valid command or press 5 for a list of commands.");
                return;
            }
        }
        int index = contactName.indexOf(nameToRemove);
        contactName.remove(index);
        contactNumber.remove(index);
        System.out.println("Contact removed. Please enter a new command: ");
    }
    public void printContacts() {
        if (contactName.isEmpty()) {
            System.out.println("List is empty. Please use a new command.");
            return;
        }
        for (int i = 0; i < contactName.size(); i++) {
            System.out.printf("Name: %s -> Number: %s%n", contactName.get(i), contactNumber.get(i));
        }
    }
}
