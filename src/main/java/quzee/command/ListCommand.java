package quzee.command;

import java.util.List;

import quzee.task.Task;
import quzee.Storage;
import quzee.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(List<Task> tasksList, Ui ui, Storage storage) {
        ui.showTasksList(tasksList);
    }
}
