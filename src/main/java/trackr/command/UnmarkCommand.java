package trackr.command;

import trackr.task.Task;
import trackr.task.TaskList;
import trackr.Storage;
import trackr.TrackrException;
import trackr.Ui;

public class UnmarkCommand extends Command {

    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage)
            throws TrackrException {

        tasks.validateIndex(index);

        Task task = tasks.get(index);
        task.markAsNotDone();
        storage.save(tasks.getAll());

        return ui.formatUnmark(task);
    }
}
