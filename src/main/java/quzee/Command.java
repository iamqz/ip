package quzee;

import java.util.List;

public abstract class Command {

    public abstract void execute(List<Task> tasksList, Ui ui, Storage storage);

    public boolean isExit() {
        return false;
    }
}
