import java.util.Scanner;

/**
 * The main class of the Memz chatbot application.
 * Handles user interaction, input parsing, and task management.
 */
public class Memz {
    private static final Task[] tasks = new Task[100];
    private static int taskCount = 0;

    /**
     * The entry point of the application.
     * Runs the main event loop, reading user input and delegating commands
     * to specific handler methods until the "bye" command is received.
     * Handles top-level exceptions for invalid inputs and execution errors.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        printGreeting();

        while (true) {
            String input = in.nextLine();
            System.out.println(Ui.LINE + "\n");

            try {
                if (input.equals("bye")) {
                    printExit();
                    break;
                } else if (input.equals("list")) {
                    printList();
                } else if (input.startsWith("mark")) {
                    handleMark(input, true);
                } else if (input.startsWith("unmark")) {
                    handleMark(input, false);
                } else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
                    handleAddTask(input);
                } else {
                    throw new MemzException(Ui.ERROR_UNKNOWN_COMMAND + Ui.PROPER_OVERALL_FORMAT);
                }
            } catch (MemzException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(Ui.ERROR_INVALID_NUMBER);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(Ui.ERROR_INDEX_OUT_OF_BOUNDS);
            }
            System.out.println(Ui.LINE);
        }
    }

    /**
     * Displays the welcome message and the Memz logo to the standard output.
     */
    private static void printGreeting() {
        System.out.println(Ui.LOGO);
    }

    /**
     * Prints a confirmation message indicating a task has been successfully added.
     * Also displays the current total number of tasks in the list.
     *
     * @param task The task object that was just added.
     */
    private static void printAddSuccess(Task task) {
        System.out.println(Ui.TASK_ADDED_MESSAGE + task + "\nNow you have " + taskCount + " tasks in the list.");
    }

    /**
     * Displays the exit message to the standard output.
     */
    private static void printExit() {
        System.out.println(Ui.BYE_MESSAGE + Ui.LINE);
    }

    /**
     * Parses the user input to create and add a specific type of task (Todo, Deadline, or Event).
     * Validates the input format and throws an exception if the format is incorrect
     * or if required arguments (like /by, /from, /to) are missing.
     *
     * @param input The raw command line string entered by the user.
     * @throws MemzException If the command format is invalid or arguments are missing.
     */
    private static void handleAddTask(String input) throws MemzException {
        if (input.startsWith("todo")) {
            // Check if description is empty
            if (input.length() <= 5) {
                throw new MemzException(Ui.ERROR_EMPTY_TODO + Ui.PROPER_TODO_FORMAT);
            }
            tasks[taskCount++] = new Todo(input.substring(5));
            printAddSuccess(tasks[taskCount - 1]);
        } else if (input.startsWith("deadline")) {
            // Check for empty description or missing /by
            if (input.length() <= 9) {
                throw new MemzException(Ui.ERROR_EMPTY_DEADLINE + Ui.PROPER_DEADLINE_FORMAT);
            }
            if (!input.contains(" /by ")) {
                throw new MemzException(Ui.ERROR_MISSING_BY + Ui.PROPER_DEADLINE_FORMAT);
            }

            String[] parts = input.substring(9).split(" /by ");
            if (parts.length < 2) { // Added this check
                throw new MemzException(Ui.ERROR_MISSING_BY + Ui.PROPER_DEADLINE_FORMAT);
            }
            tasks[taskCount++] = new Deadline(parts[0], parts[1]);
            printAddSuccess(tasks[taskCount - 1]);

        } else if (input.startsWith("event")) {
            // Check for empty description or missing tags
            if (input.length() <= 6) {
                throw new MemzException(Ui.ERROR_EMPTY_EVENT + Ui.PROPER_EVENT_FORMAT);
            }
            if (!input.contains(" /from ") || !input.contains(" /to ")) {
                throw new MemzException(Ui.ERROR_MISSING_FROM_TO + Ui.PROPER_EVENT_FORMAT);
            }

            String[] parts = input.substring(6).split(" /from | /to ");
            // Extra check to ensure we actually got 3 parts
            if (parts.length < 3) {
                throw new MemzException(Ui.ERROR_MISSING_FROM_TO + Ui.PROPER_EVENT_FORMAT);
            }

            tasks[taskCount++] = new Event(parts[0], parts[1], parts[2]);
            printAddSuccess(tasks[taskCount - 1]);
        }
    }

    /**
     * Iterates through the list of stored tasks and prints them to the standard output.
     * Displays the tasks with their index, type, status, and description.
     */
    private static void printList() {
        System.out.println(Ui.LIST_MESSAGE);
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + "." + tasks[i]);
        }
    }

    /**
     * Parses the user input to mark or unmark a specific task.
     * Updates the task's status and prints a confirmation message.
     *
     * @param input  The raw command containing the task index (e.g., "mark 1").
     * @param isDone True to mark the task as done, false to unmark it.
     * @throws NumberFormatException     If the provided index is not a valid integer.
     * @throws IndexOutOfBoundsException If the provided index is out of range of the current task list.
     */
    private static void handleMark(String input, boolean isDone) {
        int spaceIdx = isDone ? 5 : 7;

        if (spaceIdx >= input.length()) {
            throw new NumberFormatException(); // Force it to the "Do you know what a number is" error
        }

        // If parseInt fails, the catch(NumberFormatException) in main picks it up
        // If idx is invalid, the catch(IndexOutOfBoundsException) in main picks it up
        int idx = Integer.parseInt(input.substring(spaceIdx)) - 1;

        if (idx < 0 || idx >= taskCount) {
            throw new IndexOutOfBoundsException(); // Manually throw if number is valid but out of range
        }

        if (isDone) {
            tasks[idx].markAsDone();
            System.out.println(Ui.MARK_TASK_MESSAGE + tasks[idx]);
        } else {
            tasks[idx].unmarkDone();
            System.out.println(Ui.UNMARK_TASK_MESSAGE + tasks[idx]);
        }
    }
}