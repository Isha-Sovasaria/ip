package trackr;

import trackr.command.AddCommand;
import trackr.command.Command;
import trackr.command.DeleteCommand;
import trackr.command.ExitCommand;
import trackr.command.FindCommand;
import trackr.command.ListCommand;
import trackr.command.MarkCommand;
import trackr.command.UnmarkCommand;
import trackr.command.UpdateCommand;
import trackr.task.Deadline;
import trackr.task.Event;
import trackr.task.ToDo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Parses user input strings and converts them into executable commands.
 * The parser validates command formats and extracts required arguments.
 */
public class Parser {

    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_UPDATE = "update";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_FIND = "find";

    /**
     * Parses the given user input and returns the corresponding command.
     *
     * @param input The raw user input string.
     * @return The command representing the user instruction.
     * @throws TrackrException If the command is invalid or incomplete.
     */
    public Command parse(String input) throws TrackrException {
        input = input.trim();

        if (input.equals(COMMAND_BYE)) {
            return new ExitCommand();
        }
        if (input.equals(COMMAND_LIST)) {
            return new ListCommand();
        }
        if (input.startsWith(COMMAND_MARK)) {
            return new MarkCommand(parseIndex(input, "Please specify a valid task number."));
        }
        if (input.startsWith(COMMAND_UNMARK)) {
            return new UnmarkCommand(parseIndex(input, "Please specify a valid task number."));
        }
        if (input.startsWith(COMMAND_DELETE)) {
            return new DeleteCommand(parseIndex(input, "Please specify a valid task number to delete."));
        }
        if (input.startsWith(COMMAND_UPDATE)) {
            return parseUpdateCommand(input);
        }
        if (input.equals(COMMAND_TODO) || input.startsWith(COMMAND_TODO + " ")) {
            return parseTodoCommand(input);
        }
        if (input.equals(COMMAND_DEADLINE) || input.startsWith(COMMAND_DEADLINE + " ")) {
            return parseDeadline(input);
        }
        if (input.equals(COMMAND_EVENT) || input.startsWith(COMMAND_EVENT + " ")) {
            return parseEvent(input);
        }
        if (input.equals(COMMAND_FIND) || input.startsWith(COMMAND_FIND + " ")) {
            return parseFindCommand(input);
        }

        throw new TrackrException("Sorry, I don't understand that command.");
    }

    private Command parseTodoCommand(String input) throws TrackrException {
        if (input.equals(COMMAND_TODO)) {
            throw new TrackrException("The description of a todo cannot be empty.");
        }
        String description = input.substring(COMMAND_TODO.length() + 1);
        return new AddCommand(new ToDo(description));
    }

    private Command parseFindCommand(String input) throws TrackrException {
        if (input.equals(COMMAND_FIND)) {
            throw new TrackrException("Please specify a keyword to search for.");
        }
        String keyword = input.substring(COMMAND_FIND.length() + 1);
        return new FindCommand(keyword);
    }

    private Command parseUpdateCommand(String input) throws TrackrException {
        String rest = input.substring(COMMAND_UPDATE.length()).trim();
        if (rest.isEmpty()) {
            throw new TrackrException("Please specify a task number and update field (e.g. update 1 /desc new description).");
        }
        String[] parts = rest.split(" ", 2);
        if (parts.length < 2) {
            throw new TrackrException("Please specify an update field: /desc, /by, or /from ... /to");
        }
        try {
            int index = Integer.parseInt(parts[0]) - 1;
            String updatePart = parts[1];
            return new UpdateCommand(index, updatePart);
        } catch (NumberFormatException e) {
            throw new TrackrException("Please specify a valid task number.");
        }
    }

    /**
     * Extracts and parses the task index from commands that require an index.
     *
     * @param input The full user input string.
     * @param errorMessage The error message to use if parsing fails.
     * @return The zero-based index of the task.
     * @throws TrackrException If the task index is missing or invalid.
     */
    private int parseIndex(String input, String errorMessage)
            throws TrackrException {
        try {
            return Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (Exception e) {
            throw new TrackrException(errorMessage);
        }
    }

    /**
     * Parses a deadline command and creates the corresponding task.
     *
     * @param input The full user input string.
     * @return The command that adds a deadline task.
     * @throws TrackrException If the description or date is invalid.
     */
    private Command parseDeadline(String input)
            throws TrackrException {

        if (!input.contains(" /by ")) {
            throw new TrackrException("A deadline must have a /by date.");
        }

        String[] parts = input.substring(COMMAND_DEADLINE.length() + 1).split(" /by ");
        if (parts[0].isEmpty()) {
            throw new TrackrException(
                    "The description of a deadline cannot be empty.");
        }

        try {
            LocalDate date = LocalDate.parse(parts[1]);
            return new AddCommand(new Deadline(parts[0], date));
        } catch (DateTimeParseException e) {
            throw new TrackrException(
                    "Please use yyyy-mm-dd format for dates.");
        }
    }

    private Command parseEvent(String input)
            throws TrackrException {

        if (!input.contains(" /from ") || !input.contains(" /to ")) {
            throw new TrackrException("An event must have /from and /to.");
        }

        String[] parts = input.substring(COMMAND_EVENT.length() + 1).split(" /from | /to ");

        try {
            LocalDate from = LocalDate.parse(parts[1]);
            LocalDate to = LocalDate.parse(parts[2]);
            return new AddCommand(new Event(parts[0], from, to));
        } catch (DateTimeParseException e) {
            throw new TrackrException(
                    "Please use yyyy-mm-dd format for dates.");
        }
    }
}
