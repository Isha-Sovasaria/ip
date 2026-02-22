# AI Usage Declaration

## Tools Used
- ChatGPT (OpenAI)
- Cursor Agent (AI code assistant)

## How AI Was Used

AI tools were used as coding assistants to improve productivity and reduce
manual boilerplate coding. The tools were mainly used for:

- Generating initial drafts of command classes
- Suggesting refactoring improvements (e.g., reducing redundancy, improving SLAP)
- Explaining design decisions and debugging issues
- Suggesting meaningful assert statements
- Improving commit messages and documentation wording
- Help refactor code into methods and also to better make use of SLAP
- help with accurate exception handling 

Most generated code was reviewed, modified, and adapted manually to fit
the project architecture and requirements. AI suggestions were not used
verbatim without verification.

## Prompts Used 
- Write a Storage class in Java that handles loading and saving tasks to a file on disk. The tasks can be of three types: ToDo, Deadline, and Event. Use java.nio.file.Files and java.nio.file.Path to read and write data to a file. The class should ensure that the file exists, creating it if necessary. Each task should be stored in a specific format and parsed when the program starts. Tasks should be stored in the following format:
T | 0 | Task description
D | 1 | Task description | YYYY-MM-DD (for deadlines)
E | 0 | Task description | YYYY-MM-DD | YYYY-MM-DD (for events)
After loading tasks, parse each task and reconstruct the appropriate object based on the stored format. Save the tasks back to the file when updated.

- Write a Java function that parses a deadline command string and creates a corresponding task. The command should be in the following format:
  deadline <description> /by <yyyy-mm-dd>
  The function should handle exceptions for missing or malformed descriptions and dates. If the command doesn't include /by, it should throw an exception with an appropriate message.
  Additionally, if the date is not in the correct format (yyyy-mm-dd), it should throw a TrackrException with a clear error message. The function should return an AddCommand with a new Deadline task."

- Write a Java function that parses a deadline command string and creates a corresponding task. The command should be in the following format:
  deadline <description> /by <yyyy-mm-dd>
  The function should handle exceptions for missing or malformed descriptions and dates. If the command doesn't include /by, it should throw an exception with an appropriate message.
  Additionally, if the date is not in the correct format (yyyy-mm-dd), it should throw a TrackrException with a clear error message. The function should return an AddCommand with a new Deadline task."

## Observations

- AI significantly sped up boilerplate generation (e.g., command classes).
- AI was helpful for refactoring and improving code clarity.
- However, careful manual review was required to ensure correctness and
  compliance with project constraints.
- AI occasionally suggested overly complex solutions, which were simplified manually.

Overall, AI tools improved development speed while still requiring
strong understanding of the codebase to integrate suggestions properly.
