package memz.commands;

import memz.tasks.TaskList;
import memz.ui.Ui;
import memz.Storage;
import memz.exceptions.MemzException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws MemzException;

    public boolean isExit() {
        return false;
    }
}