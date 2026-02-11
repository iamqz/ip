package quzee.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import quzee.Storage;
import quzee.Ui;
import quzee.task.Task;

/**
 * Represents a command to terminate the Quzee application.
 * This command handles saving the final task list to storage before exiting.
 */
public class ExitCommand extends Command {

    @Override
    public String execute(List<Task> tasksList, Ui ui, Storage storage) {
        try {
            List<String> tasksInString = new ArrayList<>();
            for (Task task : tasksList) {
                tasksInString.add(task.convertTaskToString());
            }
            storage.writeTasks(tasksInString);
        } catch (IOException e) {
            ui.showErrorMessage(e.getMessage());
        }
        return ui.showFarewellMessage();
    }

    /**
     * Indicates that the application should stop running.
     * @return Always return True.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
