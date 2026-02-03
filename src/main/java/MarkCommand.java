import java.util.List;

public class MarkCommand extends Command {

    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(List<Task> tasksList, Ui ui, Storage storage) {
        Task toBeMarked = tasksList.get(index);
        toBeMarked.markAsDone();
        ui.showTaskMarkedMessage(toBeMarked);
    }
}
