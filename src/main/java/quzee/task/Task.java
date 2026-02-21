package quzee.task;

import quzee.QuzeeException;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

// Solution below adapted from https://nus-cs2103-ay2526-s2.github.io/website/schedule/week2/project.html#a-classes

/**
 * Represents a generic task in the Quzee application.
 * Provides basic functionality for marking tasks as done and string conversions for Storage.
 */
public abstract class Task {

    // uuuu: to satisfy ResolverStyle.STRICT
    // /M/ to allow both 02 and 2
    public static final String INPUT_FORMAT_STRING = "d/M/uuuu HHmm"; // CHANGE from protected
    protected static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern(INPUT_FORMAT_STRING)
            .withResolverStyle(ResolverStyle.STRICT);

    protected static final String OUTPUT_FORMAT_STRING = "d MMM yyyy HH:mm";
    protected static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern(OUTPUT_FORMAT_STRING);

    protected String description;
    protected boolean isDone;

    /**
     * Instantiates a Task with the {@code description}.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks this task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns the task's status.
     * @return Task's status as icon ("X" or " ").
     */
    private String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Converts and returns an encoded task string from the storage file into a Task object.
     * @param string The pipe-separated string representing a task.
     * @return The corresponding Task {@code ToDo, Deadline, or Event}
     */
    public static Task convertStringToTask(String string) throws QuzeeException {
        String[] parts = string.split(" \\| ");

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task = switch (type) {
        case "T" -> new ToDo(description);
        case "D" -> new Deadline(description, parts[3]);
        case "E" -> {
            String[] split = parts[3].split("-");
            yield new Event(description, split[0], split[1]);
        }
        default -> {
            assert false : "Unknown task type: " + type;
            throw new IllegalArgumentException("For some reason, unknown task type: " + type);
        }
        };

        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Converts and returns the task into a formatted string suitable for file storage.
     * @return A pipe-separated string representing the task.
     */
    public String convertTaskToString() {
        return "T | " + (this.isDone ? "1" : "0") + " | " + this.description;
    }

    /**
     * Returns the string representation of the task, including its status icon and description.
     * @return A string formatted as "[status] description".
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
