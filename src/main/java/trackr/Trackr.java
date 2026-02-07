package trackr;
import trackr.command.Command;
import trackr.task.TaskList;
public class Trackr {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    public Trackr(String filePath) {
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.load());
        this.ui = new Ui();
        this.parser= new Parser();
    }

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

    public static void main(String[] args) {
        new Trackr("data/trackr.txt").run();
    }
}








