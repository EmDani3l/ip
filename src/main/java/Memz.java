import java.util.Scanner;

public class Memz {
    private static final Task[] tasks = new Task[100];
    private static int taskCount = 0;
    private static final String LINE = "___________________________________________________________";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        printGreeting();

        while (true) {
            String input = in.nextLine();
            System.out.println(LINE + "\n");

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
                System.out.println("TF did u just say? Try again.");
            }
            System.out.println(LINE);
        }
    }

    /**
     * Parses user input and adds the corresponding task type to the task list.
     * Supports "todo", "deadline", and "event" command formats.
     * * @param input The raw command line string entered by the user.
     */
    private static void handleAddTask(String input) {
        if (input.startsWith("todo")) {
            tasks[taskCount++] = new Todo(input.substring(5));
            System.out.println("Wah think u big isit. I add:\n " + tasks[taskCount - 1]
                    + "\n Now you have " + taskCount + " tasks in the list.");
        } else if (input.startsWith("deadline")) {
            //capture string after "by"
            String[] parts = input.substring(9).split(" /by ");
            tasks[taskCount++] = new Deadline(parts[0], parts[1]);
            System.out.println("Careful ah:\n" + tasks[taskCount - 1]
                    + "\n Now you have " + taskCount + " tasks in the list.");
        } else if (input.startsWith("event")) {
            //capture string between "from" and "to"
            String[] parts = input.substring(6).split(" /from | /to ");
            tasks[taskCount++] = new Event(parts[0], parts[1], parts[2]);
            System.out.println("Bye time:\n" + tasks[taskCount - 1]
                    + "\n Now you have " + taskCount + " tasks in the list.");
        }
    }

    private static void printList() {
        System.out.println("Here are your tasks. Stop procrastinating:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + "." + tasks[i]);
        }
    }

    /**
     * Changes the completion status of a task based on its index in the list.
     * * @param input The raw command containing the task index (e.g., "mark 1").
     * @param isDone True to mark as done, false to unmark.
     * @throws ArrayIndexOutOfBoundsException If the provided index is invalid.
     */
    private static void handleMark(String input, boolean isDone) {
        int spaceIdx = isDone ? 5 : 7;
        int idx = Integer.parseInt(input.substring(spaceIdx)) - 1;
        if (isDone) {
            tasks[idx].markAsDone();
            System.out.println("Fine. You can have this one:\n  " + tasks[idx]);
        } else {
            tasks[idx].unmarkDone();
            System.out.println("SEE, you're not done yet:\n  " + tasks[idx]);
        }
    }

    /**
     * Prints out a message with Memz logo to greet user
     */
    private static void printGreeting() {
        System.out.println("Hello from\n" +
                " __  __                      \n" +
                "|  \\/  | ___ _ __ ___  ____ \n" +
                "| |\\/| |/ _ \\ '_ ` _ \\|_  / \n" +
                "| |  | |  __/ | | | | |/ /  \n" +
                "|_|  |_|\\___|_| |_| |_/___| \n" +
                "Want a cookie? Dowan give then how?\n");
    }

    private static void printExit() {
        System.out.println("Bye. You better watch out.\n" + LINE);
    }
}