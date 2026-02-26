package memz.commands;

import memz.tasks.TaskList;
import memz.ui.Ui;
import memz.Storage;
import memz.exceptions.MemzException;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
