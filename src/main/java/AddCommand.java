import java.util.List;

public class AddCommand extends Command {

    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(List<Task> tasksList, Ui ui, Storage storage) {
        tasksList.add(task);
        ui.showTaskAddedMessage(task, tasksList.size());
    }
}
