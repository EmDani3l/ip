package memz.commands;

import memz.tasks.TaskList;
import memz.ui.Ui;
import memz.Storage;
import memz.exceptions.MemzException;

/**
 * Represents an executable action triggered by a user command.
 * All specific command types must inherit from this and implement the execute method.
 */
public abstract class Command {
    /**
     * Executes the logic specific to the command, such as adding a task or listing them.
     * * @param tasks   The list of tasks to operate upon.
     * @param ui      The user interface for displaying feedback.
     * @param storage The storage handler for saving changes to the disk.
     * @throws MemzException If execution fails due to logic or input errors.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws MemzException;

    /**
     * Indicates whether this command should terminate the application loop.
     * * @return True if the program should exit, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}