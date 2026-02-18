Memz Chatbot (Adapted from Duke.java)
Memz is a CLI task manager for tracking Todos, Deadlines, and Events.

How to Run (Developer Mode):
Compile: javac -cp src/main/java -d bin src/main/java/memz/Memz.java
Run: java -cp bin memz.Memz

How to Run (User Mode):
Locate the memz.jar file.
Open a terminal in the folder containing the jar.
Run: java -jar memz.jar

Command Guide:
todo <description>: Adds a basic task. Example: todo read book.
deadline <description> /by <time>: Adds a task with a deadline. Example: deadline return book /by Sunday.
event <description> /from <start> /to <end>: Adds a task with a timeframe. Example: event meeting /from 2pm /to 4pm.
list: Shows all current tasks.
delete <index>: Removes the task at the given number. Example: delete 1.
mark <index>: Marks a task as completed. Example: mark 1.
unmark <index>: Marks a task as not done. Example: unmark 1.
bye: Exits the program.

Note: Tasks are saved automatically to data/memz.txt.