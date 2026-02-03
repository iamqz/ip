// Solution below adapted from https://nus-cs2103-ay2526-s2.github.io/website/schedule/week2/project.html#a-classes
public class Task {

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

        Task task;

        switch (type) {
        case "T":
            task = new Task(description);
            break;
        case "D":
            task = new Deadline(description, parts[3]);
            break;
        case "E":
            String[] split = parts[3].split("-");
            task = new Event(description, split[0], split[1]);
            break;
        default:
            throw new IllegalArgumentException("For some reason, unknown task type: " + type);
        }

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