package quzee;

import java.util.List;

public class ListCommand extends Command {
    @Override
    public void execute(List<Task> tasksList, Ui ui, Storage storage) {
        ui.showTasksList(tasksList);
    }
}
