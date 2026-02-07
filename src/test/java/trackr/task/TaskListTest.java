package trackr.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    @Test
    public void addTask_increasesSize() {
        TaskList tasks = new TaskList();
        Task task = new ToDo("read book");

        tasks.add(task);

        assertEquals(1, tasks.size());
        assertEquals(task, tasks.get(0));
    }

    @Test
    public void removeTask_returnsCorrectTask() {
        TaskList tasks = new TaskList();
        Task t1 = new ToDo("task one");
        Task t2 = new ToDo("task two");

        tasks.add(t1);
        tasks.add(t2);

        Task removed = tasks.remove(0);

        assertEquals(t1, removed);
        assertEquals(1, tasks.size());
        assertEquals(t2, tasks.get(0));
    }

    @Test
    public void getTask_returnsCorrectTask() {
        TaskList tasks = new TaskList();
        Task task = new ToDo("watch lecture");

        tasks.add(task);

        assertEquals(task, tasks.get(0));
    }

    @Test
    public void size_reflectsNumberOfTasks() {
        TaskList tasks = new TaskList();

        assertEquals(0, tasks.size());

        tasks.add(new ToDo("a"));
        tasks.add(new ToDo("b"));

        assertEquals(2, tasks.size());
    }
}
