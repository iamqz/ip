import java.util.Scanner;

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

    public void showTaskRemovedMessage(Task task, int size) {
        System.out.println("Noted. I've removed this task:\n" + task);
        System.out.println("Now you have " + size + " task" + (size <= 1 ? "" : "s") + " in the list.");
    }

    public void closeScanner() {
        this.scanner.close();
    }

}
