import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PhoneBook {
    public static void main(String[] args) throws IOException {
        PhoneBook phoneBook = new PhoneBook();
        Contacts contacts = new Contacts(new ArrayList<>(), new ArrayList<>());
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to PhoneBook v 0.01");
        System.out.println("Please use one of the following commands:");
        System.out.printf("Press 1 to add a contact.%nPress 2 to modify an existing contact.%nPress 3 to remove a contact.%nPress 4 to print the result.%n");
        System.out.println("Enter 'end' at any time to quit the application.");
        String input = "";
        while (!(input = reader.readLine()).equals("end")) {
            char ch = input.charAt(0);
            while (!Character.isDigit(ch)) {
                System.out.println("Invalid command. Try again or press 5 for a list of commands.");
                input = reader.readLine();
                if (input.equals("end")) {
                    return;
                }
                ch = input.charAt(0);
            }
            int input2 = Integer.parseInt(input);
            switch (input2) {
                case 1: contacts.addNameAndNumber(); break;
                case 2: contacts.modifyContact(); break;
                case 3: contacts.removeContact(); break;
                case 4: contacts.printContacts(); break;
                case 5: phoneBook.showCommands(); break;
                default:
                    System.out.println("Invalid command. Try again or press 5 for a list of commands."); break;
            }
        }
        System.out.println("PhoneBook closed.");
    }

    public void showCommands() {
        System.out.printf("Press 1 to add a contact.%nPress 2 to modify an existing contact.%nPress 3 to remove a contact.%nPress 4 to print the result.%n");
        System.out.println("Enter end at any time to quit the application.");
    }
}
