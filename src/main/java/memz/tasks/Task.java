package memz.tasks;

/**
 * Represents a generic task with a description and completion status.
 * This class serves as the parent for specific task types like Todo, Deadline, and Event.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Initializes a task with the given description.
     * The task is marked as not done by default.
     * @param description The textual description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a visual representation of the task's completion status.
     * @return "X" if the task is completed, otherwise a blank space.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Formats the task's data into a specific string format for file storage.
     * The format used is "status | description" (e.g., "1 | read book").
     * @return A formatted string suitable for saving to the hard disk.
     */
    public String toSaveFormat() {
        return (isDone ? "1" : "0") + " | " + description;
    }
}
