package trackr;


import trackr.task.ToDo;
import trackr.task.Deadline;
import trackr.task.Event;
import trackr.task.Task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles loading tasks from and saving tasks to a file on disk.
 * Manages file creation and task reconstruction from stored data.
 */
public class Storage {

    private final Path filePath;

    /**
     * Creates a storage object using the specified file path.
     *
     * @param filePath The path of the file used for storing tasks.
     */
    public Storage(String filePath) {
        this.filePath = Path.of(filePath);
    }

    /**
     * Loads tasks from the storage file.
     * Creates the file if it does not exist.
     *
     * @return A list of tasks loaded from the file.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            if (!Files.exists(filePath)) {
                Files.createDirectories(filePath.getParent());
                Files.createFile(filePath);
                return tasks; // empty list
            }

            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                tasks.add(parseTask(line));
            }

        } catch (IOException e) {
            System.out.println("Error loading saved data.");
        }

        return tasks;
    }

    /**
     * Saves the given list of tasks to the storage file.
     *
     * @param tasks The list of tasks to be saved.
     */
    public void save(ArrayList<Task> tasks) {
        List<String> lines = new ArrayList<>();

        for (Task task : tasks) {
            lines.add(task.toFileString());
        }

        try {
            Files.write(filePath, lines);
        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }

    /**
     * Parses a single line from the storage file and reconstructs the task.
     *
     * @param line A string representing a stored task.
     * @return The reconstructed task object.
     * @throws IllegalArgumentException If the task type is unknown.
     */
    private Task parseTask(String line) {
        String[] parts = line.split(" \\| ");

        switch (parts[0]) {
            case "T":
                ToDo t = new ToDo(parts[2]);
                if (parts[1].equals("1")) t.markAsDone();
                return t;

            case "D":
                Deadline d = new Deadline(parts[2], LocalDate.parse(parts[3]));
                if (parts[1].equals("1")) d.markAsDone();
                return d;

            case "E":
                Event e = new Event(parts[2], LocalDate.parse(parts[3]), LocalDate.parse(parts[4]));
                if (parts[1].equals("1")) e.markAsDone();
                return e;

            default:
                throw new IllegalArgumentException("Unknown trackr.task type");
        }
    }
}
