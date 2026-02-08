package quzee.task;

/**
 * Represents a "To-Do" type task in the Quzee application.
 */
public class ToDo extends Task {

    /**
     * Constructs a new "To-Do" task with the specified description.
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the task, including its status icon and description.
     * @return A string formatted as "[T][status] description".
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
