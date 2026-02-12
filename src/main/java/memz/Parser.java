package memz;

import memz.exceptions.MemzException;
import memz.tasks.*;
import memz.ui.Ui;

/**
 * Handles the logic of parsing user commands.
 */
public class Parser {

    /**
     * Parses the user input and executes the corresponding command.
     *
     * @param input The raw command line string entered by the user.
     * @param tasks The TaskList containing the tasks.
     * @param ui    The Ui to deal with user interactions.
     * @return True if the program should continue running, False if it should exit.
     * @throws MemzException             If the command format is invalid.
     * @throws NumberFormatException     If a number argument is invalid.
     * @throws IndexOutOfBoundsException If a task index is invalid.
     */
    public static boolean parseAndExecute(String input, TaskList tasks, Ui ui) throws MemzException {
        // Extract the command word (first word) to determine the action
        String command = input.split(" ")[0];

        switch (command) {
        case "bye":
            ui.showExit();
            return false;

        case "list":
            ui.showTaskList(tasks);
            return true;

        case "mark":
            handleMark(input, true, tasks, ui);
            return true;

        case "unmark":
            handleMark(input, false, tasks, ui);
            return true;

        case "todo":
        case "deadline":
        case "event":
            handleAddTask(command, input, tasks, ui);
            return true;

        default:
            throw new MemzException(Ui.ERROR_UNKNOWN_COMMAND + Ui.PROPER_OVERALL_FORMAT);
        }
    }

    /**
     * Parses user input and adds the corresponding task type to the task list.
     * Delegates the creation of specific task objects to helper methods.
     *
     * @param command The command type (todo, deadline, event).
     * @param input   The raw command string.
     * @param tasks   The list of tasks to add to.
     * @param ui      The UI to print success messages.
     * @throws MemzException If the task cannot be parsed due to formatting errors.
     */
    private static void handleAddTask(String command, String input, TaskList tasks, Ui ui) throws MemzException {
        Task newTask;

        switch (command) {
        case "todo":
            if (input.length() <= 5) {
                throw new MemzException(Ui.ERROR_EMPTY_TODO + Ui.PROPER_TODO_FORMAT);
            }
            newTask = new Todo(input.substring(5));
            break;

        case "deadline":
            newTask = parseDeadline(input);
            break;

        case "event":
            newTask = parseEvent(input);
            break;

        default:
            // This should logically never happen due to the switch in parseAndExecute
            throw new MemzException(Ui.ERROR_UNKNOWN_COMMAND);
        }

        tasks.add(newTask);
        ui.showTaskAdded(newTask, tasks.size());
    }

    /**
     * Extracts details from an "event" command and creates a new Event object.
     * Validates that the input contains the required "/from" and "/to" tags and
     * that the description is not empty.
     *
     * @param input The full command string (e.g., "event meeting /from Mon /to Fri").
     * @return An Event object containing the description, start time, and end time.
     * @throws MemzException If the description is empty, tags are missing, or the format is incorrect.
     */
    private static Task parseEvent(String input) throws MemzException {
        if (input.length() <= 6) {
            throw new MemzException(Ui.ERROR_EMPTY_EVENT + Ui.PROPER_EVENT_FORMAT);
        }
        if (!input.contains(" /from ") || !input.contains(" /to ")) {
            throw new MemzException(Ui.ERROR_MISSING_FROM_TO + Ui.PROPER_EVENT_FORMAT);
        }

        // Split by the delimiters to get Description, From, and To parts
        String[] parts = input.substring(6).split(" /from | /to ");
        if (parts.length < 3) {
            throw new MemzException(Ui.ERROR_MISSING_FROM_TO + Ui.PROPER_EVENT_FORMAT);
        }
        return new Event(parts[0], parts[1], parts[2]);
    }

    /**
     * Extracts details from a "deadline" command and creates a new Deadline object.
     * Validates that the input contains the required "/by" tag and that the
     * description is not empty.
     *
     * @param input The full command string (e.g., "deadline submit report /by Monday").
     * @return A Deadline object containing the description and due date.
     * @throws MemzException If the description is empty, the "/by" tag is missing, or the format is incorrect.
     */
    private static Task parseDeadline(String input) throws MemzException {
        if (input.length() <= 9) {
            throw new MemzException(Ui.ERROR_EMPTY_DEADLINE + Ui.PROPER_DEADLINE_FORMAT);
        }
        if (!input.contains(" /by ")) {
            throw new MemzException(Ui.ERROR_MISSING_BY + Ui.PROPER_DEADLINE_FORMAT);
        }

        String[] parts = input.substring(9).split(" /by ");
        if (parts.length < 2) {
            throw new MemzException(Ui.ERROR_MISSING_BY + Ui.PROPER_DEADLINE_FORMAT);
        }
        return new Deadline(parts[0], parts[1]);
    }

    /**
     * Changes the completion status of a task based on its index in the list.
     */
    private static void handleMark(String input, boolean isDone, TaskList tasks, Ui ui) {
        int spaceIdx = isDone ? 5 : 7;

        if (spaceIdx >= input.length()) {
            throw new NumberFormatException();
        }

        int idx = Integer.parseInt(input.substring(spaceIdx)) - 1;

        if (idx < 0 || idx >= tasks.size()) {
            throw new IndexOutOfBoundsException();
        }

        if (isDone) {
            tasks.mark(idx);
            ui.showMarked(tasks.get(idx));
        } else {
            tasks.unmark(idx);
            ui.showUnmarked(tasks.get(idx));
        }
    }
}