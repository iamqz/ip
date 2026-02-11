package quzee.command;

import java.util.List;

import quzee.Storage;
import quzee.Ui;
import quzee.task.Task;

/**
 * Represents an executable command in the Quzee application.
 */
public abstract class Command {

    /**
     * Executes the command's logic using the provided {@code tasksList, ui, storage}.
     *
     * @param tasksList The list of tasks currently held by the application.
     * @param ui        The user interface used to display feedback to the user.
     * @param storage   The storage handler used to save or load tasks
     * @return
     */
    public abstract String execute(List<Task> tasksList, Ui ui, Storage storage);

    /**
     * Returns true if this command should cause the application to terminate.
     * @return True if the application should exit, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
