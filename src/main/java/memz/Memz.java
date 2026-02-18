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
    private final Storage storage;

    public Memz() {
        this.ui = new Ui();
        this.tasks = new TaskList();
        this.storage = new Storage("data/memz.txt");
        this.storage.load(tasks);
    }

    /**
     * Starts the application and runs the main event loop.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Memz().run();
    }

    /**
     * Handles the continuous cycle of reading, parsing, and saving user commands.
     */
    public void run() {
        ui.showWelcome();
        boolean isRunning = true;

        while (isRunning) {
            String input = ui.readCommand();
            ui.showLine();
            System.out.println();

            try {
                isRunning = Parser.parseAndExecute(input, tasks, ui);
                storage.save(tasks);
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