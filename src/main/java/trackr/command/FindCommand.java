package trackr.command;

import trackr.Storage;
import trackr.Ui;
import trackr.task.Task;
import trackr.task.TaskList;

import java.util.ArrayList;

/**
 * Represents a command that finds tasks matching a keyword.
 */
public class FindCommand extends Command {

    private final String keyword;

    /**
     * Creates a find command with the given keyword.
     *
     * @param keyword The keyword to search for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matches = tasks.find(keyword);
        ui.showFindResults(matches);
    }
}
