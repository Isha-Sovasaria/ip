package trackr.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with a due date.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Creates a deadline task with the given description and due date.
     *
     * @param description The description of the deadline task.
     * @param by The due date of the task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Formats the due date into a readable string representation.
     *
     * @return The formatted due date string.
     */
    private String formatDate() {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("MMM dd yyyy");
        return by.format(formatter);
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return The formatted string representing the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDate() + ")";
    }

    /**
     * Returns the file storage representation of the deadline task.
     *
     * @return The formatted string used for saving the task.
     */
    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0")
                + " | " + description
                + " | " + by;
    }
}
