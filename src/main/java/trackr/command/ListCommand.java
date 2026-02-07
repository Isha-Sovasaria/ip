package trackr.command;

import trackr.task.TaskList;
import trackr.Storage;
import trackr.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks.getAll());
    }
}
