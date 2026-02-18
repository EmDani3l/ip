package memz.tasks;

/**
 * Represents a basic task without any date or time constraints.
 */
public class Todo extends Task {

    /**
     * Constructs a new memz.tasks.Todo task with the specified description.
     * Initial status is set to not done.
     * @param description The textual description of what needs to be done.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSaveFormat() {
        return "T | " + super.toSaveFormat();
    }
}
