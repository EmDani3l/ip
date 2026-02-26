package memz.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task that must be completed by a specific date or time.
 */
public class Deadline extends Task {
    protected String by;
    protected LocalDate dateBy;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        try {
            // Attempt to parse as a date
            this.dateBy = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            // If not in yyyy-mm-dd, treat it as a string
            this.dateBy = null;
        }
    }

    @Override
    public String toString() {
        String displayBy = (dateBy != null)
                ? dateBy.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                : by;
        return "[D]" + super.toString() + " (by: " + displayBy + ")";
    }

    @Override
    public String toSaveFormat() {
        return "D | " + super.toSaveFormat() + " | " + by;
    }
}
