package memz;

import memz.tasks.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Handles saving and loading tasks from a file on the hard disk.
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file into the provided TaskList.
     * Uses Scanner to read contents line-by-line as shown in FileReadingDemo.
     *
     * @param tasks The TaskList to populate.
     */
    public void load(TaskList tasks) {
        File f = new File(filePath);

        if (!f.exists()) {
            return; // File doesn't exist yet, nothing to load
        }

        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String line = s.nextLine();
                Task t = parseLineToTask(line);
                if (t != null) {
                    tasks.add(t);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    /**
     * Translates a formatted string from the text file back into a Task object.
     */
    private Task parseLineToTask(String line) {
        // Split by the delimiter " | "
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            return null;
        }
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String desc = parts[2];

        Task t = null;
        switch (type) {
        case "T":
            t = new Todo(desc);
            break;
        case "D":
            if (parts.length >= 4) t = new Deadline(desc, parts[3]);
            break;
        case "E":
            if (parts.length >= 5) t = new Event(desc, parts[3], parts[4]);
            break;
        }

        if (t != null && isDone) {
            t.markAsDone();
        }
        return t;
    }

    /**
     * Saves the current list of tasks to the hard disk.
     * Uses FileWriter to overwrite the file as shown in FileWritingDemo.
     *
     * @param tasks The TaskList to save.
     */
    public void save(TaskList tasks) {
        File dir = new File("data");
        if (!dir.exists()) {
            dir.mkdir();
        }

        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < tasks.size(); i++) {
                fw.write(tasks.get(i).toSaveFormat() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}