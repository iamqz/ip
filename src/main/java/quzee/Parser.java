package quzee;

import java.util.List;

import quzee.command.AddCommand;
import quzee.command.Command;
import quzee.command.DeleteCommand;
import quzee.command.ExitCommand;
import quzee.command.FindCommand;
import quzee.command.ListCommand;
import quzee.command.MarkCommand;
import quzee.command.UnmarkCommand;
import quzee.task.Deadline;
import quzee.task.Event;
import quzee.task.Task;
import quzee.task.ToDo;

/**
 * Parses user input into executable commands for the Quzee application.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding Command object.
     * @param userInput The raw input string inputted by the user.
     * @param tasksList The current list of tasks, used for validating task indices.
     * @return A Command object that can be executed.
     * @throws QuzeeException If user input is invalid or incomplete.
     */
    public static Command parse(String userInput, List<Task> tasksList) throws QuzeeException {
        if (userInput.equals("bye")) {
            return new ExitCommand();
        } else if (userInput.equals("list")) {
            return new ListCommand();
        } else if (userInput.startsWith("find")) {

            String[] modifiedUserInput = userInput.split("\\s+");

            if (modifiedUserInput.length < 2 || modifiedUserInput[1].isEmpty()) {
                throw new QuzeeException("For some reason, the keyword is missing!");
            }
            return new FindCommand(modifiedUserInput[1].trim());

        } else if (userInput.startsWith("delete")) {

            String[] modifiedUserInput = userInput.split("\\s+");

            if (modifiedUserInput.length == 1) {
                throw new QuzeeException("For some reason, the Task number is missing!");
            }

            int index = Integer.parseInt(modifiedUserInput[1]);

            if (index <= 0 || index > tasksList.size()) {
                throw new QuzeeException("For some reason, I do not have Task " + index + "!");
            }

            return new DeleteCommand(index - 1);

        } else if (userInput.startsWith("mark") || userInput.startsWith("unmark")) {

            String[] modifiedUserInput = userInput.split("\\s+");

            if (modifiedUserInput.length == 1) {
                throw new QuzeeException("For some reason, the Task number is missing!");
            }

            int index = Integer.parseInt(modifiedUserInput[1]);

            if (index <= 0 || index > tasksList.size()) {
                throw new QuzeeException("For some reason, I do not have Task " + index + "!");
            }

            if (modifiedUserInput[0].equals("mark")) {
                return new MarkCommand(index - 1);
            } else {
                return new UnmarkCommand(index - 1);
            }

        } else if (userInput.startsWith("todo")) {

            if (userInput.length() < 5) {
                throw new QuzeeException("For some reason, the description is missing!");
            }
            return new AddCommand(new ToDo(userInput.substring(5)));

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
                throw new QuzeeException("For some reason, invalid input format!\n"
                        + "Deadline format should be: event <description> /by <deadline>\nDate Format: "
                        + Task.INPUT_FORMAT_STRING);
            }

            return new AddCommand(new Deadline(modifiedUserInput[0], modifiedUserInput[1]));

        } else if (userInput.startsWith("event")) {

            if (userInput.length() < 6) {
                throw new QuzeeException("For some reason, the description and time periods are missing!");
            }

            if (!userInput.contains(" /from") || !userInput.contains(" /to")) {
                throw new QuzeeException("For some reason, \"/from <start> /to <end>\" is missing!\nDate "
                        + "Format: " + Task.INPUT_FORMAT_STRING);
            }

            String[] modifiedUserInput = userInput.substring(6).split("\\s+/from\\s+|\\s+/to\\s+");

            if (modifiedUserInput.length < 3) {
                throw new QuzeeException("For some reason, invalid input format!\n"
                        + "Event format should be: event <description> /from <start> /to <end>\nDate Format: "
                        + Task.INPUT_FORMAT_STRING);
            }

            return new AddCommand(new Event(modifiedUserInput[0], modifiedUserInput[1], modifiedUserInput[2]));
        } else {
            throw new QuzeeException("For some reason, I do not know \"" + userInput + "\"!");
        }
    }
}
