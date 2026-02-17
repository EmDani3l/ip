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

    /**
     * Marks a task as done based on index.
     * @param index The zero-based index of the task.
     */
    public void mark(int index) {
        tasks.get(index).markAsDone();
    }

    /**
     * Unmarks a task based on index.
     * @param index The zero-based index of the task.
     */
    public void unmark(int index) {
        tasks.get(index).unmarkDone();
    }

    /**
     * Deletes a task from the list based on index.
     * @param index The zero-based index of the task to delete.
     * @return The task that was removed.
     */
    public Task delete(int index) {
        return tasks.remove(index);
    }
}