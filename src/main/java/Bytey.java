import java.util.Scanner;

public class Bytey {
    private static final String LINE = "____________________________________________________________";

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        showGreeting();
        while (true) {
            String input = sc.nextLine();
            System.out.println(LINE);
            if (input.equals("bye")) {
                printExit();
                break;
            }
            echoInput(input);
        }
    }

    private static void showGreeting() {
        System.out.println(LINE);
        System.out.println(" Hello! I'm Bytey");
        System.out.println(" What can I do for you?");
        System.out.println(LINE);
    }

    private static void echoInput(String input) {
        System.out.println(" " + input);
        System.out.println(LINE);
    }

    private static void printExit() {
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }
}
