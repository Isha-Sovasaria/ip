package trackr.command;

import trackr.task.TaskList;
import trackr.Storage;
import trackr.TrackrException;
import trackr.Ui;

/**
 * Represents an executable user command.
 * All specific commands extend this class and implement the execution logic.
 */
public abstract class Command {

    /**
     * Executes the command using the given task list, user interface, and storage.
     *
     * @param tasks The task list to operate on.
     * @param ui The user interface used to display messages.
     * @param storage The storage used to persist task data.
     * @throws TrackrException If an error occurs during command execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage)
            throws TrackrException;

    /**
     * Indicates whether this command should terminate the application.
     *
     * @return True if the command is an exit command, otherwise false.
     */
    public boolean isExit() {
        return false;
    }
}
