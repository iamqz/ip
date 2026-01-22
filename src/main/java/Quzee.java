import java.util.Scanner;
import java.util.ArrayList;
public class Quzee {

    public static final String CHATBOT_NAME = "Quzee";
    public static ArrayList<Task> database = new ArrayList<>();

    private static void addTaskToDatabase(Task task) {
        database.add(task);
        System.out.println("Got it. I've added this task:\n" + task.toString());
        int size = database.size();
        System.out.println("Now you have " + size + " task" + (size == 1 ? "" : "s") + " in the list.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm " + CHATBOT_NAME + "\nWhat can I do for you?\n");

        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.strip().equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n");
                break;

            } else if (userInput.strip().equals("list")) {
                System.out.println("Here are the tasks in your list:");
                int count = 0;
                for (Task task : database) {
                    System.out.println(++count + "." + task.toString());
                }

            } else if (userInput.strip().startsWith("mark") || userInput.strip().startsWith("unmark")) {
                boolean toBeMarked = userInput.strip().split(" ")[0].equals("mark");
                int index = Integer.parseInt(userInput.strip().split(" ")[1]);

                if (index > 0 && index <= database.size()) {
                    Task task = database.get(index - 1);

                    if (toBeMarked) {
                        task.markAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                    } else {
                        task.markAsUndone();
                        System.out.println("OK, I've marked this task as not done yet:");
                    }
                    System.out.println(task);

                } else {
                    System.out.println("Error: Task Not Found!");
                }

            } else if (userInput.strip().startsWith("todo ")) {
                addTaskToDatabase(new ToDo(userInput.strip().substring(5)));

            } else if (userInput.strip().startsWith("deadline ")) {
                String[] modifiedUserInput = userInput.strip().substring(9).split(" /by ");
                addTaskToDatabase(new Deadline(modifiedUserInput[0], modifiedUserInput[1]));

            } else if (userInput.strip().startsWith("event ")) {
                String[] modifiedUserInput = userInput.strip().substring(6).split(" /from | /to ");
                addTaskToDatabase(new Event(modifiedUserInput[0], modifiedUserInput[1], modifiedUserInput[2]));

            } else {
                System.out.println("Error: Invalid input!");
            }

            System.out.println(); // new line for readability

        }

    }
}