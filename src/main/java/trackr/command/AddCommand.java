package trackr.command;

import trackr.task.Task;
import trackr.task.TaskList;
import trackr.Storage;
import trackr.Ui;

public class AddCommand extends Command {

    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        storage.save(tasks.getAll());
        return ui.formatAdd(task, tasks.size());
    }
}
