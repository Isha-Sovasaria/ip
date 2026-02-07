package trackr.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a start and end date.
 */
public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    /**
     * Creates an event task with the given description and date range.
     *
     * @param description The description of the event.
     * @param from The start date of the event.
     * @param to The end date of the event.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Formats a date into a readable string representation.
     *
     * @param x The date to be formatted.
     * @return The formatted date string.
     */
    private String formatDate(LocalDate x) {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("MMM dd yyyy");
        return x.format(formatter);
    }

    /**
     * Returns the string representation of the event task.
     *
     * @return The formatted string representing the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + formatDate(from) + " to: " + formatDate(to) + ")";
    }

    /**
     * Returns the file storage representation of the event task.
     *
     * @return The formatted string used for saving the task.
     */
    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0")
                + " | " + description
                + " | " + from
                + " | " + to;
    }
}
