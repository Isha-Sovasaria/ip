package trackr.command;

import trackr.Storage;
import trackr.TrackrException;
import trackr.Ui;
import trackr.task.Task;
import trackr.task.TaskList;

import java.util.ArrayList;

/**
 * Represents a command that finds tasks matching a keyword.
 */
public class FindCommand extends Command {

    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage)
            throws TrackrException {

        ArrayList<Task> matches = tasks.find(keyword);

        return ui.formatFindResults(matches);
    }
}
