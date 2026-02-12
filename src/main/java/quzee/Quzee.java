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
            tasksList.clear();

            storage.readTasks().stream()
                    .map(Task::convertStringToTask)
                    .forEach(tasksList::add);

            System.out.println("Loaded " + tasksList.size() + " tasks from storage.");

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        System.out.println(ui.showWelcomeMessage());
    }

    /**
     * Starts the chatbot's main execution loop.
     * Continues reading user input and executing commands accordingly until the exit command is issued.
     */
    public void run() {
        boolean isExit = false;

        while (!isExit) {
            try {
                String userInput = ui.readInput();
                quzee.command.Command command = Parser.parse(userInput, tasksList); // Explicitly pass components
                command.execute(tasksList, ui, storage);
                isExit = command.isExit();
            } catch (NumberFormatException e) {
                System.out.println(ui.showErrorMessage("Task number is invalid!"));
            } catch (Exception e) {
                System.out.println(ui.showErrorMessage(e.getMessage()));
            } finally {
                System.out.println(ui.showDivider());
            }

        }
    }

    /**
     * Generates a response based on the {@code userInput}
     * @param userInput User's input
     * @return String-message in the format: "Quzee heard: userInput"
     */
    public String getResponse(String userInput) {
        try {
            quzee.command.Command command = Parser.parse(userInput, tasksList);
            return command.execute(tasksList, ui, storage);
        } catch (QuzeeException e) {
            return "Error:\n" + e.getMessage();
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

    public String getWelcomeMessage() {
        return ui.showWelcomeMessage();
    }
}
