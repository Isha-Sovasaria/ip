package trackr.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    private String formatDate(LocalDate x) {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("MMM dd yyyy");
        return x.format(formatter);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + formatDate(from) + " to: " + formatDate(to) + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0")
                + " | " + description
                + " | " + from
                + " | " + to;
    }

}
