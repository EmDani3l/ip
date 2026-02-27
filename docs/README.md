# Memz Chatbot - User Guide

Memz is a CLI-based task manager designed for those who want a sarcastic yet functional way to track their daily grind. Stop procrastinating and start tracking.

## Table of Contents

1. Adding Tasks

2. Managing Tasks

3. Searching

4. Exiting

## 1. Adding Tasks

### todo

Adds a basic task without any date constraints.

Format: todo {description}

Example: todo read book

### deadline

Adds a task that must be completed by a specific date or time.

Format: deadline {description} /by yyyy-mm-dd

or

Format: deadline {description} /by {when}

Examples: 

deadline return book /by 2026-12-01

deadline return book /by next Friday

### event

Adds a task that occurs within a specific timeframe.

Format: event {description} /from yyyy-mm-dd /to yyyy-mm-dd

or

Format: event {description} /from {when} /to {when}

Examples: 

event project meeting /from 2026-03-01 /to 2026-03-02

event project meeting /from today /to tomorrow

## 2. Managing Tasks

### list

Displays all current tasks in your list.

Format: list

### mark

Marks a task as completed.

Format: mark {index}

Example: mark 1

### unmark

Reverts a completed task back to "not done".

Format: unmark {index}

Example: unmark 1

### delete

Removes a task permanently from the list.

Format: delete {index}

Example: delete 2

## 3. Searching

### find

Filters the list to show only tasks containing a specific keyword in the description.

Format: find {keyword}

Example: find book

## 4. Exiting

### bye

Saves your data and closes the application.

Format: bye

## Additional Details

Dates: Always use the yyyy-mm-dd format (Year-Month-Day) for deadlines and events, or Memz will complain.

Persistence: Your tasks are automatically saved to ./data/memz.txt every time you enter a command.