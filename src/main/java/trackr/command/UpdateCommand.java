package trackr.command;

import java.time.LocalDate;
import java.util.ArrayList;

import trackr.Storage;
import trackr.TrackrException;
import trackr.Ui;
import trackr.task.Deadline;
import trackr.task.Event;
import trackr.task.Task;
import trackr.task.TaskList;

public class UpdateCommand extends Command {

    private final int index;
    private final String updatePart;

    public UpdateCommand(int index, String updatePart) {
        this.index = index;
        this.updatePart = updatePart;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage)
            throws TrackrException {

        assert tasks != null : "Task list should not be null.";
        assert storage != null : "Storage should not be null.";

        if (index < 0 || index >= tasks.size()) {
            throw new TrackrException("Task index out of range.");
        }

        Task task = tasks.get(index);
        assert task != null : "Retrieved task should not be null.";

        if (updatePart.startsWith("/desc ")) {
            String newDesc = updatePart.substring(6);
            task.setDescription(newDesc);

        } else if (updatePart.startsWith("/by ") && task instanceof Deadline) {
            String newDate = updatePart.substring(4);
            LocalDate date = LocalDate.parse(newDate);
            ((Deadline) task).setBy(date);

        } else if (updatePart.contains("/from ") && task instanceof Event) {
            String[] parts = updatePart.split(" /to ");

            if (parts.length != 2) {
                throw new TrackrException("Event must have both /from and /to.");
            }

            String fromPart = parts[0].substring(6);
            String toPart = parts[1];

            ((Event) task).setFrom(LocalDate.parse(fromPart));
            ((Event) task).setTo(LocalDate.parse(toPart));

        } else {
            throw new TrackrException("Invalid update field for this task type.");
        }

        storage.save(tasks.getAll());
        return "Update succesful";
    }

}
