import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import java.nio.file.Paths;
public class Quzee {

    public static final String CHATBOT_NAME = "Quzee";
    public static List<Task> tasksList = new ArrayList<>();
    private static Ui ui;

    private static void addTaskToTasksList(Task task) {
        tasksList.add(task);
        ui.showTaskAddedMessage(task, tasksList.size());
    }

    private static void removeTaskFromTasksList(int index) {
        Task task = tasksList.get(index);
        tasksList.remove(index);
        ui.showTaskDeletedMessage(task, tasksList.size());
    }

    public static void main(String[] args) {
        ui = new Ui();
        Storage storage = new Storage(Paths.get("data", "quzee.txt"));
        try {
            List<String> tasks = storage.readTasks();
            for (String task : tasks) {
                tasksList.add(Task.convertStringToTask(task));
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        ui.showWelcomeMessage();

        while (true) {
            try {
                String userInput = ui.readInput();
                if (userInput.equals("bye")) {
                    try {
                        List<String> tasksInString = new ArrayList<>();
                        for (Task task : tasksList) {
                            tasksInString.add(task.convertTaskToString());
                        }
                        storage.writeTasks(tasksInString);
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                    }
                    ui.showFarewellMessage();
                    ui.closeScanner();
                    break;

                } else if (userInput.equals("list")) {

                    if (tasksList.isEmpty()) {
                        System.out.println("There are no tasks in the tasksList.");
                    } else {
                        System.out.println("Here are the tasks in your list:");
                        int count = 0;
                        for (Task task : tasksList) {
                            System.out.println(++count + "." + task);
                        }
                    }

                } else if (userInput.startsWith("mark") || userInput.startsWith("unmark")) {

                    String[] modifiedUserInput = userInput.split("\\s+");

                    if  (modifiedUserInput.length == 1) {
                        throw new QuzeeException("For some reason, the Task number is missing!");
                    }

                    int index = Integer.parseInt(modifiedUserInput[1]);

                    if (index <= 0 || index > tasksList.size()) {
                        throw new QuzeeException("For some reason, I do not have Task " + index + "!");
                    }

                    Task task = tasksList.get(index - 1);

                    if (modifiedUserInput[0].equals("mark")) {
                        task.markAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                    } else {
                        task.markAsUndone();
                        System.out.println("OK, I've marked this task as not done yet:");
                    }
                    System.out.println(task);

                } else if (userInput.startsWith("delete")) {

                    String[] modifiedUserInput = userInput.split("\\s+");

                    if  (modifiedUserInput.length == 1) {
                        throw new QuzeeException("For some reason, the Task number is missing!");
                    }

                    int index = Integer.parseInt(modifiedUserInput[1]);

                    if (index <= 0 || index > tasksList.size()) {
                        throw new QuzeeException("For some reason, I do not have Task " + index + "!");
                    }

                    removeTaskFromTasksList(index - 1);

                } else if (userInput.startsWith("todo")) {

                    if (userInput.length() < 5) {
                        throw new QuzeeException("For some reason, the description is missing!");
                    }
                    addTaskToTasksList(new ToDo(userInput.substring(5)));

                } else if (userInput.startsWith("deadline")) {

                    if (userInput.length() < 9) {
                        throw new QuzeeException("For some reason, the description and deadline are missing!");
                    }

                    if (!userInput.contains(" /by")) {
                        throw new QuzeeException("For some reason, \"/by <deadline>\" is missing!\nDate Format: "
                                + Task.INPUT_FORMAT_STRING);
                    }
                    String[] modifiedUserInput = userInput.substring(9).split("\\s+/by\\s+");
                    if (modifiedUserInput.length < 2) {
                        throw new QuzeeException("For some reason, invalid input format!\n" +
                                "Deadline format should be: event <description> /by <deadline>\nDate Format: "
                                + Task.INPUT_FORMAT_STRING);
                    }

                    addTaskToTasksList(new Deadline(modifiedUserInput[0], modifiedUserInput[1]));

                } else if (userInput.startsWith("event")) {

                    if (userInput.length() < 6) {
                        throw new QuzeeException("For some reason, the description and time periods are missing!");
                    }

                    if (!userInput.contains(" /from") || !userInput.contains(" /to")) {
                        throw new QuzeeException("For some reason, \"/from <start> /to <end>\" is missing!\nDate " +
                                "Format: " + Task.INPUT_FORMAT_STRING);
                    }

                    String[] modifiedUserInput = userInput.substring(6).split("\\s+/from\\s+|\\s+/to\\s+");

                    if (modifiedUserInput.length < 3) {
                        throw new QuzeeException("For some reason, invalid input format!\n" +
                                "Event format should be: event <description> /from <start> /to <end>\nDate Format: "
                                + Task.INPUT_FORMAT_STRING);
                    }
                    addTaskToTasksList(new Event(modifiedUserInput[0], modifiedUserInput[1], modifiedUserInput[2]));

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