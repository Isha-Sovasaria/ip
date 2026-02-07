package trackr;
import trackr.task.Task;
import java.util.Scanner;

/**
 * Handles all interactions with the user.
 * Responsible for displaying messages and reading user input.
 */
public class Ui {

    private static final String SEPARATOR_LINE =
            "____________________________________________________________";

    private final Scanner scanner = new Scanner(System.in);

    /**
     * Displays the greeting message when the application starts.
     */
    public void showGreeting() {
        System.out.println(SEPARATOR_LINE);
        System.out.println(" Hello! I'm trackr.Trackr");
        System.out.println(" What can I do for you?");
        System.out.println(SEPARATOR_LINE);
    }

    /**
     * Reads a command entered by the user.
     *
     * @return The user input string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println(SEPARATOR_LINE);
        System.out.println(" " + message);
        System.out.println(SEPARATOR_LINE);
    }

    /**
     * Displays a confirmation message after adding a task.
     *
     * @param task The task that was added.
     * @param taskCount The total number of tasks in the list.
     */
    public void showAdd(Task task, int taskCount) {
        System.out.println(SEPARATOR_LINE);
        System.out.println(" Got it. I've added this trackr.task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + taskCount
                + " tasks in the list.");
        System.out.println(SEPARATOR_LINE);
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public void showList(java.util.ArrayList<Task> tasks) {
        System.out.println(SEPARATOR_LINE);
        System.out.println(" Here are the tasks in your list:");

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i));
        }

        System.out.println(SEPARATOR_LINE);
    }

    /**
     * Displays a confirmation message after marking a task as done.
     *
     * @param task The task that was marked as done.
     */
    public void showMark(Task task) {
        System.out.println(SEPARATOR_LINE);
        System.out.println(" Nice! I've marked this trackr.task as done:");
        System.out.println("   " + task);
        System.out.println(SEPARATOR_LINE);
    }

    /**
     * Displays a confirmation message after unmarking a task.
     *
     * @param task The task that was marked as not done.
     */
    public void showUnmark(Task task) {
        System.out.println(SEPARATOR_LINE);
        System.out.println(" OK, I've marked this trackr.task as not done yet:");
        System.out.println("   " + task);
        System.out.println(SEPARATOR_LINE);
    }

    /**
     * Displays a confirmation message after deleting a task.
     *
     * @param task The task that was removed.
     * @param taskCount The remaining number of tasks in the list.
     */
    public void showDelete(Task task, int taskCount) {
        System.out.println(SEPARATOR_LINE);
        System.out.println(" Noted. I've removed this trackr.task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + taskCount
                + " tasks in the list.");
        System.out.println(SEPARATOR_LINE);
    }

    /**
     * Displays the exit message when the application terminates.
     */
    public void showExit() {
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(SEPARATOR_LINE);
    }
}
