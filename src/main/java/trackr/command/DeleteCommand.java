package trackr.command;

import trackr.task.Task;
import trackr.task.TaskList;
import trackr.Storage;
import trackr.TrackrException;
import trackr.Ui;

public class DeleteCommand extends Command {

    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage)
            throws TrackrException {

        tasks.validateIndex(index);

        Task removedTask = tasks.remove(index);
        storage.save(tasks.getAll());

        return ui.formatDelete(removedTask, tasks.size());
    }
}
