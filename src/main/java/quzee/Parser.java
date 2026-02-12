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
        String[] modifiedUserInput = userInput.split(REGEX_SPACING, 2);
        String commandWord = modifiedUserInput[0].toLowerCase();

        return switch (commandWord) {
        case "bye" -> new ExitCommand();
        case "list" -> new ListCommand();
        case "find" -> helperFind(modifiedUserInput[1]);
        case "delete" -> helperDelete(modifiedUserInput[1], tasksList);
        case "mark" -> helperMark(modifiedUserInput[1], tasksList);
        case "unmark" -> helperUnmark(modifiedUserInput[1], tasksList);
        case "todo" -> helperTodo(modifiedUserInput[1]);
        case "deadline" -> helperDeadline(modifiedUserInput[1]);
        case "event" -> helperEvent(modifiedUserInput[1]);
        default -> throw new QuzeeException("Unknown command: " + commandWord);
        };
    }

    private static Command helperFind(String editedInput) throws QuzeeException {
        if (editedInput.isEmpty()) {
            throw new QuzeeException("For some reason, the keyword is missing!");
        }
        return new FindCommand(editedInput);
    }

    /**
     * Helper function to validate and convert String-coded index into int-index.
     * Used in helperDelete, helperMark, helperUnmark.
     * @param editedInput String-coded index.
     * @param tasksList The list of tasks.
     * @return int-coded index (0-indexed).
     * @throws QuzeeException QuzeeException is thrown when input is invalid.
     */
    private static int validateAndParseIndex(String editedInput, List<Task> tasksList) throws QuzeeException {
        if (editedInput.isEmpty()) {
            throw new QuzeeException("For some reason, the Task number is missing!");
        }

        int index = Integer.parseInt(editedInput);
        boolean isValidIndex = index > 0 && index <= tasksList.size();

        if (!isValidIndex) {
            throw new QuzeeException("For some reason, I do not have Task " + index + "!");
        }
        return index - 1;
    }

    private static Command helperDelete(String editedInput, List<Task> tasksList) throws QuzeeException {

        return new DeleteCommand(validateAndParseIndex(editedInput, tasksList));
    }

    private static Command helperMark(String editedInput, List<Task> tasksList) throws QuzeeException {

        return new MarkCommand(validateAndParseIndex(editedInput, tasksList));
    }

    private static Command helperUnmark(String editedInput, List<Task> tasksList) throws QuzeeException {

        return new UnmarkCommand(validateAndParseIndex(editedInput, tasksList));
    }

    private static Command helperTodo(String editedInput) throws QuzeeException {

        if (editedInput.isEmpty()) {
            throw new QuzeeException("For some reason, the description is missing!");
        }

        return new AddCommand(new ToDo(editedInput));
    }

    private static Command helperDeadline(String editedInput) throws QuzeeException {

        String[] temp = editedInput.split(MARKER_BY, 2);
        boolean isValidInput = temp.length == 2 && !temp[0].isEmpty() && !temp[1].isEmpty();

        if (!isValidInput) {
            throw new QuzeeException("For some reason, invalid input format!\n"
                    + "Deadline format should be: deadline <description> " + MARKER_BY + " <deadline>\nDate Format: "
                    + Task.INPUT_FORMAT_STRING);
        }
        return new AddCommand(new Deadline(temp[0].strip(), temp[1].strip()));
    }

    private static Command helperEvent(String editedInput) throws QuzeeException {

        String[] temp = editedInput.split(MARKER_FROM + "|" + MARKER_TO, 2);
        boolean isValidInput = temp.length == 3 && !temp[0].isEmpty() && !temp[1].isEmpty() && !temp[2].isEmpty();

        if (!isValidInput) {
            throw new QuzeeException("For some reason, invalid input format!\n"
                    + "Event format should be: event <description> " + MARKER_FROM + " <start> " + MARKER_TO
                    + " <end>\nDate Format: " + Task.INPUT_FORMAT_STRING);
        }
        return new AddCommand(new Event(temp[0].strip(), temp[1].strip(), temp[2].strip()));
    }
}
