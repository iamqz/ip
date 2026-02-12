package quzee.command;

import java.time.LocalDateTime;
import java.util.List;

import quzee.Storage;
import quzee.Ui;
import quzee.task.Deadline;
import quzee.task.Event;
import quzee.task.Task;

/**
 * Represents a command to remind upcoming tasks from the task list.
 */
public class RemindCommand extends Command {

    private static final int WITHIN_DAYS = 3;

    @Override
    public String execute(List<Task> tasksList, Ui ui, Storage storage) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime remindTime = now.plusDays(WITHIN_DAYS);

        return ui.showRemindingTasksList(tasksList.stream()
                .filter(t -> isTaskUpcoming(t, now, remindTime))
                .toList());
    }

    private boolean isTaskUpcoming(Task task, LocalDateTime start, LocalDateTime end) {
        if (task instanceof Deadline) {
            LocalDateTime deadline = ((Deadline) task).getDeadline();
            return deadline.isAfter(start) && deadline.isBefore(end);
        } else if (task instanceof Event) {
            LocalDateTime eventStart = ((Event) task).getStart();
            return eventStart.isAfter(start) && eventStart.isBefore(end);
        }

        // Default
        return false;
    }
}
