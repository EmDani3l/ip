/**
 * Represents a basic task without any date or time constraints.
 * This class extends the {@link Task} class to provide specific
 * identifier labels for "Todo" items.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo task with the specified description.
     * Initial status is set to not done.
     *
     * @param description The textual description of what needs to be done.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of a Todo task.
     * Includes the [T] type identifier followed by the task's status and description.
     * Returns a formatted string suitable for display in the task list.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
