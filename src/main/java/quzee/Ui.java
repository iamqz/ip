package quzee;

import java.util.List;
import java.util.Scanner;

import quzee.task.Task;

public class Ui {

    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcomeMessage() {
        System.out.println("Hello! I'm " + Quzee.CHATBOT_NAME + "\nWhat can I do for you?\n");
    }

    public void showFarewellMessage() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    public String readInput() {
        return scanner.nextLine().strip();
    }

    public void showTaskAddedMessage(Task task, int size) {
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.println("Now you have " + size + " task" + (size <= 1 ? "" : "s") + " in the list.");
    }

    public void showTaskDeletedMessage(Task task, int size) {
        System.out.println("Noted. I've removed this task:\n" + task);
        System.out.println("Now you have " + size + " task" + (size <= 1 ? "" : "s") + " in the list.");
    }

    public void showTaskMarkedMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task);
    }

    public void showTaskUnmarkedMessage(Task task) {
        System.out.println("OK, I've marked this task as not done yet:\n" + task);
    }

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

    public void showErrorMessage(String message) {
        System.out.println(message);
    }

    public void showDivider() {
        System.out.println();
    }
    public void closeScanner() {
        this.scanner.close();
    }

}
