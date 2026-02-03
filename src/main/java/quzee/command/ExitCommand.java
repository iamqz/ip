package quzee.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import quzee.task.Task;
import quzee.Storage;
import quzee.Ui;

public class ExitCommand extends Command {

    @Override
    public void execute(List<Task> tasksList, Ui ui, Storage storage) {
        try {
            List<String> tasksInString = new ArrayList<>();
            for (Task task : tasksList) {
                tasksInString.add(task.convertTaskToString());
            }
            storage.writeTasks(tasksInString);
        } catch (IOException e) {
            ui.showErrorMessage(e.getMessage());
        }
        ui.showFarewellMessage();
    }

    public boolean isExit() {
        return true;
    }
}
