package memz;

import memz.exceptions.MemzException;
import memz.tasks.*;
import memz.ui.Ui;
import memz.commands.*;

/**
 * Interprets user strings and executes the corresponding actions.
 */
public class Parser {

    /**
     * Parses the command word and executes the logic for task operations.
     * @param input Raw user input.
     * @param tasks List to be modified.
     * @param ui    UI for printing results.
     * @return      False if "bye" is called, true otherwise.
     * @throws MemzException If command format or details are invalid.
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