package memz.commands;

import memz.tasks.Task;
import memz.tasks.TaskList;
import memz.ui.Ui;
import memz.Storage;
import memz.exceptions.MemzException;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        ui.showTaskAdded(task, tasks.size());
    }
}
