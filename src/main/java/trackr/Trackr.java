package trackr;
import trackr.command.Command;
import trackr.task.TaskList;

/**
 * Represents the main application class of Trackr.
 * Responsible for initializing components and running the command loop.
 */
public class Trackr {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    /**
     * Creates a Trackr object using the specified file path for storage.
     *
     * @param filePath The path of the file used to store task data.
     */
    public Trackr(String filePath) {
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.load());
        this.ui = new Ui();
        this.parser= new Parser();
    }

    /**
     * Starts the application and continuously processes user commands.
     * Terminates when an exit command is executed.
     */
    public void run() {
        ui.showGreeting();
        while (true) {
            try {
                String input = ui.readCommand();
                Command command = parser.parse(input);
                command.execute(tasks, ui, storage);

                if (command.isExit()) {
                    System.exit(0);
                }
            } catch (TrackrException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Launches the Trackr application.
     * Entry point of the program
     *
     */
    public static void main(String[] args) {
        new Trackr("data/trackr.txt").run();
    }
}








