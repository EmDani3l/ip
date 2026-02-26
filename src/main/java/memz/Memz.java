package memz;

import memz.exceptions.MemzException;
import memz.tasks.TaskList;
import memz.ui.Ui;
import memz.commands.*;

/**
 * Handles user interaction, input parsing, and task management.
 */
public class Memz {
    private final Ui ui;
    private final TaskList tasks;
    private final Storage storage;

    public Memz(String filePath) {
        this.ui = new Ui();
        this.tasks = new TaskList();
        this.storage = new Storage(filePath);
        this.storage.load(tasks);
    }

    /**
     * Starts the application and runs the main event loop.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Memz("data/memz.txt").run();
    }

    /**
     * Handles the continuous cycle of reading, parsing, and saving user commands.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            String input = ui.readCommand();
            ui.showLine();
            System.out.println();

            try {
                Command c = Parser.parse(input); // Step 1: Parse
                c.execute(tasks, ui, storage);   // Step 2: Execute
                isExit = c.isExit();             // Step 3: Check exit status
                storage.save(tasks);             // Step 4: Save
            } catch (MemzException | NumberFormatException | IndexOutOfBoundsException e) {
                ui.showError(e.getMessage());
            } catch (java.time.format.DateTimeParseException e) {
                ui.showError("Invalid date format! Please use yyyy-mm-dd (e.g., 2024-12-25).");
            }
            ui.showLine();
        }
    }
}