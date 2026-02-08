package quzee.command;

import java.util.List;

import quzee.Storage;
import quzee.Ui;
import quzee.task.Task;

/**
 * Represents a command to mark a specific task as not yet completed.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Constructs a UnmarkCommand for the task to be unmarked at the specified index.
     * @param index The zero-based index of the task to be unmarked.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(List<Task> tasksList, Ui ui, Storage storage) {
        Task toBeUnmarked = tasksList.get(index);
        toBeUnmarked.markAsUndone();
        ui.showTaskUnmarkedMessage(toBeUnmarked);
    }
}
