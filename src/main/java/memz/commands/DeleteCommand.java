package memz.commands;

import memz.tasks.TaskList;
import memz.tasks.Task;
import memz.ui.Ui;
import memz.Storage;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task deleted = tasks.delete(index);
        ui.showTaskDeleted(deleted, tasks.size());
    }
}