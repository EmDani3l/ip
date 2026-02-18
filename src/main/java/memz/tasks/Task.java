package memz.tasks;

/**
 * Represents a generic task with a description and completion status.
 * Parent class for specific task types like Todo, Deadline, Event.
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

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String toSaveFormat() {
        return (isDone ? "1" : "0") + " | " + description;
    }
}
