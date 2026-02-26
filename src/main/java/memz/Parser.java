package memz;

import memz.exceptions.MemzException;
import memz.tasks.*;
import memz.ui.Ui;
import memz.commands.*;

/**
 * Contains logic to interpret raw user input strings and convert them into
 * executable {@code Command} objects.
 */
public class Parser {

    /**
     * Analyzes the user's input string to determine which command to create.
     * * @param input The full raw string entered by the user.
     * @return A specific {@code Command} subclass (e.g., AddCommand, DeleteCommand).
     * @throws MemzException If the command word is unknown or the format is invalid.
     */
    public static Command parse(String input) throws MemzException {
        String commandWord = input.split(" ")[0];

        switch (commandWord) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(parseIndex(input), true);
        case "unmark":
            return new MarkCommand(parseIndex(input), false);
        case "delete":
            return new DeleteCommand(parseIndex(input));
        case "todo":
            String todoDesc = check(input, 5, Ui.ERROR_EMPTY_TODO + Ui.PROPER_TODO_FORMAT);
            return new AddCommand(new Todo(todoDesc));
        case "deadline":
            return new AddCommand(parseDeadline(input));
        case "event":
            return new AddCommand(parseEvent(input));
        case "find":
            String keyword = check(input, 5, "Please enter a keyword to find!");
            return new FindCommand(keyword.trim());
        default:
            throw new MemzException(Ui.ERROR_UNKNOWN_COMMAND + Ui.PROPER_OVERALL_FORMAT);
        }
    }

    private static void addTask(Task t, TaskList tasks, Ui ui) {
        tasks.add(t);
        ui.showTaskAdded(t, tasks.size());
    }

    private static int parseIndex(String input) {
        int spaceIdx = input.indexOf(' ');

        if (spaceIdx == -1 || spaceIdx + 1 >= input.length()) {
            throw new NumberFormatException();
        }

        return Integer.parseInt(input.substring(spaceIdx + 1).trim()) - 1;
    }

    private static String check(String input, int minLen, String errorMsg) throws MemzException {
        if (input.length() <= minLen) {
            throw new MemzException(errorMsg);
        }
        return input.substring(minLen);
    }

    /**
     * Extracts the date/time information for a Deadline task.
     * * @param input The raw input string containing the "/by" delimiter.
     * @return A {@code Deadline} task object.
     * @throws MemzException If the "/by" keyword is missing or description is empty.
     */
    private static Task parseDeadline(String input) throws MemzException {
        String content = check(input, 9, Ui.ERROR_EMPTY_DEADLINE + Ui.PROPER_DEADLINE_FORMAT);

        if (!content.contains(" /by ")) {
            throw new MemzException(Ui.ERROR_MISSING_BY + Ui.PROPER_DEADLINE_FORMAT);
        }

        String[] parts = content.split(" /by ");
        if (parts.length < 2) {
            throw new MemzException(Ui.ERROR_MISSING_BY + Ui.PROPER_DEADLINE_FORMAT);
        }
        return new Deadline(parts[0], parts[1]);
    }

    /**
     * Extracts the date/time information for an Event task.
     * * @param input The raw input string containing the "/from" and "to" delimiter.
     * @return A {@code Event} task object.
     * @throws MemzException If the "/from" and/or "/to" keyword is missing or description is empty.
     */
    private static Task parseEvent(String input) throws MemzException {
        String content = check(input, 6, Ui.ERROR_EMPTY_EVENT + Ui.PROPER_EVENT_FORMAT);

        if (!content.contains(" /from ") || !content.contains(" /to ")) {
            throw new MemzException(Ui.ERROR_MISSING_FROM_TO + Ui.PROPER_EVENT_FORMAT);
        }

        String[] parts = content.split(" /from | /to ");
        if (parts.length < 3) {
            throw new MemzException(Ui.ERROR_MISSING_FROM_TO + Ui.PROPER_EVENT_FORMAT);
        }
        return new Event(parts[0], parts[1], parts[2]);
    }
}