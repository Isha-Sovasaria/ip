# Trackr User Guide

Trackr is a chatbot that helps you manage your tasks. You can add todos, deadlines, and events, mark them as done, search for tasks, and update them—all from a simple chat interface.

Trackr can be used in two ways:
- **Command-line interface (CLI)** – Type commands in a terminal
- **Graphical user interface (GUI)** – Chat with Trackr in a window

---

## Quick Start

### Running the GUI

**Using the JAR file** :
   ```
   java -jar trackr.jar
   ```

A window will open. Type your message in the text box at the bottom and press Enter or click Send.

---

### Running the CLI

**Using the JAR file** :
   ```
   java -cp trackr.jar trackr.Trackr
   ```

You will see a greeting in the terminal. Type commands and press Enter after each command.

---

## Features

### Notes about the command format

- Words in UPPER_CASE are parameters to be supplied by you.
- e.g. in todo DESCRIPTION, DESCRIPTION is a parameter such as todo Buy groceries.
-  Dates must be in yyyy-mm-dd format (e.g. 2025-03-15).
- INDEX refers to the task number shown in the list (1, 2, 3, …).
-  Commands must follow the specified format exactly.
  e.g. mark1 is invalid; use mark 1.
-  Required keywords such as /by, /from, and /to must be included where specified.
-  If required fields are missing (e.g. missing description or date), Trackr will display a specific error message.
-  Commands are case-sensitive. Use lowercase (e.g. todo, not Todo).
- Extraneous parameters for commands that do not take them (such as list and bye) are ignored.
 e.g. list 123 is interpreted as list. 
- If copying multi-line commands, ensure spaces are preserved.

---

### Viewing all tasks: `list`

Shows all tasks in your list.

**Format:** `list`

**Example:**
```
list
```

---

### Adding a todo: `todo`

Adds a todo task with a description only (no date).

**Format:** `todo DESCRIPTION`

- `DESCRIPTION` cannot be empty.

**Examples:**
```
todo Buy groceries
todo Return library book
```

---

### Adding a deadline: `deadline`

Adds a task with a description and due date.

**Format:** `deadline DESCRIPTION /by DATE`

- `DESCRIPTION` cannot be empty.
- `DATE` must be in **yyyy-mm-dd** format (e.g. `2025-03-15`).

**Examples:**
```
deadline Submit report /by 2025-03-15
deadline Pay bills /by 2025-02-28
```

---

### Adding an event: `event`

Adds an event with a description, start date, and end date.

**Format:** `event DESCRIPTION /from START_DATE /to END_DATE`

- `DESCRIPTION` cannot be empty.
- Both `/from` and `/to` are required.
- `START_DATE` and `END_DATE` must be in **yyyy-mm-dd** format.

**Examples:**
```
event Team meeting /from 2025-02-20 /to 2025-02-20
event Conference /from 2025-03-01 /to 2025-03-03
```

---

### Marking a task as done: `mark`

Marks the specified task as completed.

**Format:** `mark INDEX`

- `INDEX` must be a valid task number from the list (1, 2, 3, …​).

**Example:**
```
list
mark 2
```

---

### Marking a task as not done: `unmark`

Marks the specified task as not yet completed.

**Format:** `unmark INDEX`

**Example:**
```
unmark 2
```

---

### Deleting a task: `delete`

Removes the specified task from the list.

**Format:** `delete INDEX`

**Example:**
```
delete 3
```

---

### Finding tasks: `find`

Finds tasks whose descriptions contain the given keyword.

**Format:** `find KEYWORD`

- `KEYWORD` cannot be empty.
- Search is case-sensitive.

**Examples:**
```
find meeting
find report
```

---

### Updating a task: `update`

Updates an existing task. You can change the description, due date (for deadlines), or start/end dates (for events).

**Format:** `update INDEX /desc NEW_DESCRIPTION`  
**Format:** `update INDEX /by NEW_DATE` *(Deadline tasks only)*  
**Format:** `update INDEX /from NEW_START /to NEW_END` *(Event tasks only)*

- At least one field (`/desc`, `/by`, or `/from`/`/to`) must be provided.
- Dates must be in **yyyy-mm-dd** format.
- For events, both `/from` and `/to` must be given together.

**Examples:**
```
update 1 /desc Buy milk and bread
update 2 /by 2025-04-01
update 3 /from 2025-03-05 /to 2025-03-07
```

---

### Exiting the program: `bye`

Exits Trackr.

**Format:** `bye`

---

## Saving the data

Task data is saved automatically after any command that changes the list. You do not need to save manually.

---

## Editing the data file

Task data is stored in `data/trackr.txt` (relative to where you run the application). Each line represents one task.

**Format:**
- Todo: `T | DONE | DESCRIPTION`
- Deadline: `D | DONE | DESCRIPTION | DATE`
- Event: `E | DONE | DESCRIPTION | START_DATE | END_DATE`

- `DONE` is `1` if the task is marked done, `0` otherwise.
- Fields are separated by ` | ` (space, pipe, space).
- Dates are in **yyyy-mm-dd** format.

**Examples:**
```
T | 0 | Buy groceries
D | 1 | Submit report | 2025-03-15
E | 0 | Team meeting | 2025-02-20 | 2025-02-20
```

If you edit this file directly, ensure the format is correct. Invalid lines may cause errors when Trackr loads.

---

## Command summary

| Command | Format | Description |
|---------|--------|-------------|
| `list` | `list` | View all tasks |
| `todo` | `todo DESCRIPTION` | Add a todo |
| `deadline` | `deadline DESCRIPTION /by DATE` | Add a deadline |
| `event` | `event DESCRIPTION /from DATE /to DATE` | Add an event |
| `mark` | `mark INDEX` | Mark task as done |
| `unmark` | `unmark INDEX` | Mark task as not done |
| `delete` | `delete INDEX` | Delete a task |
| `find` | `find KEYWORD` | Search tasks by keyword |
| `update` | `update INDEX /desc DESC` or `/by DATE` or `/from DATE /to DATE` | Update a task |
| `bye` | `bye` | Exit the program |
