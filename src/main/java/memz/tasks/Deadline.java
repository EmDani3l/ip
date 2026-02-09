package memz.tasks;

/**
 * Represents a task that must be completed by a specific date or time.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Initializes a new memz.tasks.Deadline task.
     * * @param description The content of the task.
     * @param by The date or time the task is due.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
