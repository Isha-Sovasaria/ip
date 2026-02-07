package trackr.command;

import trackr.task.TaskList;
import trackr.Storage;
import trackr.TrackrException;
import trackr.Ui;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage)
            throws TrackrException;

    public boolean isExit() {
        return false;
    }
}
