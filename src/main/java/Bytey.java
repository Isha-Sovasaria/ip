import java.util.ArrayList;
import java.util.Scanner;

public class Bytey {

    private static final String LINE =
            "____________________________________________________________";

    private static ArrayList<Task> tasks = new ArrayList<>();
    private static Storage storage = new Storage();

    public static void main(String[] args) {
        System.out.println("Working directory: " + System.getProperty("user.dir"));
        Scanner sc = new Scanner(System.in);
        showGreeting();
        tasks = storage.load();
        while (true) {
            String input = sc.nextLine();
            try {
                checkInput(input);
            } catch (ByteyException e) {
                System.out.println(LINE);
                System.out.println(" " + e.getMessage());
                System.out.println(LINE);
            }
        }
    }

    private static void checkInput(String input) throws ByteyException {

        if (input.equals("bye")) {
            System.out.println(LINE);
            printExit();
            System.exit(0);

        } else if (input.equals("list")) {
            showList();

        } else if (input.startsWith("mark ")) {
            markTask(input);

        } else if (input.startsWith("unmark ")) {
            unmarkTask(input);

        } else if (input.equals("todo") || input.startsWith("todo ")) {
            handleTodo(input);

        } else if (input.equals("deadline") || input.startsWith("deadline ")) {
            handleDeadline(input);

        } else if (input.equals("event") || input.startsWith("event ")) {
            handleEvent(input);

        }else if (input.startsWith("delete ")) {
            deleteTask(input);
        } else {
            throw new ByteyException(
                    "Sorry, I don't understand that command.");
        }
    }

    private static void handleTodo(String input) throws ByteyException {
        if (input.equals("todo")) {
            throw new ByteyException(
                    "The description of a todo cannot be empty.");
        }

        String description = input.substring(5);
        tasks.add(new ToDo(description));
        storage.save(tasks);
        showAdd();
    }

    private static void handleDeadline(String input) throws ByteyException {
        if (!input.contains(" /by ")) {
            throw new ByteyException(
                    "A deadline must have a /by date.");
        }

        String[] parts = input.substring(9).split(" /by ");
        if (parts[0].isEmpty()) {
            throw new ByteyException(
                    "The description of a deadline cannot be empty.");
        }

        tasks.add(new Deadline(parts[0], parts[1]));
        storage.save(tasks);
        showAdd();
    }

    private static void handleEvent(String input) throws ByteyException {
        if (!input.contains(" /from ") || !input.contains(" /to ")) {
            throw new ByteyException(
                    "An event must have /from and /to.");
        }

        String[] parts = input.substring(6).split(" /from | /to ");
        tasks.add(new Event(parts[0], parts[1], parts[2]));
        storage.save(tasks);
        showAdd();
    }

    private static void showGreeting() {
        System.out.println(LINE);
        System.out.println(" Hello! I'm Bytey");
        System.out.println(" What can I do for you?");
        System.out.println(LINE);
    }

    private static void showAdd() {
        System.out.println(LINE);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + tasks.get(tasks.size() - 1));
        System.out.println(" Now you have " + tasks.size()
                + " tasks in the list.");
        System.out.println(LINE);
    }

    private static void showList() {
        System.out.println(LINE);
        System.out.println(" Here are the tasks in your list:");

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i));
        }

        System.out.println(LINE);
    }

    private static void markTask(String input) throws ByteyException {
        int index;

        try {
            index = Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (Exception e) {
            throw new ByteyException(
                    "Please specify a valid task number.");
        }

        if (index < 0 || index >= tasks.size()) {
            throw new ByteyException(
                    "That task number does not exist.");
        }

        Task task = tasks.get(index);
        task.markAsDone();
        storage.save(tasks);
        System.out.println(LINE);
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + task);
        System.out.println(LINE);
    }

    private static void unmarkTask(String input) throws ByteyException {
        int index;

        try {
            index = Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (Exception e) {
            throw new ByteyException(
                    "Please specify a valid task number.");
        }

        if (index < 0 || index >= tasks.size()) {
            throw new ByteyException(
                    "That task number does not exist.");
        }

        Task task = tasks.get(index);
        task.markAsNotDone();
        storage.save(tasks);
        System.out.println(LINE);
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + task);
        System.out.println(LINE);
    }
    private static void deleteTask(String input) throws ByteyException {
        int index;

        try {
            index = Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (Exception e) {
            throw new ByteyException(
                    "Please specify a valid task number to delete.");
        }

        if (index < 0 || index >= tasks.size()) {
            throw new ByteyException(
                    "That task number does not exist.");
        }

        Task removedTask = tasks.remove(index);
        storage.save(tasks);
        System.out.println(LINE);
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + removedTask);
        System.out.println(" Now you have " + tasks.size()
                + " tasks in the list.");
        System.out.println(LINE);
    }

    private static void printExit() {
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }
}
