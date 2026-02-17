package trackr.command;

import trackr.Ui;
import trackr.task.TaskList;
import trackr.Storage;
import trackr.TrackrException;

/**
 * Represents an executable user command.
 */
public abstract class Command {

    /**
     * Executes the command and returns the result message.
     *
     * @param tasks   The task list to operate on.
     * @param ui
     * @param storage The storage used to persist task data.
     * @return The result message to be shown to the user.
     * @throws TrackrException If an error occurs.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage)
            throws TrackrException;

    /**
     * Indicates whether this command should terminate the application.
     */
    public boolean isExit() {
        return false;
    }
}
