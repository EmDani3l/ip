memz.Memz Chatbot - Adapted from Duke
memz.Memz is a CLI task manager that tracks your Todos, Deadlines, and Events.

How to Run
1. Open a terminal in the project folder.
2. Compile the code:
   `javac -cp src/main/java -d bin src/main/java/memz/Memz.java`
3. Run the bot:
   `java -cp bin memz.Memz`

User Guide
1. Add a memz.tasks.Todo Adds a basic task without a date. Format: todo <description> Example: todo read book

2. Add a memz.tasks.Deadline Adds a task that needs to be done by a specific time. You must use /by. Format: deadline <description> /by <date/time> Example: deadline return book /by Sunday

3. Add an memz.tasks.Event Adds a task that starts and ends at specific times. You must use /from and /to. Format: event <description> /from <start> /to <end> Example: event project meeting /from Mon 2pm /to 4pm

4. List Tasks Shows all tasks currently in your list. Command: list

5. Mark memz.tasks.Task as Done Marks a specific task as completed. Format: mark <task_number> Example: mark 1

6. Unmark memz.tasks.Task Marks a completed task as not done. Format: unmark <task_number> Example: unmark 1

7. Exit Exits the program. Command: bye