package quzee.command;

import java.util.List;

import quzee.Storage;
import quzee.Ui;
import quzee.task.Task;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {

    private final int index;

    /**
     * Constructs a DeleteCommand for the task to be deleted at the specified index.
     * @param index The zero-based index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(List<Task> tasksList, Ui ui, Storage storage) {
        int oldSize = tasksList.size();
        Task toBeDeleted = tasksList.get(index);
        tasksList.remove(index);
        assert oldSize + 1 == tasksList.size(): "Size of tasksList did not decrease after the deletion of task";
        return ui.showTaskDeletedMessage(toBeDeleted, tasksList.size());
    }
}
