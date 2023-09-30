import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class User {
    private String username;
    private String password;
    private List<Task> tasks;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.tasks = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(String description, LocalDateTime reminderTime) {
        Task task = new Task(description, reminderTime);
        tasks.add(task);
    }

    public void updateTask(int taskIndex, String newDescription, LocalDateTime newReminderTime) {
        if (isValidTaskIndex(taskIndex)) {
            Task task = tasks.get(taskIndex - 1);
            task.setDescription(newDescription);
            task.setReminderTime(newReminderTime);
        }
    }

    public void deleteTask(int taskIndex) {
        if (isValidTaskIndex(taskIndex)) {
            tasks.remove(taskIndex - 1);
        }
    }

    private boolean isValidTaskIndex(int taskIndex) {
        return taskIndex >= 1 && taskIndex <= tasks.size();
    }
}

class Task {
    private String description;
    private LocalDateTime reminderTime;

    public Task(String description, LocalDateTime reminderTime) {
        this.description = description;
        this.reminderTime = reminderTime;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getReminderTime() {
        return reminderTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setReminderTime(LocalDateTime reminderTime) {
        this.reminderTime = reminderTime;
    }
}

public class DailyTaskSchedulerApp {
    private static Map<String, User> users = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n\t\tDaily Task Scheduler");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice code: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    System.out.println("Exiting the Daily Task Scheduler.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void register() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (users.containsKey(username)) {
            System.out.println("Username already exists. Try again.");
        } else {
            users.put(username, new User(username, password));
            System.out.println("Registration successful.");
        }
    }

    private static void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (users.containsKey(username)) {
            User user = users.get(username);
            if (user.getPassword().equals(password)) {
                showTaskMenu(user);
            } else {
                System.out.println("Invalid password.");
            }
        } else {
            System.out.println("User not found. Register first.");
        }
    }

    private static void showTaskMenu(User user) {
        while (true) {
            System.out.println("\nTask Management");
            System.out.println("1. Add Task");
            System.out.println("2. Update Task");
            System.out.println("3. Delete Task");
            System.out.println("4. List Tasks");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addTask(user);
                    break;
                case 2:
                    updateTask(user);
                    break;
                case 3:
                    deleteTask(user);
                    break;
                case 4:
                    listTasks(user);
                    break;
                case 5:
                    return; // Logout and go back to the main menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addTask(User user) {
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        System.out.print("Enter task reminder date and time (yyyy-MM-dd HH:mm): ");
        String reminderTimeString = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime reminderTime = LocalDateTime.parse(reminderTimeString, formatter);

        user.addTask(description, reminderTime);
        System.out.println("Task added.");
    }

    private static void updateTask(User user) {
        listTasks(user);
        System.out.print("Enter task index to update: ");
        int taskIndex = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        if (user.getTasks().isEmpty()) {
            System.out.println("No tasks to update.");
            return;
        }
        if (taskIndex < 1 || taskIndex > user.getTasks().size()) {
            System.out.println("Invalid task index.");
            return;
        }

        System.out.print("Enter new task description: ");
        String newDescription = scanner.nextLine();
        System.out.print("Enter new task reminder date and time (yyyy-MM-dd HH:mm): ");
        String newReminderTimeString = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime newReminderTime = LocalDateTime.parse(newReminderTimeString, formatter);

        user.updateTask(taskIndex, newDescription, newReminderTime);
        System.out.println("Task updated.");
    }

    private static void deleteTask(User user) {
        listTasks(user);
        System.out.print("Enter task index to delete: ");
        int taskIndex = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        if (user.getTasks().isEmpty()) {
            System.out.println("No tasks to delete.");
            return;
        }
        if (taskIndex < 1 || taskIndex > user.getTasks().size()) {
            System.out.println("Invalid task index.");
            return;
        }

        user.deleteTask(taskIndex);
        System.out.println("Task deleted.");
    }

    private static void listTasks(User user) {
        List<Task> tasks = user.getTasks();
        System.out.println("\nTask List:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println((i + 1) + ". " + task.getDescription() + " (Reminder: " + task.getReminderTime() + ")");
        }
    }
}
