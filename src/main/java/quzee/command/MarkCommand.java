package quzee.command;

import java.util.List;

import quzee.task.Task;
import quzee.Storage;
import quzee.Ui;

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
    public void execute(List<Task> tasksList, Ui ui, Storage storage) {
        Task toBeMarked = tasksList.get(index);
        toBeMarked.markAsDone();
        ui.showTaskMarkedMessage(toBeMarked);
    }
}
