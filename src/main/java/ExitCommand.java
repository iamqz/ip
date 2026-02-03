import java.util.List;

public class ExitCommand extends Command {

    @Override
    public void execute(List<Task> tasksList, Ui ui, Storage storage) {
        ui.showFarewellMessage();
    }

    public boolean isExit() {
        return true;
    }
}
