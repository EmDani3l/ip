# Memz Chatbot - User Guide

> "Want a cookie? Dowan give then how?"

Memz is a CLI-based task manager designed for those who want a sarcastic yet functional way to track their daily grind. Stop procrastinating and start tracking.

---

## Table of Contents

**I. Getting Started**
* [Installation](#installation)

**II. Task Actions**
* [Adding a Todo](#adding-a-todo)
* [Adding a Deadline](#adding-a-deadline)
* [Adding an Event](#adding-an-event)
* [Listing All Tasks](#listing-all-tasks)
* [Marking a Task to Done](#marking-a-task-to-done)
* [Unmarking a Task to Not Done](#unmarking-a-task-to-not-done)
* [Deleting a Task](#deleting-a-task)
* [Finding Tasks](#finding-tasks)

**III. General**
* [Exiting](#exiting)
* [Command Cheat Sheet](#command-cheat-sheet)
* [Data Storage](#data-storage)

---

## Installation

Prerequisites: You need **Java 17** or higher installed on your machine.

1. Download the latest `memz.jar` file from the releases.
2. Open your terminal.
3. Navigate to the folder where you downloaded the file.
4. Run the following command:
   `java -jar memz.jar`

---

# Task Actions

## Adding a Todo

Adds a task.

Format: `todo <description>` \
Example: `todo read book`

Output:
```text
Got it. I've added this task:
  [T][ ] read book
Now you have 1 tasks in the list.
```

---

## Adding a Deadline

Adds a task that must be completed by a specific date or time.
Always use the `yyyy-mm-dd` format (Year-Month-Day) for date deadlines.

Format: `deadline <description> /by <date>` OR `deadline <description> /by <time>` \
Example: `deadline return book /by 2026-12-01` OR `deadline return book /by tomorrow`

Output:
```text
Got it. I've added this task:
  [D][ ] return book (by: Dec 01 2026)
Now you have 2 tasks in the list.
```
OR

```text
Got it. I've added this task:
  [D][ ] return book (by: tomorrow)
Now you have 2 tasks in the list.
```

---

## Adding an Event

Adds a task that occurs within a specific timeframe.
Always use the `yyyy-mm-dd` format (Year-Month-Day) for events with dates.

Format: `event <description> /from <start date> /to <end date>` OR `event <description> /from <start> /to <end>` \
Example: `event project meeting /from 2026-03-01 /to 2026-03-02` OR `event project meeting /from now /to later`

Output:
```text
Got it. I've added this task:
  [E][ ] project meeting (from: Mar 01 2026 to: Mar 02 2026)
Now you have 3 tasks in the list.
```
OR

```text
Got it. I've added this task:
  [E][ ] project meeting (from: now to: later)
Now you have 3 tasks in the list.
```

---

## Listing All Tasks

Displays all current tasks in your list.

Format: `list` \
Example: `list`

Output:
```text
Here are your tasks. Stop procrastinating:
1.[T][ ] read book
2.[D][ ] return book (by: Dec 01 2026)
3.[E][ ] project meeting (from: Mar 01 2026 to: Mar 02 2026)
```

---

## Marking a Task to Done

Marks a task as completed.

Format: `mark <index>` \
Example: `mark 1`

Output:
```text
Fine. You can have this one:
  [T][X] read book
```

---

## Unmarking a Task to Not Done

Reverts a completed task back to "not done".

Format: `unmark <index>` \
Example: `unmark 1`

Output:
```text
SEE, you're not done yet:
  [T][ ] read book
```

---

## Deleting a Task

Removes a task from the list.

Format: `delete <index>` \
Example: `delete 1`

Output:
```text
Cheater. I've removed this task:
  [T][ ] read book
Now you have 2 tasks in the list.
```

---

## Finding Tasks

Search and show only tasks containing a specific keyword.

Format: `find <keyword>` \
Example: `find book`

Output:
```text
Here are the matching tasks in your list:
     1.[T][ ] read book
     2.[D][ ] return book (by: Dec 01 2026)
```

---

# General

## Exiting

Saves your data and closes the application.

Format: `bye`

Output:
```text
Bye. You better watch out.
```

---

## Command Cheat Sheet

| Command      | Format                                        | Example                                                                                                |
|:-------------|:----------------------------------------------|:-------------------------------------------------------------------------------------------------------|
| **todo** | `todo <description>`                          | `todo read book`                                                                                       |
| **deadline** | `deadline <description> /by <date or time>`   | `deadline return book /by 2026-12-01` OR `deadline return book /by tonight`                            |
| **event** | `event <description> /from <start> /to <end>` | `event project meeting /from 2026-03-01 /to 2026-03-02` OR `event project meeting /from now /to later` |
| **list** | `list`                                        | `list`                                                                                                 |
| **mark** | `mark <index>`                                | `mark 1`                                                                                               |
| **unmark** | `unmark <index>`                              | `unmark 1`                                                                                             |
| **find** | `find <keyword>`                              | `find book`                                                                                            |
| **delete** | `delete <index>`                              | `delete 1`                                                                                             |
| **bye** | `bye`                                         | `bye`                                                                                                  |

---

## Data Storage

Tasks are automatically saved to `./data/memz.txt`.