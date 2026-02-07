package trackr;
import trackr.task.Task;
import java.util.Scanner;

public class Ui {

    private static final String SEPARATOR_LINE =
            "____________________________________________________________";

    private final Scanner scanner = new Scanner(System.in);

    public void showGreeting() {
        System.out.println(SEPARATOR_LINE);
        System.out.println(" Hello! I'm trackr.Trackr");
        System.out.println(" What can I do for you?");
        System.out.println(SEPARATOR_LINE);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showError(String message) {
        System.out.println(SEPARATOR_LINE);
        System.out.println(" " + message);
        System.out.println(SEPARATOR_LINE);
    }

    public void showAdd(Task task, int taskCount) {
        System.out.println(SEPARATOR_LINE);
        System.out.println(" Got it. I've added this trackr.task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + taskCount
                + " tasks in the list.");
        System.out.println(SEPARATOR_LINE);
    }

    public void showList(java.util.ArrayList<Task> tasks) {
        System.out.println(SEPARATOR_LINE);
        System.out.println(" Here are the tasks in your list:");

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i));
        }

        System.out.println(SEPARATOR_LINE);
    }

    public void showMark(Task task) {
        System.out.println(SEPARATOR_LINE);
        System.out.println(" Nice! I've marked this trackr.task as done:");
        System.out.println("   " + task);
        System.out.println(SEPARATOR_LINE);
    }

    public void showUnmark(Task task) {
        System.out.println(SEPARATOR_LINE);
        System.out.println(" OK, I've marked this trackr.task as not done yet:");
        System.out.println("   " + task);
        System.out.println(SEPARATOR_LINE);
    }

    public void showDelete(Task task, int taskCount) {
        System.out.println(SEPARATOR_LINE);
        System.out.println(" Noted. I've removed this trackr.task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + taskCount
                + " tasks in the list.");
        System.out.println(SEPARATOR_LINE);
    }

    public void showExit() {
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(SEPARATOR_LINE);
    }
}
