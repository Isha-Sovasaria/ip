import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Trackr {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    public Trackr(String filePath) {
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.load());
        this.ui = new Ui();
    }

    public void run() {
        ui.showGreeting();
        while (true) {
            String input = ui.readCommand();
            try {
                checkInput(input);
            } catch (TrackrException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Trackr("data/trackr.txt").run();
    }

    private void checkInput(String input) throws TrackrException {

        if (input.equals("bye")) {
            ui.showExit();
            System.exit(0);

        } else if (input.equals("list")) {
            ui.showList(tasks.getAll());

        } else if (input.startsWith("mark ")) {
            markTask(input);

        } else if (input.startsWith("unmark ")) {
            unmarkTask(input);

        } else if (input.equals("todo") || input.startsWith("todo ")) {
            handleTodo(input);

        } else if (input.equals("deadline") || input.startsWith("deadline ")) {
            handleDeadline(input);

        } else if (input.equals("event") || input.startsWith("event ")) {
            handleEvent(input);

        }else if (input.startsWith("delete ")) {
            deleteTask(input);
        } else {
            throw new TrackrException(
                    "Sorry, I don't understand that command.");
        }
    }

    private void handleTodo(String input) throws TrackrException {
        if (input.equals("todo")) {
            throw new TrackrException(
                    "The description of a todo cannot be empty.");
        }

        String description = input.substring(5);
        tasks.add(new ToDo(description));
        storage.save(tasks.getAll());
        ui.showAdd(tasks.get(tasks.size() - 1), tasks.size());
    }

    private void handleDeadline(String input) throws TrackrException {
        if (!input.contains(" /by ")) {
            throw new TrackrException(
                    "A deadline must have a /by date.");
        }

        String[] parts = input.substring(9).split(" /by ");
        if (parts[0].isEmpty()) {
            throw new TrackrException(
                    "The description of a deadline cannot be empty.");
        }

        try {
            LocalDate date = LocalDate.parse(parts[1]);
            tasks.add(new Deadline(parts[0], date));
        } catch (DateTimeParseException e) {
            throw new TrackrException(
                    "Please use yyyy-mm-dd format for dates.");
        }

        storage.save(tasks.getAll());
        ui.showAdd(tasks.get(tasks.size() - 1), tasks.size());
    }

    private void handleEvent(String input) throws TrackrException {
        if (!input.contains(" /from ") || !input.contains(" /to ")) {
            throw new TrackrException(
                    "An event must have /from and /to.");
        }

        String[] parts = input.substring(6).split(" /from | /to ");
        try {
            LocalDate from = LocalDate.parse(parts[1]);
            LocalDate to = LocalDate.parse(parts[2]);
            tasks.add(new Event(parts[0], from, to));
        } catch (DateTimeParseException e) {
            throw new TrackrException(
                    "Please use yyyy-mm-dd format for dates.");
        }

        storage.save(tasks.getAll());
        ui.showAdd(tasks.get(tasks.size() - 1), tasks.size());
    }

    private int parseTaskIndex(String input, String errorMessage)
            throws TrackrException {

        int index;
        try {
            index = Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (Exception e) {
            throw new TrackrException(errorMessage);
        }

        if (index < 0 || index >= tasks.size()) {
            throw new TrackrException("That task number does not exist.");
        }

        return index;
    }


    private void markTask(String input) throws TrackrException {
        int index = parseTaskIndex(input, "Please specify a valid task number.");

        Task task = tasks.get(index);
        task.markAsDone();
        storage.save(tasks.getAll());
        ui.showMark(task);

    }

    private void unmarkTask(String input) throws TrackrException {
        int index = parseTaskIndex(input, "Please specify a valid task number.");

        Task task = tasks.get(index);
        task.markAsNotDone();
        storage.save(tasks.getAll());
        ui.showUnmark(task);

    }

    private void deleteTask(String input) throws TrackrException {
        int index = parseTaskIndex(input,
                "Please specify a valid task number to delete.");

        Task removedTask = tasks.remove(index);
        storage.save(tasks.getAll());
        ui.showDelete(removedTask, tasks.size());

    }

}
