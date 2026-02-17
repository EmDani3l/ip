package memz.tasks;

/**
 * Represents a generic task with a description and completion status.
 * Acts as the parent class for specific task types like memz.tasks.Todo, memz.tasks.Deadline, and memz.tasks.Event.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        //Change to [X] if marked as done, otherwise leave blank
        return (isDone ? "X" : " ");
    }

    /**
     * Updates the task status to done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Updates the task status to not done.
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task, including its status icon and description.
     * Returns a formatted string of the task status.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String toSaveFormat() {
        return (isDone ? "1" : "0") + " | " + description;
    }
}
