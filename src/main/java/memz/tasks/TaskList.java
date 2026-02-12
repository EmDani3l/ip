package memz.tasks;

/**
 * Manages the list of tasks.
 * Handles adding, retrieving, and updating tasks.
 */
public class TaskList {
    private final Task[] tasks;
    private int taskCount;

    public TaskList() {
        this.tasks = new Task[100];
        this.taskCount = 0;
    }

    public void add(Task task) {
        tasks[taskCount++] = task;
    }

    public Task get(int index) {
        return tasks[index];
    }

    public int size() {
        return taskCount;
    }

    /**
     * Marks a task as done based on index.
     * @param index The zero-based index of the task.
     */
    public void mark(int index) {
        tasks[index].markAsDone();
    }

    /**
     * Unmarks a task based on index.
     * @param index The zero-based index of the task.
     */
    public void unmark(int index) {
        tasks[index].unmarkDone();
    }
}