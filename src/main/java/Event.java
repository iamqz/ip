import java.time.LocalDateTime;

public class Event extends Task {

    protected LocalDateTime start;
    protected LocalDateTime end;

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

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start.format(OUTPUT_FORMAT) + " to: "
                + this.end.format(OUTPUT_FORMAT) + ")";
    }
}
