import java.util.Scanner;

public class Bytey {
    private static final String LINE = "____________________________________________________________";
    private static final int MAX_TASKS = 100;
    private static Task[] tasks = new Task[MAX_TASKS];
    private static int taskCount = 0;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        showGreeting();
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
        }
        else if (input.equals("list")) {
            showList();
        } else if (input.startsWith("mark ")) {
            markTask(input);
        } else if (input.startsWith("unmark ")) {
            unmarkTask(input);
        } else if (input.startsWith("todo")) {
            handleTodo(input);

        } else if (input.startsWith("deadline ")) {
            handleDeadline(input);

        } else if (input.startsWith("event ")) {
            handleEvent(input);
        }
        else {
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
        tasks[taskCount++] = new ToDo(description);
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

        tasks[taskCount++] = new Deadline(parts[0], parts[1]);
        showAdd();
    }
    private static void handleEvent(String input) throws ByteyException {
        if (!input.contains(" /from ") || !input.contains(" /to ")) {
            throw new ByteyException(
                    "An event must have /from and /to.");
        }

        String[] parts = input.substring(6).split(" /from | /to ");
        tasks[taskCount++] = new Event(parts[0], parts[1], parts[2]);
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
        System.out.println("   " + tasks[taskCount - 1]);
        System.out.println(" Now you have " + taskCount + " tasks in the list.");
        System.out.println(LINE);
    }


    private static void showList() {
        System.out.println(LINE);
        System.out.println(" Here are the tasks in your list:");

        for (int i = 0; i < taskCount; i++) {
            System.out.println(" " + (i + 1) + "."+tasks[i]);
        }

        System.out.println(LINE);
    }

    private static void markTask(String input) throws ByteyException {
        int index;

        try {
            index = Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (Exception e) {
            throw new ByteyException("Please specify a valid task number.");
        }

        if (index < 0 || index >= taskCount) {
            throw new ByteyException("That task number does not exist.");
        }

        Task task = tasks[index];
        task.markAsDone();

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
            throw new ByteyException("Please specify a valid task number.");
        }

        if (index < 0 || index >= taskCount) {
            throw new ByteyException("That task number does not exist.");
        }

        Task task = tasks[index];
        task.markAsNotDone();

        System.out.println(LINE);
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + task);
        System.out.println(LINE);
    }

    private static void printExit() {
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }
}
