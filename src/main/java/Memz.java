import java.util.Scanner;

public class Memz {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        String logo = "____________________________________________________________\n"
                + "Hello! I'm Memz!\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(logo);

        while (true) {
            String input = in.nextLine();
            System.out.println("___________________________________________________________\n");

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n"
                + "___________________________________________________________");
                break;
            } else {
                System.out.println(input);
                System.out.println("___________________________________________________________");
            }
        }
    }
}