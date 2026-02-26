package memz.commands;

import memz.tasks.TaskList;
import memz.ui.Ui;
import memz.Storage;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}