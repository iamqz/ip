package quzee.command;

import java.util.List;

import quzee.Storage;
import quzee.Ui;
import quzee.task.Task;

/**
 * Represents a command to list all the current task to the user.
 */
public class ListCommand extends Command {
    @Override
    public String execute(List<Task> tasksList, Ui ui, Storage storage) {
        ui.showTasksList(tasksList);
        return null;
    }
}
