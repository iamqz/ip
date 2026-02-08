package quzee.command;

import java.util.ArrayList;
import java.util.List;

import quzee.Storage;
import quzee.Ui;
import quzee.task.Task;

public class FindCommand extends Command {

    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(List<Task> tasksList, Ui ui, Storage storage) {
        List<Task> matchingTasks = new ArrayList<>();

        for (Task task : tasksList) {
            if(task.toString().contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        ui.showTasksList(matchingTasks);
    }
}
