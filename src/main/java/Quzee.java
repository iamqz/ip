import java.util.Scanner;
public class Quzee {

    public static final String CHATBOT_NAME = "Quzee";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm " + CHATBOT_NAME + "\nWhat can I do for you?\n");

        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n");
                break;
            } else {
                System.out.println(userInput);
            }

        }

    }
}