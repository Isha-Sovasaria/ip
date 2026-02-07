package trackr.task;

import org.junit.jupiter.api.Test;

import trackr.Parser;
import trackr.TrackrException;
import trackr.command.*;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    private final Parser parser = new Parser();

    @Test
    public void parse_listCommand_success() throws Exception {
        Command command = parser.parse("list");
        assertInstanceOf(ListCommand.class, command);
    }

    @Test
    public void parse_exitCommand_success() throws Exception {
        Command command = parser.parse("bye");
        assertInstanceOf(ExitCommand.class, command);
    }

    @Test
    public void parse_todoCommand_success() throws Exception {
        Command command = parser.parse("todo read book");
        assertInstanceOf(AddCommand.class, command);
    }

    @Test
    public void parse_markCommand_success() throws Exception {
        Command command = parser.parse("mark 1");
        assertInstanceOf(MarkCommand.class, command);
    }

    @Test
    public void parse_markWithNonNumber_throwsException() {
        TrackrException e = assertThrows(TrackrException.class, () ->
                parser.parse("mark abc")
        );

        assertEquals("Please specify a valid task number.", e.getMessage());
    }

    @Test
    public void parse_markWithoutIndex_throwsException() {
        TrackrException e = assertThrows(TrackrException.class, () ->
                parser.parse("mark")
        );

        assertEquals("Please specify a valid task number.", e.getMessage());
    }

    @Test
    public void parse_todoWithoutDescription_throwsException() {
        TrackrException e = assertThrows(TrackrException.class, () ->
                parser.parse("todo")
        );

        assertEquals("The description of a todo cannot be empty.", e.getMessage());
    }

    @Test
    public void parse_unknownCommand_throwsException() {
        TrackrException e = assertThrows(TrackrException.class, () ->
                parser.parse("fly")
        );

        assertEquals("Sorry, I don't understand that command.", e.getMessage());
    }
}
