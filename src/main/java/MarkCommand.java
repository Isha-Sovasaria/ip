public class MarkCommand extends Command {

    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws TrackrException {

        if (index < 0 || index >= tasks.size()) {
            throw new TrackrException("That task number does not exist.");
        }

        Task task = tasks.get(index);
        task.markAsDone();
        storage.save(tasks.getAll());
        ui.showMark(task);
    }
}
