package quzee.command;

import java.util.List;

import quzee.task.Task;
import quzee.Storage;
import quzee.Ui;

public class UnmarkCommand extends Command {
    private final int index;

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
