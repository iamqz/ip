package quzee.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import quzee.QuzeeException;

public class DeadlineTest {

    @Test
    public void testToString_validDate_success() throws QuzeeException {
        Deadline deadline = new Deadline("Testing", "21/2/2026 2359");
        assertEquals("[D][ ] Testing (by: 21 Feb 2026 23:59)", deadline.toString());
    }

    @Test
    public void testConstructor_invalidDate_throwsException() {
        // Verifies that strict resolver catches non-existent dates
        assertThrows(QuzeeException.class, () ->
                new Deadline("Invalid Date", "30/2/2026 2359"));
    }
}
