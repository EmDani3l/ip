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

        String[] items = new String[100];
        int listIndex = 1;

        while (true) {
            String input = in.nextLine();
            System.out.println("___________________________________________________________\n");

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n"
                + "___________________________________________________________");
                break;
            }
            else if (input.equals("list")) {
                for (int i = 1; i < listIndex; i++) {
                    System.out.println(i + ". " + items[i]);
                }
                System.out.println("___________________________________________________________");
            }
            else {
                System.out.println("added: " + input);
                items[listIndex] = input;
                listIndex++;
                System.out.println("___________________________________________________________");
            }
        }
    }
}