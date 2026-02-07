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

public class Storage {

    private final Path filePath;

    public Storage(String filePath) {
        this.filePath = Path.of(filePath);
    }

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
