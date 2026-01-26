public class Bytey {
    private static final String LINE = "____________________________________________________________";

    public static void main(String[] args) {
        showGreeting();
        printExit();
    }

    private static void showGreeting() {
        System.out.println(LINE);
        System.out.println(" Hello! I'm Bytey");
        System.out.println(" What can I do for you?");
        System.out.println(LINE);
    }

    private static void printExit() {
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }
}
