package quzee;

/**
 * Represents exception specific to the Quzee chatbot application.
 * Used to signal errors related to the user input, task management, or command execution.
 */
public class QuzeeException extends Exception {

    /**
     * Constructs a new QuzeeException with the specified error message.
     * @param message The error message describing the cause of the exception.
     */
    public QuzeeException(String message) {
        super(message);
    }
}
