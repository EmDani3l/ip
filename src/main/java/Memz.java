import java.util.Scanner;

public class Memz {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.println("Hello from");
        System.out.println(" __  __                      ");
        System.out.println("|  \\/  | ___ _ __ ___  ____ ");
        System.out.println("| |\\/| |/ _ \\ '_ ` _ \\|_  / ");
        System.out.println("| |  | |  __/ | | | | |/ /  ");
        System.out.println("|_|  |_|\\___|_| |_| |_/___| ");
        System.out.println("Want a cookie? Dowan give then how?\n");

        Task[] tasks = new Task[100];
        int taskCount = 0;

        while (true) {
            String input = in.nextLine();
            System.out.println("___________________________________________________________\n");

            if (input.equals("bye")) {
                //exit condition
                System.out.println("Bye. You better watch out.\n"
                        + "___________________________________________________________");
                break;
            } else if (input.equals("list")) {
                System.out.println("Here are your tasks. Stop procrastinating:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i].toString());
                }
            } else if (input.startsWith("mark")) {
                //set a task as done
                int idx = Integer.parseInt(input.substring(5)) - 1;
                tasks[idx].markAsDone();
                System.out.println("Fine. You can have this one:");
                System.out.println("  " + tasks[idx]);
            } else if (input.startsWith("unmark")) {
                //set a task as not being done
                int idx = Integer.parseInt(input.substring(7)) - 1;
                tasks[idx].unmarkDone();
                System.out.println("SEE, you're not done yet:");
                System.out.println("  " + tasks[idx]);
            } else if (input.startsWith("todo")) {
                String description = input.substring(5);
                tasks[taskCount] = new Todo(description);
                taskCount++;
                System.out.println("Wah think u big isit. I add:\n " + tasks[taskCount - 1] + "\n Now you have " + taskCount + " tasks in the list.");
            } else if (input.startsWith("deadline")) {
                String[] parts = input.substring(9).split(" /by ");
                tasks[taskCount] = new Deadline(parts[0], parts[1]);
                taskCount++;
                System.out.println("Careful ah:\n" + tasks[taskCount -1] + "\n Now you have " + taskCount + " tasks in the list.");
            } else if (input.startsWith("event")) {
                String[] parts = input.substring(6).split(" /from | /to ");
                tasks[taskCount] = new Event(parts[0], parts[1], parts[2]);
                taskCount++;
                System.out.println("Bye time:\n" + tasks[taskCount -1] + "\n Now you have " + taskCount + " tasks in the list.");
            } else {
                System.out.println("TF did u just say? Try again.");
            }
            System.out.println("___________________________________________________________");
        }
    }
}