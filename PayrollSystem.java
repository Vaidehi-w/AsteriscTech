import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Employee {
    private String name;
    private int id;
    private double salary;
    private int attendance;

    public Employee(String name, int id, double salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
        this.attendance = 0;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public double getSalary() {
        return salary;
    }

    public int getAttendance() {
        return attendance;
    }

    public void updateSalary(double newSalary) {
        this.salary = newSalary;
    }

    public void updateAttendance(int days) {
        this.attendance += days;
    }

    @Override
    public String toString() {
        return "Employee ID: " + id + "\nName: " + name + "\nSalary: $" + salary + "\nAttendance: " + attendance
                + " days";
    }
}

public class PayrollSystem {
    private static ArrayList<Employee> employees = new ArrayList<>();
    private static int nextId = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nEmployee Payroll System");
            System.out.println("1. Register");
            System.out.println("2. Add Employee");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Calculate Salary");
            System.out.println("6. Mark Attendance");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    registerUser(scanner);
                    break;
                case 2:
                    addEmployee(scanner);
                    break;
                case 3:
                    updateEmployee(scanner);
                    break;
                case 4:
                    deleteEmployee(scanner);
                    break;
                case 5:
                    calculateSalary(scanner);
                    break;
                case 6:
                    markAttendance(scanner);
                    break;
                case 7:
                    System.out.println("Exiting the program.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void registerUser(Scanner scanner) {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        // You can add more registration details here if needed
        // For a simple example, we skip other details.
        System.out.println("Registration successful. Your Employee ID is: " + nextId);
        nextId++;
    }

    private static void addEmployee(Scanner scanner) {
        System.out.print("Enter employee name: ");
        String name = scanner.nextLine();
        System.out.print("Enter employee salary: ");
        double salary = scanner.nextDouble();

        Employee employee = new Employee(name, nextId, salary);
        employees.add(employee);
        nextId++;

        System.out.println("Employee added successfully.");
    }

    private static void updateEmployee(Scanner scanner) {
        System.out.print("Enter employee ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        for (Employee employee : employees) {
            if (employee.getId() == id) {
                System.out.print("Enter new salary: $");
                double newSalary = scanner.nextDouble();
                employee.updateSalary(newSalary);
                System.out.println("Salary updated successfully.");
                return;
            }
        }

        System.out.println("Employee not found.");
    }

    private static void deleteEmployee(Scanner scanner) {
        System.out.print("Enter employee ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        for (Employee employee : employees) {
            if (employee.getId() == id) {
                employees.remove(employee);
                System.out.println("Employee deleted successfully.");
                return;
            }
        }

        System.out.println("Employee not found.");
    }

    private static void calculateSalary(Scanner scanner) {
        System.out.print("Enter employee ID to calculate salary: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        for (Employee employee : employees) {
            if (employee.getId() == id) {
                System.out.println("Salary for " + employee.getName() + ": $" + employee.getSalary());
                return;
            }
        }

        System.out.println("Employee not found.");
    }

    private static void markAttendance(Scanner scanner) {
        System.out.print("Enter employee ID to mark attendance: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        for (Employee employee : employees) {
            if (employee.getId() == id) {
                System.out.print("Enter days present: ");
                int days = scanner.nextInt();
                employee.updateAttendance(days);
                System.out.println("Attendance marked successfully.");
                return;
            }
        }

        System.out.println("Employee not found.");
    }
}
