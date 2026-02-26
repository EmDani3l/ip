package memz.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected String from;
    protected String to;
    protected LocalDate dateFrom;
    protected LocalDate dateTo;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        try {
            this.dateFrom = LocalDate.parse(from);
        } catch (DateTimeParseException e) {
            this.dateFrom = null;
        }
        try {
            this.dateTo = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            this.dateTo = null;
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String f = (dateFrom != null) ? dateFrom.format(fmt) : from;
        String t = (dateTo != null) ? dateTo.format(fmt) : to;
        return "[E]" + super.toString() + " (from: " + f + " to: " + t + ")";
    }

    @Override
    public String toSaveFormat() {
        return "E | " + super.toSaveFormat() + " | " + from + " | " + to;
    }
}