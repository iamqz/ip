package quzee.command;

import java.util.List;

import quzee.Storage;
import quzee.Ui;
import quzee.task.Task;

/**
 * Represents a command to add a new task to the task list.
 */
public class AddCommand extends Command {

    private final Task task;

    /**
     * Constructs an AddCommand with the specific task.
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(List<Task> tasksList, Ui ui, Storage storage) {
        int oldSize = tasksList.size();
        tasksList.add(task);
        assert oldSize + 1 == tasksList.size() : "Size of tasksList did not increase after the addition of task";
        return ui.showTaskAddedMessage(task, tasksList.size());
    }
}
