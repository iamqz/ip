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
        assertEquals("For some reason, I do not know \"INVALID\"!", quzeeException.getMessage());
    }
}
