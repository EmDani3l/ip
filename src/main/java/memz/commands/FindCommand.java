package memz.commands;

import memz.tasks.TaskList;
import memz.tasks.Task;
import memz.ui.Ui;
import memz.Storage;
import java.util.ArrayList;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            if (t.getDescription().contains(keyword)) {
                matchingTasks.add(t);
            }
        }

        ui.showFoundTasks(matchingTasks);
    }
}