package quzee.task;

import java.time.LocalDateTime;

/**
 * Represents an "Event" type task in the Quzee application.
 */
public class Event extends Task {

    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Constructs a new "Event" task with the specified description.
     * @param description The description of the task.
     * @param start The start of the task.
     * @param end The end of the task.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = LocalDateTime.parse(start, INPUT_FORMAT);
        this.end = LocalDateTime.parse(end, INPUT_FORMAT);
    }

    @Override
    public String convertTaskToString() {
        return "E | " + (this.isDone ? "1" : "0") + " | " + this.description + " | " + this.start.format(INPUT_FORMAT)
                + "-" + this.end.format(INPUT_FORMAT);
    }

    /**
     * Returns the string representation of the task, including its status icon and description.
     * @return A string formatted as "[E][status] description (from: start to: end)".
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start.format(OUTPUT_FORMAT) + " to: "
                + this.end.format(OUTPUT_FORMAT) + ")";
    }
}
