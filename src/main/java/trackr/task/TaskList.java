package trackr.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks in the application.
 * Provides methods to manage tasks in the list.
 */
public class TaskList {

    private final ArrayList<Task> tasks;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a task list using an existing list of tasks.
     *
     * @param tasks The list of tasks to initialize with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the task list at the specified index.
     *
     * @param index The index of the task to be removed.
     * @return The removed task.
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The total number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the list containing all tasks.
     *
     * @return The list of all tasks.
     */
    public ArrayList<Task> getAll() {
        return tasks;
    }

    /**
     * Finds tasks whose descriptions contain the given keyword.
     *
     * @param keyword The keyword to search for.
     * @return A list of matching tasks.
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> matches = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matches.add(task);
            }
        }

        return matches;
    }
}
