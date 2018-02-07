import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Burger {

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private String breadRollType;
    private String meat;
    private double price;

    private boolean lettuce = false;
    private boolean tomato = false;
    private boolean carrot = false;
    private boolean spice = false;

    private double lettucePrice = 0.55;
    private double tomatoPrice = 0.45;
    private double carrotPrice = 0.35;
    private double spicePrice = 0.25;

    private double extraPrice = 0;

    private boolean isDeluxe = false;

    public Burger(String breadRollType, String meat, double price) {
        this.breadRollType = breadRollType;
        this.meat = meat;
        this.price = price;
    }

    public String getBreadRollType() {
        return breadRollType;
    }

    public String getMeat() {
        return meat;
    }

    public void setDeluxe(boolean active) {
        isDeluxe = active;
    }

    public void additionsBasic() throws IOException {
        if (isDeluxe) {
            return;
        }
        String addition = "";
        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 0: addition = "lettuce";break;
                case 1: addition = "tomato";break;
                case 2: addition = "carrot";break;
                case 3: addition = "spice";break;
            }
            System.out.printf("Do you want extra %s?%n", addition);
            String answer = reader.readLine().toLowerCase();
            if (answer.equals("yes")) {
                switch (i) {
                    case 0: lettuce = true; extraPrice += lettucePrice; break;
                    case 1: tomato = true; extraPrice += tomatoPrice; break;
                    case 2: carrot = true; extraPrice += carrotPrice; break;
                    case 3: spice = true; extraPrice += spicePrice; break;
                }
            }
            else if (!answer.equals("no")) {
                System.out.println("Invalid input. Please use Yes or No");
                i--;
            }
        }
    }

    public void additionsToPrint() {
        if (tomato || carrot || lettuce || spice) {
            System.out.printf("Base price: $%.2f%n", price);
            System.out.printf("It contains: %s and %s.%n", breadRollType, meat);
        }
        if (lettuce) {
            System.out.printf("Extra Lettuce added for $%.2f%n", lettucePrice);
        }
        if (tomato) {
            System.out.printf("Extra Tomatoes added for $%.2f%n", tomatoPrice);
        }
        if (carrot) {
            System.out.printf("Extra Carrots added for $%.2f%n", carrotPrice);
        }
        if (spice) {
            System.out.printf("Extra spice added for $%.2f%n", spicePrice);
        }
    }

    public double getPrice() {
        return price;
    }

    public double getExtraPrice() {
        return extraPrice;
    }

    public void returnTotalForBasic() {
        System.out.printf("Total price: %.2f%n", getPrice() + getExtraPrice());
    }
}

class HealthyBurger extends Burger {
    private boolean extraCheese = false;
    private boolean jerky = false;

    private double extraCheesePrice = 1.25;
    private double extraJerkyPrice = 1.35;

    public HealthyBurger() {
        super("Brown rye bread roll", "Beef", 6.69);
    }

    public void additionsNew() throws IOException {
        additionsBasic();
        String addition = "";
        for (int i = 0; i < 2; i++) {
            switch (i) {
                case 0: addition = "extra cheese"; break;
                case 1: addition = "jerky"; break;
            }
            System.out.printf("Do you want extra %s%n", addition);
            String answer = reader.readLine().toLowerCase();
            if (answer.equals("yes")) {
                switch (i) {
                    case 0: extraCheese = true; break;
                    case 1: jerky = true; break;
                }
            }
            else if (!answer.equals("no")) {
                System.out.println("Wrong input. Please use Yes or No.");
                i--;
            }
        }
    }
    public double totalPrice() throws IOException {
        double total = getPrice() + getExtraPrice();
        if (extraCheese) {
            total += extraCheesePrice;
        }
        if (jerky) {
            total += extraJerkyPrice;
        }
        return total;
    }

    public void showInfo() throws IOException {
        additionsToPrint();
        if (extraCheese) {
            System.out.printf("Extra Cheese added for $%.2f%n", extraCheesePrice);
        }
        if (jerky) {
            System.out.printf("Extra Jerky added for $%.2f%n", extraJerkyPrice);
        }
        System.out.printf("Total price: $%.2f%n", totalPrice());

    }
}

class DeluxeBurger extends Burger {
    public void isDeluxe() {
        super.setDeluxe(true);
    }

    private double extraDrinks = 1.50;
    private double extraChips = 1.25;

    public DeluxeBurger() {
        super("Sickening White Bread", "Sickening Tuna", 9000);
    }

    public void showInfo() {
        System.out.printf("Base price of Burger is: $%.2f%n", getPrice());
        System.out.printf("It contains: %s and %s.%n", getBreadRollType(), getMeat());
        System.out.printf("Extra Drinks added for $%.2f%n", extraDrinks);
        System.out.printf("Extra Chips added for $%.2f%n", extraChips);
        System.out.printf("Total price: $%.2f%n", getPrice() + extraChips + extraDrinks + getExtraPrice());
    }
}



public class BillsBurgersChallenge {
    public static void main(String[] args) throws IOException {
        Burger burger = new Burger("Soft Bunz", "Chicken", 3.50);
        HealthyBurger healthyBurger = new HealthyBurger();
        DeluxeBurger deluxeBurger = new DeluxeBurger();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.printf("Hello and welcome to our new burger order app!%nWe hope to make you happy by providing excellent hambubger service ;)%nPlease tell us what kind of burger you would like to order: %n1: Regular%n2: Healthy%n3: Deluxe%n");
        String input = reader.readLine().toLowerCase();
        char[] ch = input.toCharArray();
        while (!Character.isDigit(ch[0])) {
            System.out.println("Wrong input. Please use the numbers 1-3 and try again.");
            input = reader.readLine().toLowerCase();
            ch = input.toCharArray();
        }

        int inputToDigit = Integer.parseInt(Character.toString(ch[0]));
        switch (inputToDigit) {
            case 1: burger.additionsBasic(); burger.additionsToPrint(); burger.returnTotalForBasic(); break;
            case 2: healthyBurger.additionsNew(); healthyBurger.showInfo(); break;
            case 3: deluxeBurger.isDeluxe(); deluxeBurger.showInfo(); break;
        }
        System.out.println();
        System.out.printf("Thank you for your order!%nPlease rate our service in 1-5:%n");

        while (true) {
            String inputLast = reader.readLine();
            char[] ch2 = inputLast.toCharArray();
            while (!Character.isDigit(ch2[0])) {
                System.out.println("Give proper input you blueboy!");
            }
            int n = Integer.parseInt(Character.toString(ch2[0]));
            switch (n) {
                case 1:
                    System.out.println("You're a disgrace to the emperor!"); break;
                case 2:
                    System.out.println("But sir, we didn't drop it on the floor. The dirt was already there!"); break;
                case 3:
                    System.out.println("If I tell you I love you, but only a little, will you be satisfied?"); break;
                case 4:
                    System.out.println("You're close to where you need to be, but we thrive only on excellence."); break;
                case 5:
                    System.out.printf("Chyu is a noob.%nYOU WIN"); return;
            }
        }
    }
}
