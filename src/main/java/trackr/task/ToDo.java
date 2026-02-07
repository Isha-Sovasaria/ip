package trackr.task;

/**
 * Represents a to-do task with a description.
 * A to-do task does not have any associated date or time.
 */
public class ToDo extends Task {

    /**
     * Creates a to-do task with the given description.
     *
     * @param description The description of the to-do task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the to-do task.
     *
     * @return The formatted string representing the to-do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
