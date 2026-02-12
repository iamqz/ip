package quzee;

import java.util.List;
import java.util.Scanner;

import quzee.task.Task;

/**
 * Handles the user interface of the application.
 * Responsible for the reading of user inputs and displaying messages.
 */
public class Ui {

    private final Scanner scanner;

    /**
     * Instantiates the UI of the application.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message to the user when the application starts.
     * @return Welcome message.
     */
    public String showWelcomeMessage() {
        return "Hello! I'm " + Quzee.CHATBOT_NAME + "\nWhat can I do for you?\n";
    }

    /**
     * Displays the farewell message to the user the application ends.
     * @return Farewell message.
     */
    public String showFarewellMessage() {
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * Reads a line of user input entered by the user.
     * @return The trimmed string entered by the user.
     */
    public String readInput() {
        return scanner.nextLine().strip();
    }

    /**
     * Displays the successful addition of the task
     * @param task The added task.
     * @param size The size of the {@code tasksList} after addition.
     * @return Acknowledgement message that the task has been added.
     */
    public String showTaskAddedMessage(Task task, int size) {
        return "Got it. I've added this task:\n" + task
                + "\nNow you have " + size + " task" + (size <= 1 ? "" : "s") + " in the list.";
    }

    /**
     * Displays the successful deletion of the task
     * @param task The deleted task.
     * @param size The size of the {@code tasksList} after deletion.
     * @return Acknowledgement message that the task has been deleted.
     */
    public String showTaskDeletedMessage(Task task, int size) {
        return "Noted. I've removed this task:\n" + task
                + "\nNow you have " + size + " task" + (size <= 1 ? "" : "s") + " in the list.";
    }

    /**
     * Displays that the task has been marked
     * @param task The marked task.
     * @return Acknowledgement message that the task has been marked.
     */
    public String showTaskMarkedMessage(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Displays that the task has been unmarked
     * @param task The unmarked task.
     * @return Acknowledgement message that the task has been unmarked.
     */
    public String showTaskUnmarkedMessage(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task;
    }

    /**
     * Displays the list of tasks to the user.
     * @param tasksList The list of tasks to be displayed.
     * @return The list of tasks.
     */
    public String showTasksList(List<Task> tasksList) {
        if (tasksList.isEmpty()) {
            return showErrorMessage("There are no tasks in your list.");
        } else {
            StringBuilder stringBuilder = new StringBuilder("Here are the tasks in your list:\n");
            int count = 0;
            for (Task task : tasksList) {
                stringBuilder.append(++count).append(". ").append(task).append("\n");
            }
            return stringBuilder.toString();
        }
    }

    /**
     * Displays the list of matching tasks to the user.
     * @param tasksList The list of matching tasks to be displayed.
     * @return The list of matching tasks.
     */
    public String showMatchingTasksList(List<Task> tasksList) {
        if (tasksList.isEmpty()) {
            return showErrorMessage("No matching tasks in your list!");
        } else {
            StringBuilder stringBuilder = new StringBuilder("Here are the matching tasks in your list:\n");
            int count = 0;
            for (Task task : tasksList) {
                stringBuilder.append(++count).append(". ").append(task).append("\n");
            }
            return stringBuilder.toString();
        }
    }

    /**
     * Displays the error message.
     * @param message The error message.
     * @return Error Message.
     */
    public String showErrorMessage(String message) {
        return "ERROR:\nFor some reason - " + message;
    }

    /**
     * Displays the divider between each user input
     * @return An empty line divider.
     */
    public String showDivider() {
        return "\n";
    }

    /**
     * Close the scanner.
     */
    public void closeScanner() {
        this.scanner.close();
    }

}
