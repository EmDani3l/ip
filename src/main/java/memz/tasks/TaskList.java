package memz.tasks;

import java.util.ArrayList;

/**
 * Manages the list of tasks.
 * Handles adding, deleting, retrieving, and updating tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;
    private int taskCount;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public void mark(int index) {
        tasks.get(index).markAsDone();
    }

    public void unmark(int index) {
        tasks.get(index).unmarkDone();
    }

    public Task delete(int index) {
        return tasks.remove(index);
    }
}