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
     */
    public void showWelcomeMessage() {
        System.out.println("Hello! I'm " + Quzee.CHATBOT_NAME + "\nWhat can I do for you?\n");
    }

    /**
     * Displays the farewell message to the user the application ends.
     */
    public void showFarewellMessage() {
        System.out.println("Bye. Hope to see you again soon!\n");
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
     */
    public void showTaskAddedMessage(Task task, int size) {
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.println("Now you have " + size + " task" + (size <= 1 ? "" : "s") + " in the list.");
    }

    /**
     * Displays the successful deletion of the task
     * @param task The deleted task.
     * @param size The size of the {@code tasksList} after deletion.
     */
    public void showTaskDeletedMessage(Task task, int size) {
        System.out.println("Noted. I've removed this task:\n" + task);
        System.out.println("Now you have " + size + " task" + (size <= 1 ? "" : "s") + " in the list.");
    }

    /**
     * Displays that the task has been marked
     * @param task The marked task.
     */
    public void showTaskMarkedMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task);
    }

    /**
     * Displays that the task has been unmarked
     * @param task The unmarked task.
     */
    public void showTaskUnmarkedMessage(Task task) {
        System.out.println("OK, I've marked this task as not done yet:\n" + task);
    }

    /**
     * Displays the list of tasks to the user.
     * @param tasksList The list of tasks to be displayed.
     */
    public void showTasksList(List<Task> tasksList) {
        if (tasksList.isEmpty()) {
            showErrorMessage("There are no tasks in your list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            int count = 0;
            for (Task task : tasksList) {
                System.out.println(++count + ". " + task);
            }
        }
    }


    public void showMatchingTasksList(List<Task> tasksList) {
        if (tasksList.isEmpty()) {
            System.out.println("No matching tasks in your list!");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            int count = 0;
            for (Task task : tasksList) {
                System.out.println(++count + ". " + task);
            }
        }
    }

    /**
     * Displays the error message.
     * @param message The error message.
     */
    public void showErrorMessage(String message) {
        System.out.println(message);
    }

    /**
     * Displays the divider between each user input
     */
    public void showDivider() {
        System.out.println();
    }

    /**
     * Close the scanner.
     */
    public void closeScanner() {
        this.scanner.close();
    }

}
