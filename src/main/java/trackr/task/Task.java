package trackr.task;

/**
 * Represents a generic task with a description and completion status.
 * Serves as the base class for different types of tasks.
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Creates a task with the given description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is done, otherwise a blank space.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The formatted string representing the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + description;
    }

    /**
     * Returns the file storage representation of the task.
     *
     * @return The formatted string used for saving the task.
     */
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
