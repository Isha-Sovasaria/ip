package trackr.command;

import trackr.Storage;
import trackr.TrackrException;
import trackr.Ui;
import trackr.task.TaskList;

public class ListCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage)
            throws TrackrException {

        return ui.formatList(tasks.getAll());
    }
}
