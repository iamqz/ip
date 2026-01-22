import java.util.Scanner;
import java.util.ArrayList;
public class Quzee {

    public static final String CHATBOT_NAME = "Quzee";
    public static ArrayList<String> database = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm " + CHATBOT_NAME + "\nWhat can I do for you?\n");

        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n");
                break;
            } else if (userInput.equals("list")) {
                int count = 0;
                for (String s : database) {
                    System.out.println(++count + ". " + s);
                }
                System.out.println("\n");
            } else {
                database.add(userInput);
                System.out.println("added: " + userInput + "\n");
            }

        }

    }
}