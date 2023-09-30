import java.util.ArrayList;
import java.util.Scanner;

class MenuItem {
    private String name;
    private double price;

    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class OrderItem {
    private MenuItem menuItem;
    private int quantity;

    public OrderItem(MenuItem menuItem, int quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotal() {
        return menuItem.getPrice() * quantity;
    }
}

class Cafe {
    private ArrayList<MenuItem> menu;
    private ArrayList<OrderItem> order;

    public Cafe() {
        menu = new ArrayList<>();
        order = new ArrayList<>();
    }

    public void addItemToMenu(String name, double price) {
        menu.add(new MenuItem(name, price));
    }

    public void displayMenu() {
        System.out.println("Menu:");
        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.get(i);
            System.out.println((i + 1) + ". " + item.getName() + " - Rs" + item.getPrice());
        }
    }

    public void takeOrder(int menuItemIndex, int quantity) {
        if (menuItemIndex >= 1 && menuItemIndex <= menu.size() && quantity > 0) {
            MenuItem selectedMenuItem = menu.get(menuItemIndex - 1);
            order.add(new OrderItem(selectedMenuItem, quantity));
            System.out.println(quantity + " " + selectedMenuItem.getName() + "(s) added to the order.");
        } else {
            System.out.println("Invalid input. Please try again.");
        }
    }

    public double calculateTotalBill() {
        double totalBill = 0;
        for (OrderItem item : order) {
            totalBill += item.getTotal();
        }
        return totalBill;
    }

    public void generateReceipt() {
        System.out.println("\nReceipt:");
        for (OrderItem item : order) {
            System.out.println(item.getQuantity() + " " + item.getMenuItem().getName() + "(s) - Rs" + item.getTotal());
        }
        System.out.println("\nTotal Bill: Rs" + calculateTotalBill());
    }
}

public class CafeBillingApplication {
    public static void main(String[] args) {
        Cafe cafe = new Cafe();
        cafe.addItemToMenu("Coffee", 40);
        cafe.addItemToMenu("Tea", 20);
        cafe.addItemToMenu("Muffin", 30);

        Scanner scanner = new Scanner(System.in);
        System.out.println("\t*WElcome to Cafe*");
        while (true) {

            System.out.println("\n1. Display Menu");
            System.out.println("2. Place Order");
            System.out.println("3. Generate Receipt");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    cafe.displayMenu();
                    break;
                case 2:
                    cafe.displayMenu();
                    System.out.print("Enter the menu item number: ");
                    int menuItemIndex = scanner.nextInt();
                    System.out.print("Enter the quantity: ");
                    int quantity = scanner.nextInt();
                    cafe.takeOrder(menuItemIndex, quantity);
                    break;
                case 3:
                    cafe.generateReceipt();
                    break;
                case 4:
                    System.out.println("Thank you for visiting!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
