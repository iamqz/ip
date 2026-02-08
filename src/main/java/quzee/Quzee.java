package quzee;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import quzee.task.Task;

public class Quzee {

    public static final String CHATBOT_NAME = "Quzee";
    private final Storage storage;
    public static List<Task> tasks = new ArrayList<>();
    private final Ui ui;

    public Quzee() {
        this.ui = new Ui();
        this.storage = new Storage(Paths.get("data", "quzee.txt"));
        try {
            List<String> tasks = storage.readTasks();
            for (String task : tasks) {
                Quzee.tasks.add(Task.convertStringToTask(task));
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void run() {
        boolean isExit = false;
        ui.showWelcomeMessage();
        while (!isExit) {
            try {
                String userInput = ui.readInput();
                quzee.command.Command command = Parser.parse(userInput, tasks); // Explicitly pass components
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (QuzeeException e) {
                ui.showErrorMessage("ERROR:\n" + e.getMessage());
            } catch (NumberFormatException e) {
                ui.showErrorMessage("ERROR:\nTask number is invalid!");
            } catch (Exception e) {
                ui.showErrorMessage(e.getMessage());
            } finally {
                ui.showDivider();
            }

        }
    }

    public static void main(String[] args) {
        new Quzee().run();
    }
}