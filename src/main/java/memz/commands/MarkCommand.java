package memz.commands;

import memz.tasks.TaskList;
import memz.ui.Ui;
import memz.Storage;
import memz.exceptions.MemzException;

public class MarkCommand extends Command {
    private final int index;
    private final boolean isMark;

    public MarkCommand(int index, boolean isMark) {
        this.index = index;
        this.isMark = isMark;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MemzException {
        if (isMark) {
            tasks.mark(index);
            ui.showMarked(tasks.get(index));
        } else {
            tasks.unmark(index);
            ui.showUnmarked(tasks.get(index));
        }
    }
}