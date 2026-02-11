package quzee.command;

import java.util.ArrayList;
import java.util.List;

import quzee.Storage;
import quzee.Ui;
import quzee.task.Task;

/**
 * Represents a command to find matching tasks in the task list.
 */
public class FindCommand extends Command {

    private final String keyword;

    /**
     * Constructs a FindCommand with the keyword.
     * @param keyword The keyword to find for in the tasksList.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(List<Task> tasksList, Ui ui, Storage storage) {
        List<Task> matchingTasks = new ArrayList<>();

        for (Task task : tasksList) {
            if (task.toString().contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        return ui.showMatchingTasksList(matchingTasks);
    }
}
