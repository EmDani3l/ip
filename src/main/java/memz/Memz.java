package memz;

import memz.exceptions.MemzException;
import memz.tasks.TaskList;
import memz.ui.Ui;

/**
 * Handles user interaction, input parsing, and task management.
 */
public class Memz {
    private final Ui ui;
    private final TaskList tasks;

    public Memz() {
        this.ui = new Ui();
        this.tasks = new TaskList();
    }

    /**
     * Runs the main event loop, reading user input and delegating commands
     * to specific handler methods until the "bye" command is received.
     * Handles top-level exceptions for invalid inputs and execution errors.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Memz().run();
    }

    public void run() {
        ui.showWelcome();
        boolean isRunning = true;

        while (isRunning) {
            String input = ui.readCommand();
            ui.showLine();
            System.out.println();

            try {
                isRunning = Parser.parseAndExecute(input, tasks, ui);
            } catch (MemzException e) {
                ui.showError(e.getMessage());
            } catch (NumberFormatException e) {
                ui.showError(Ui.ERROR_INVALID_NUMBER);
            } catch (IndexOutOfBoundsException e) {
                ui.showError(Ui.ERROR_INDEX_OUT_OF_BOUNDS);
            }
            ui.showLine();
        }
    }
}