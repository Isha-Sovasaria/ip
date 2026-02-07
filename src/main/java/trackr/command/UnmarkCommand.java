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
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws TrackrException {

        if (index < 0 || index >= tasks.size()) {
            throw new TrackrException("That trackr.task number does not exist.");
        }

        Task task = tasks.get(index);
        task.markAsNotDone();
        storage.save(tasks.getAll());
        ui.showUnmark(task);
    }
}
