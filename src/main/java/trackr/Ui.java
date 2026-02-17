package trackr;

import trackr.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles formatting and console interactions.
 */
public class Ui {

    public static final String SEPARATOR_LINE =
            "____________________________________________________________";

    private final Scanner scanner = new Scanner(System.in);

    /* =======================
       Console-only methods
       ======================= */

    public void showGreeting() {
        System.out.println(formatGreeting());
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showError(String message) {
        System.out.println(formatError(message));
    }

    public void showExit() {
        System.out.println(formatExit());
    }

    /* =======================
       Formatting methods
       (used by commands + GUI)
       ======================= */

    public String formatGreeting() {
        return SEPARATOR_LINE + "\n"
                + " Hello! I'm Trackr\n"
                + " What can I do for you?\n"
                + SEPARATOR_LINE;
    }

    public String formatError(String message) {
        return SEPARATOR_LINE + "\n"
                + " " + message + "\n"
                + SEPARATOR_LINE;
    }

    public String formatAdd(Task task, int taskCount) {
        return SEPARATOR_LINE + "\n"
                + " Got it. I've added this task:\n"
                + "   " + task + "\n"
                + " Now you have " + taskCount + " tasks in the list.\n"
                + SEPARATOR_LINE;
    }

    public String formatList(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append(SEPARATOR_LINE).append("\n");
        sb.append(" Here are the tasks in your list:\n");

        for (int i = 0; i < tasks.size(); i++) {
            sb.append(" ").append(i + 1).append(".")
                    .append(tasks.get(i)).append("\n");
        }

        sb.append(SEPARATOR_LINE);
        return sb.toString();
    }

    public String formatMark(Task task) {
        return SEPARATOR_LINE + "\n"
                + " Nice! I've marked this task as done:\n"
                + "   " + task + "\n"
                + SEPARATOR_LINE;
    }

    public String formatUnmark(Task task) {
        return SEPARATOR_LINE + "\n"
                + " OK, I've marked this task as not done yet:\n"
                + "   " + task + "\n"
                + SEPARATOR_LINE;
    }

    public String formatDelete(Task task, int taskCount) {
        return SEPARATOR_LINE + "\n"
                + " Noted. I've removed this task:\n"
                + "   " + task + "\n"
                + " Now you have " + taskCount + " tasks in the list.\n"
                + SEPARATOR_LINE;
    }

    public String formatFindResults(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append(SEPARATOR_LINE).append("\n");
        sb.append(" Here are the matching tasks in your list:\n");

        for (int i = 0; i < tasks.size(); i++) {
            sb.append(" ").append(i + 1).append(".")
                    .append(tasks.get(i)).append("\n");
        }

        sb.append(SEPARATOR_LINE);
        return sb.toString();
    }

    public String formatExit() {
        return " Bye. Hope to see you again soon!\n"
                + SEPARATOR_LINE;
    }
}
