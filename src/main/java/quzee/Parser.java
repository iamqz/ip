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

    private static final String REGEX_SPACING = "\\s+";

    private static final String MARKER_BY = "/by";
    private static final String MARKER_FROM = "/from";
    private static final String MARKER_TO = "/to";
    /**
     * Parses the user input and returns the corresponding Command object.
     * @param userInput The raw input string inputted by the user.
     * @param tasksList The current list of tasks, used for validating task indices.
     * @return A Command object that can be executed.
     * @throws QuzeeException If user input is invalid or incomplete.
     */
    public static Command parse(String userInput, List<Task> tasksList) throws QuzeeException {
        String[] modifiedUserInput = userInput.trim().split(REGEX_SPACING, 2);
        String commandWord = modifiedUserInput[0].toLowerCase();

        String arguments = modifiedUserInput.length > 1 ? modifiedUserInput[1] : "";

        return switch (commandWord) {
        case "bye" -> new ExitCommand();
        case "list" -> new ListCommand();
        case "find" -> helperFind(arguments);
        case "delete" -> helperDelete(arguments, tasksList);
        case "mark" -> helperMark(arguments, tasksList);
        case "unmark" -> helperUnmark(arguments, tasksList);
        case "todo" -> helperTodo(arguments);
        case "deadline" -> helperDeadline(arguments);
        case "event" -> helperEvent(arguments);
        default -> throw new QuzeeException("Unknown command: " + commandWord);
        };
    }

    private static Command helperFind(String arguments) throws QuzeeException {
        if (arguments.trim().isEmpty()) {
            throw new QuzeeException("The keyword is missing!");
        }
        return new FindCommand(arguments);
    }

    /**
     * Helper function to validate and convert String-coded index into int-index.
     * Used in helperDelete, helperMark, helperUnmark.
     * @param arguments String-coded index.
     * @param tasksList The list of tasks.
     * @return int-coded index (0-indexed).
     * @throws QuzeeException QuzeeException is thrown when input is invalid.
     */
    private static int validateAndParseIndex(String arguments, List<Task> tasksList) throws QuzeeException {
        if (arguments.isEmpty()) {
            throw new QuzeeException("Task number is missing!");
        }

        int index = Integer.parseInt(arguments);
        boolean isValidIndex = index > 0 && index <= tasksList.size();

        if (!isValidIndex) {
            throw new QuzeeException("Non-existing Task " + index + "!");
        }
        return index - 1;
    }

    private static Command helperDelete(String arguments, List<Task> tasksList) throws QuzeeException {

        return new DeleteCommand(validateAndParseIndex(arguments, tasksList));
    }

    private static Command helperMark(String arguments, List<Task> tasksList) throws QuzeeException {

        return new MarkCommand(validateAndParseIndex(arguments, tasksList));
    }

    private static Command helperUnmark(String arguments, List<Task> tasksList) throws QuzeeException {

        return new UnmarkCommand(validateAndParseIndex(arguments, tasksList));
    }

    private static Command helperTodo(String arguments) throws QuzeeException {

        if (arguments.isEmpty()) {
            throw new QuzeeException("The description is missing!");
        }

        return new AddCommand(new ToDo(arguments));
    }

    private static Command helperDeadline(String arguments) throws QuzeeException {

        String[] temp = arguments.split(MARKER_BY, 2);
        boolean isValidInput = temp.length == 2 && !temp[0].isEmpty() && !temp[1].isEmpty();

        if (!isValidInput) {
            throw new QuzeeException("Invalid input format!\n"
                    + "Deadline format should be: deadline <description> " + MARKER_BY + " <deadline>\nDate Format: "
                    + Task.INPUT_FORMAT_STRING);
        }
        return new AddCommand(new Deadline(temp[0].strip(), temp[1].strip()));
    }

    private static Command helperEvent(String arguments) throws QuzeeException {

        String[] temp = arguments.split(MARKER_FROM + "|" + MARKER_TO, 2);
        boolean isValidInput = temp.length == 3 && !temp[0].isEmpty() && !temp[1].isEmpty() && !temp[2].isEmpty();

        if (!isValidInput) {
            throw new QuzeeException("Invalid input format!\n"
                    + "Event format should be: event <description> " + MARKER_FROM + " <start> " + MARKER_TO
                    + " <end>\nDate Format: " + Task.INPUT_FORMAT_STRING);
        }
        return new AddCommand(new Event(temp[0].strip(), temp[1].strip(), temp[2].strip()));
    }
}
