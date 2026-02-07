package trackr;
import trackr.command.AddCommand;
import trackr.command.Command;
import trackr.command.DeleteCommand;
import trackr.command.ExitCommand;
import trackr.command.ListCommand;
import trackr.command.MarkCommand;
import trackr.command.UnmarkCommand;

import trackr.task.ToDo;
import trackr.task.Deadline;
import trackr.task.Event;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Parses user input strings and converts them into executable commands.
 * The parser validates command formats and extracts required arguments.
 */
public class Parser {

    /**
     * Parses the given user input and returns the corresponding command.
     *
     * @param input The raw user input string.
     * @return The command representing the user instruction.
     * @throws TrackrException If the command is invalid or incomplete.
     */
    public Command parse(String input) throws TrackrException {
        input = input.trim();

        if (input.equals("bye")) {
            return new ExitCommand();

        } else if (input.equals("list")) {
            return new ListCommand();

        } else if (input.startsWith("mark")) {
            return new MarkCommand(parseIndex(input,
                    "Please specify a valid task number."));

        } else if (input.startsWith("unmark")) {
            return new UnmarkCommand(parseIndex(input,
                    "Please specify a valid task number."));

        } else if (input.startsWith("delete")) {
            return new DeleteCommand(parseIndex(input,
                    "Please specify a valid task number to delete."));

        } else if (input.equals("todo") || input.startsWith("todo ")) {
            if (input.equals("todo")) {
                throw new TrackrException(
                        "The description of a todo cannot be empty.");
            }
            return new AddCommand(new ToDo(input.substring(5)));

        } else if (input.equals("deadline") || input.startsWith("deadline ")) {
            return parseDeadline(input);

        } else if (input.equals("event") || input.startsWith("event ")) {
            return parseEvent(input);

        } else {
            throw new TrackrException("Sorry, I don't understand that command.");
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

        String[] parts = input.substring(9).split(" /by ");
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

        String[] parts = input.substring(6).split(" /from | /to ");

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
