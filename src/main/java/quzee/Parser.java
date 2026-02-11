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
        String[] modifiedUserInput = userInput.split("\\s+");
        String commandWord = modifiedUserInput[0].toLowerCase();

        return switch (commandWord) {
            case "bye" -> new ExitCommand();
            case "list" -> new ListCommand();
            case "find" -> helperFind(modifiedUserInput);
            case "delete" -> helperDelete(modifiedUserInput, tasksList);
            case "mark" -> helperMark(modifiedUserInput, tasksList);
            case "unmark" -> helperUnmark(modifiedUserInput, tasksList);
            case "todo" -> helperTodo(modifiedUserInput);
            case "deadline" -> helperDeadline(modifiedUserInput);
            case "event" -> helperEvent(modifiedUserInput);
            default -> throw new QuzeeException("Unknown command: " + commandWord);
        };
    }

    private static Command helperFind(String[] modifiedUserInput) throws QuzeeException {
        if (modifiedUserInput.length != 2) {
            throw new QuzeeException("For some reason, the keyword is missing!");
        }
        return new FindCommand(modifiedUserInput[1].trim());
    }

    private static Command helperDelete(String[] modifiedUserInput, List<Task> tasksList) throws QuzeeException {

        if (modifiedUserInput.length != 2) {
            throw new QuzeeException("For some reason, the Task number is missing!");
        }

        int index = Integer.parseInt(modifiedUserInput[1]);
        boolean isValidIndex = index > 0 && index <= tasksList.size();

        if (!isValidIndex) {
            throw new QuzeeException("For some reason, I do not have Task " + index + "!");
        }

        return new DeleteCommand(index - 1);
    }

    private static Command helperMark(String[] modifiedUserInput, List<Task> tasksList) throws QuzeeException {

        if (modifiedUserInput.length != 2) {
            throw new QuzeeException("For some reason, the Task number is missing!");
        }

        int index = Integer.parseInt(modifiedUserInput[1]);
        boolean isValidIndex = index > 0 && index <= tasksList.size();

        if (!isValidIndex) {
            throw new QuzeeException("For some reason, I do not have Task " + index + "!");
        }

        return new MarkCommand(index - 1);
    }

    private static Command helperUnmark(String[] modifiedUserInput, List<Task> tasksList) throws QuzeeException {

        if (modifiedUserInput.length != 2) {
            throw new QuzeeException("For some reason, the Task number is missing!");
        }

        int index = Integer.parseInt(modifiedUserInput[1]);
        boolean isValidIndex = index > 0 && index <= tasksList.size();

        if (!isValidIndex) {
            throw new QuzeeException("For some reason, I do not have Task " + index + "!");
        }

        return new UnmarkCommand(index - 1);
    }

    private static Command helperTodo(String[] modifiedUserInput) throws QuzeeException {

        if (modifiedUserInput.length != 2) {
            throw new QuzeeException("For some reason, the description is missing!");
        }

        return new AddCommand(new ToDo(modifiedUserInput[1]));
    }

    private static Command helperDeadline(String[] modifiedUserInput) throws QuzeeException {

        boolean isValidInput = modifiedUserInput.length == 4 && modifiedUserInput[2].equals("/by");

        if (!isValidInput) {
            throw new QuzeeException("For some reason, invalid input format!\n"
                    + "Deadline format should be: deadline <description> /by <deadline>\nDate Format: "
                    + Task.INPUT_FORMAT_STRING);
        }
        return new AddCommand(new Deadline(modifiedUserInput[1], modifiedUserInput[3]));
    }

    private static Command helperEvent(String[] modifiedUserInput) throws QuzeeException {

        boolean isValidInput = modifiedUserInput.length == 6 && modifiedUserInput[2].equals("/from")
                && modifiedUserInput[4].equals("/to");

        if (!isValidInput) {
            throw new QuzeeException("For some reason, invalid input format!\n"
                    + "Event format should be: event <description> /from <start> /to <end>\nDate Format: "
                    + Task.INPUT_FORMAT_STRING);
        }
        return new AddCommand(new Deadline(modifiedUserInput[1], modifiedUserInput[3]));
    }
}
