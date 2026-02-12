package quzee.command;

import java.util.List;

import quzee.Storage;
import quzee.Ui;
import quzee.task.Task;

/**
 * Represents a command to mark a specific task as completed.
 */
public class MarkCommand extends Command {

    private final int index;

    /**
     * Constructs a MarkCommand for the task to be marked at the specified index.
     * @param index The zero-based index of the task to be marked.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(List<Task> tasksList, Ui ui, Storage storage) {
        assert this.index >= 0 && this.index < tasksList.size() : "Index is out of bounds.";
        Task toBeMarked = tasksList.get(index);
        toBeMarked.markAsDone();
        return ui.showTaskMarkedMessage(toBeMarked);
    }
}
