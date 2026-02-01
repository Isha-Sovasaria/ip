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
            } else {
                addTask(input);
            }
        }
    }

    private static void showGreeting() {
        System.out.println(LINE);
        System.out.println(" Hello! I'm Bytey");
        System.out.println(" What can I do for you?");
        System.out.println(LINE);
    }

    private static void addTask(String task) {
        tasks[taskCount] = new Task(task);
        taskCount++;

        System.out.println(LINE);
        System.out.println(" added: " + task);
        System.out.println(LINE);
    }

    private static void showList() {
        System.out.println(LINE);

        for (int i = 0; i < taskCount; i++) {
            System.out.println(" " + (i + 1) + ". " + tasks[i].getDescription());
        }

        System.out.println(LINE);
    }

    private static void printExit() {
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }
}
