package quzee;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import quzee.task.Task;

/**
 * Main class for the Quzee chatbot application.
 * Manages the initialisation of all the components and the application loop.
 */
public class Quzee {

    public static final String CHATBOT_NAME = "Quzee";
    private static List<Task> tasksList = new ArrayList<>();
    private final Storage storage;
    private final Ui ui;

    /**
     * Initialises the Quzee application, setting up the UI and Storage components.
     * Loads existing tasks from the local storage file upon startup to {@code tasksList}.
     */
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

    /**
     * Starts the chatbot's main execution loop.
     * Continues reading user input and executing commands accordingly until the exit command is issued.
     */
    public void run() {
        boolean isExit = false;
        ui.showWelcomeMessage();
        while (!isExit) {
            try {
                String userInput = ui.readInput();
                quzee.command.Command command = Parser.parse(userInput, tasksList); // Explicitly pass components
                command.execute(tasksList, ui, storage);
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

    /**
     * Main entry point of the Quzee application.
     * Instantiates the Quzee chatbot and triggers the main execution loop.
     * @param args Command Line arguments (not used in this application).
     */
    public static void main(String[] args) {
        new Quzee().run();
    }
}
