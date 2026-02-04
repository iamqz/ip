package quzee;

import org.junit.jupiter.api.Test;
import quzee.command.ExitCommand;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ParserTest {

    @Test
    public void test_parse_validBye_returnsExitCommand() throws QuzeeException {
        assertTrue(Parser.parse("bye", new ArrayList<>()) instanceof ExitCommand);
    }

    @Test
    public void test_parse_unknownCommand_returnsQuzeeException() throws QuzeeException {
        QuzeeException quzeeException = assertThrows(QuzeeException.class, () ->
                Parser.parse("INVALID", new ArrayList<>()));
        assertEquals("For some reason, I do not know \"INVALID\"!", quzeeException.getMessage());
    }
}
