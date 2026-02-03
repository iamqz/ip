package quzee;

import quzee.command.AddCommand;
import quzee.command.Command;
import quzee.command.DeleteCommand;
import quzee.command.ExitCommand;
import quzee.command.ListCommand;
import quzee.command.MarkCommand;
import quzee.command.UnmarkCommand;

import quzee.task.Task;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import java.nio.file.Paths;
public class Quzee {

    public static final String CHATBOT_NAME = "Quzee";
    private final Storage storage;
    public static List<Task> tasksList = new ArrayList<>();
    private final Ui ui;

    public Quzee() {
        this.ui = new Ui();
        this.storage = new Storage(Paths.get("data", "quzee.txt"));
        try {
            List<String> tasks = storage.readTasks();
            for (String task : tasks) {
                tasksList.add(Task.convertStringToTask(task));
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
                Command command = Parser.parse(userInput, tasksList);
                command.execute(tasksList, ui, storage);
                isExit = command.isExit();
            } catch (QuzeeException e) {
                System.out.println("ERROR:\n" + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("ERROR:\nTask number is invalid!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                ui.showDivider();
            }

        }
        ui.closeScanner();
    }

    public static void main(String[] args) {
        new Quzee().run();
    }
}