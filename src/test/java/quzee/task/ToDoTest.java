package quzee.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {

    @Test
    public void testToString_notDone_success() {
        ToDo toDo = new ToDo("Finish CS2103");
        assertEquals("[T][ ] Finish CS2103", toDo.toString());
    }

    @Test
    public void testToString_done_success() {
        ToDo toDo = new ToDo("Finish CS2103");
        toDo.markAsDone();
        assertEquals("[T][X] Finish CS2103", toDo.toString());
    }

    @Test
    public void test_convertTaskToString_success() {
        ToDo toDo = new ToDo("Finish CS2103");
        assertEquals("T | 0 | Finish CS2103", toDo.convertTaskToString());
    }
}
