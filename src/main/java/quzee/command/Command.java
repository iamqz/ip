package quzee.command;

import java.util.List;

import quzee.task.Task;
import quzee.Storage;
import quzee.Ui;

public abstract class Command {

    public abstract void execute(List<Task> tasksList, Ui ui, Storage storage);


    public boolean isExit() {
        return false;
    }
}
