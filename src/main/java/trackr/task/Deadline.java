package trackr.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    private String formatDate() {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("MMM dd yyyy");
        return by.format(formatter);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDate() + ")";
    }

    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0")
                + " | " + description
                + " | " + by;
    }

}
