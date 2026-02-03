package quzee;

import quzee.task.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
