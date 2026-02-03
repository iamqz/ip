import java.util.List;

public class DeleteCommand extends Command {

    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(List<Task> tasksList, Ui ui, Storage storage) {
        Task toBeDeleted = tasksList.get(index);
        tasksList.remove(index);
        ui.showTaskDeletedMessage(toBeDeleted, tasksList.size());
    }
}
