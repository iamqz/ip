package quzee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import quzee.command.ExitCommand;

public class ParserTest {

    @Test
    public void testParse_validBye_returnsExitCommand() throws QuzeeException {
        assertTrue(Parser.parse("bye", new ArrayList<>()) instanceof ExitCommand);
    }

    @Test
    public void testParse_unknownCommand_returnsQuzeeException() throws QuzeeException {
        QuzeeException quzeeException = assertThrows(QuzeeException.class, () ->
                Parser.parse("INVALID", new ArrayList<>()));
        assertEquals("Unknown command: invalid", quzeeException.getMessage());
    }

    @Test
    public void testParse_invalidEvent_throwsQuzeeException() {
        // Missing /from or /to markers are caught
        QuzeeException exception = assertThrows(QuzeeException.class, () ->
                Parser.parse("event test /from 21/2/2026 2359", new ArrayList<>()));
        assertTrue(exception.getMessage().contains("Invalid input format"));
    }
}
