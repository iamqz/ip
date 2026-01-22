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
            try {
                String userInput = scanner.nextLine().strip();
                if (userInput.equals("bye")) {
                    scanner.close(); // added
                    System.out.println("Bye. Hope to see you again soon!\n");
                    break;

                } else if (userInput.equals("list")) {

                    if (database.isEmpty()) {
                        System.out.println("There are no tasks in the database.");
                    } else {
                        System.out.println("Here are the tasks in your list:");
                        int count = 0;
                        for (Task task : database) {
                            System.out.println(++count + "." + task.toString());
                        }
                    }

                } else if (userInput.startsWith("mark") || userInput.startsWith("unmark")) {

                    String[] modifiedUserInput = userInput.split("\\s+");

                    if  (modifiedUserInput.length == 1) {
                        throw new QuzeeException("For some reason, the Task number is missing!");
                    }

                    int index = Integer.parseInt(modifiedUserInput[1]);

                    if (index <= 0 || index > database.size()) {
                        throw new QuzeeException("For some reason, I do not have Task " + index + "!");
                    }

                    Task task = database.get(index - 1);

                    if (modifiedUserInput[0].equals("mark")) {
                        task.markAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                    } else {
                        task.markAsUndone();
                        System.out.println("OK, I've marked this task as not done yet:");
                    }
                    System.out.println(task);

                } else if (userInput.startsWith("todo")) {

                    if (userInput.length() < 5) {
                        throw new QuzeeException("For some reason, the description is missing!");
                    }
                    addTaskToDatabase(new ToDo(userInput.substring(5)));

                } else if (userInput.startsWith("deadline")) {

                    if (userInput.length() < 9) {
                        throw new QuzeeException("For some reason, the description and deadline are missing!");
                    }

                    if (!userInput.contains(" /by")) {
                        throw new QuzeeException("For some reason, \"/by <deadline>\" is missing!");
                    }
                    String[] modifiedUserInput = userInput.substring(9).split("\\s+/by\\s+");
                    if (modifiedUserInput[0].isEmpty()) {
                        throw new QuzeeException("For some reason, the description is missing!");
                    }
                    if (modifiedUserInput[1].isEmpty()) {
                        throw new QuzeeException("For some reason, the deadline is missing!");
                    }

                    addTaskToDatabase(new Deadline(modifiedUserInput[0], modifiedUserInput[1]));

                } else if (userInput.startsWith("event")) {

                    if (userInput.length() < 6) {
                        throw new QuzeeException("For some reason, the description and time periods are missing!");
                    }

                    if (!userInput.contains(" /from") || !userInput.contains(" /to")) {
                        throw new QuzeeException("For some reason, \"/from <start> /to <end>\" is missing!");
                    }

                    String[] modifiedUserInput = userInput.substring(6).split("\\s+/from\\s+|\\s+/to\\s+");

                    if (modifiedUserInput[0].isEmpty()) {
                        throw new QuzeeException("For some reason, the description is missing!");
                    }
                    if (modifiedUserInput[1].isEmpty()) {
                        throw new QuzeeException("For some reason, the time period (start) is missing!");
                    }
                    if (modifiedUserInput[2].isEmpty()) {
                        throw new QuzeeException("For some reason, the time period (end) is missing!");
                    }
                    addTaskToDatabase(new Event(modifiedUserInput[0], modifiedUserInput[1], modifiedUserInput[2]));

                } else {
                    throw new QuzeeException("For some reason, I do not know \"" + userInput + "\"!");
                }

            } catch (QuzeeException e) {
                System.out.println("ERROR:\n" + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("ERROR:\nTask number is invalid!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            System.out.println(); // new line for readability

        }

    }
}