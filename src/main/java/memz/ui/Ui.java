package memz.ui;

public class Ui {
    // Normal behaviour constants
    public static final String LINE = "___________________________________________________________";
    public static final String TASK_ADDED_MESSAGE = "Got it. I've added this task:\n  ";
    public static final String LIST_MESSAGE = "Here are your tasks. Stop procrastinating:";
    public static final String MARK_TASK_MESSAGE = "Fine. You can have this one:\n  ";
    public static final String UNMARK_TASK_MESSAGE = "SEE, you're not done yet:\n  ";
    public static final String BYE_MESSAGE = "Bye. You better watch out.\n";
    public static final String LOGO = "Hello from\n" +
            " __  __                      \n" +
            "|  \\/  | ___ _ __ ___  ____ \n" +
            "| |\\/| |/ _ \\ '_ ` _ \\|_  / \n" +
            "| |  | |  __/ | | | | |/ /  \n" +
            "|_|  |_|\\___|_| |_| |_/___| \n" +
            "Want a cookie? Dowan give then how?\n";

    // Formatting constants
    public static final String PROPER_TODO_FORMAT = "Correct format: todo <description>";
    public static final String PROPER_DEADLINE_FORMAT = "Correct format: deadline <description> /by <time>";
    public static final String PROPER_EVENT_FORMAT = "Correct format: event <description> /from <start> /to <end>";

    // Overall format constant
    public static final String PROPER_OVERALL_FORMAT = "Please follow the given input format:\n"
            + PROPER_TODO_FORMAT
            + " | "
            + PROPER_DEADLINE_FORMAT
            + " | "
            + PROPER_EVENT_FORMAT;

    // Error message constants
    public static final String ERROR_UNKNOWN_COMMAND = "OI. I don't know what that means.\n";
    public static final String ERROR_EMPTY_TODO = "Stop pretending to do nothing.\n";
    public static final String ERROR_EMPTY_DEADLINE = "memz.tasks.Deadline cannot not exist.\n";
    public static final String ERROR_MISSING_BY = "You ain't running from this one.\n";
    public static final String ERROR_EMPTY_EVENT = "Events are not imaginary.\n";
    public static final String ERROR_MISSING_FROM_TO = "An event only begins if it ends and only ends if it begins.\n";
    public static final String ERROR_INVALID_NUMBER = "Do you know what a number is??\nExample: mark 1";
    public static final String ERROR_INDEX_OUT_OF_BOUNDS = "I know you are not THAT busy. Pick a task you actually have.";
}