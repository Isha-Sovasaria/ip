package trackr.command;

import trackr.Storage;
import trackr.TrackrException;
import trackr.Ui;
import trackr.task.TaskList;

public class ExitCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage)
            throws TrackrException {

        return ui.formatExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
