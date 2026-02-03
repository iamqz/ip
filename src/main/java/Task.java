import java.time.format.DateTimeFormatter;

// Solution below adapted from https://nus-cs2103-ay2526-s2.github.io/website/schedule/week2/project.html#a-classes
public abstract class Task {

    protected static final String INPUT_FORMAT_STRING = "d/M/yyyy HHmm";
    protected static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern(INPUT_FORMAT_STRING);
    protected static final String OUTPUT_FORMAT_STRING = "d MMM yyyy HH:mm";
    protected static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern(OUTPUT_FORMAT_STRING);

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    public static Task convertStringToTask(String string) {
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
            default -> throw new IllegalArgumentException("For some reason, unknown task type: " + type);
        };

        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

    public String convertTaskToString() {
        return "T | " + (this.isDone ? "1" : "0") + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}