package memz;

import memz.exceptions.MemzException;
import memz.tasks.*;
import memz.ui.Ui;

public class Parser {

    public static boolean parseAndExecute(String input, TaskList tasks, Ui ui) throws MemzException {
        String command = input.split(" ")[0];

        switch (command) {
        case "bye":
            ui.showExit();
            return false;

        case "list":
            ui.showTaskList(tasks);
            break;

        case "mark":
            tasks.mark(parseIndex(input));
            ui.showMarked(tasks.get(parseIndex(input)));
            break;

        case "unmark":
            tasks.unmark(parseIndex(input));
            ui.showUnmarked(tasks.get(parseIndex(input)));
            break;

        case "delete":
            Task deleted = tasks.delete(parseIndex(input));
            ui.showTaskDeleted(deleted, tasks.size());
            break;

        case "todo":
            String todoDesc = check(input, 5, Ui.ERROR_EMPTY_TODO + Ui.PROPER_TODO_FORMAT);
            add(new Todo(todoDesc), tasks, ui);
            break;

        case "deadline":
            add(parseDeadline(input), tasks, ui);
            break;

        case "event":
            add(parseEvent(input), tasks, ui);
            break;

        default:
            throw new MemzException(Ui.ERROR_UNKNOWN_COMMAND + Ui.PROPER_OVERALL_FORMAT);
        }
        return true;
    }

    private static void add(Task t, TaskList tasks, Ui ui) {
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