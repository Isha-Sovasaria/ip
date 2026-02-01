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
            if (input.equals("bye")) {
                System.out.println(LINE);
                printExit();
                break;
            }
            else if (input.equals("list")) {
                showList();
            } else if (input.startsWith("mark ")) {
                markTask(input);
            } else if (input.startsWith("unmark ")) {
                unmarkTask(input);
            } else if (input.startsWith("todo ")) {
                String description = input.substring(5);
                tasks[taskCount++] = new ToDo(description);
                showAdd();

            } else if (input.startsWith("deadline ")) {
                String[] parts = input.substring(9).split(" /by ");
                tasks[taskCount++] = new Deadline(parts[0], parts[1]);
                showAdd();

            } else if (input.startsWith("event ")) {
                String[] parts = input.substring(6).split(" /from | /to ");
                tasks[taskCount++] = new Event(parts[0], parts[1], parts[2]);
                showAdd();
            }
            else {
                tasks[taskCount++] = new ToDo(input);
                showAdd();
            }
        }
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

    private static void markTask(String input) {
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        Task task = tasks[index];
        task.markAsDone();

        System.out.println(LINE);
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   [X]" + task.getDescription());
        System.out.println(LINE);
    }

    private static void unmarkTask(String input) {
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        Task task = tasks[index];
        task.markAsNotDone();

        System.out.println(LINE);
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   [ ]" + task.getDescription());
        System.out.println(LINE);
    }

    private static void printExit() {
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }
}
