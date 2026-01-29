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
                System.out.println("Bye. You better watch out.\n"
                + "___________________________________________________________");
                break;
            }
            else if (input.equals("list")) {
                System.out.println("Here are your tasks. Stop procrastinating:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i].toString());
                }
            }
            else if (input.startsWith("mark")) {
                int idx = Integer.parseInt(input.substring(5)) - 1;
                tasks[idx].markAsDone();
                System.out.println("Fine. You can have this one:");
                System.out.println("  " + tasks[idx]);
            }
            else if (input.startsWith("unmark")) {
                int idx = Integer.parseInt(input.substring(7)) - 1;
                tasks[idx].unmarkDone();
                System.out.println("SEE, you're not done yet:");
                System.out.println("  " + tasks[idx]);
            }
            else {
                tasks[taskCount] = new Task(input);
                taskCount++;
                System.out.println("added: " + input);
            }
            System.out.println("___________________________________________________________");
        }
    }
}