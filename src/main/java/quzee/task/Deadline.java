package quzee.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import quzee.QuzeeException;

/**
 * Represents a "Deadline" type task in the Quzee application.
 */
public class Deadline extends Task {

    protected LocalDateTime deadline;

    /**
     * Constructs a new "Deadline" task with the specified description.
     * @param description The description of the task.
     * @param deadline The deadline of the task.
     */
    public Deadline(String description, String deadline) throws QuzeeException {
        super(description);
        try {
            this.deadline = LocalDateTime.parse(deadline, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new QuzeeException(e.getMessage());
        }
    }

    @Override
    public String convertTaskToString() {
        return "D | " + (this.isDone ? "1" : "0") + " | " + this.description + " | "
                + this.deadline.format(INPUT_FORMAT);
    }

    /**
     * Returns the string representation of the task, including its status icon and description.
     * @return A string formatted as "[D][status] description (by: deadline)".
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.format(OUTPUT_FORMAT) + ")";
    }

    /**
     * Getter for the task's deadline.
     * @return Task's deadline.
     */
    public LocalDateTime getDeadline() {
        return this.deadline;
    }
}
