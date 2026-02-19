package memz;

import memz.tasks.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Manages reading and writing tasks to the hard disk.
 */
public class Storage {
    private final String filePath;

    /**
     * Initializes storage with the specified file path.
     * @param filePath Path to the save file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file into the TaskList.
     * @param tasks The list to populate with saved data.
     */
    public void load(TaskList tasks) {
        File f = new File(filePath);
        if (!f.exists()) {
            return;
        }

        try (Scanner s = new Scanner(f)) {
            while (s.hasNextLine()) {
                String line = s.nextLine();
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split(" \\| ");
                Task t = null;

                switch (parts[0]) {
                case "T":
                    if (parts.length >= 3) {
                        t = new Todo(parts[2]);
                    }
                    break;
                case "D":
                    if (parts.length >= 4) {
                        t = new Deadline(parts[2], parts[3]);
                    }
                    break;
                case "E":
                    if (parts.length >= 5) {
                        t = new Event(parts[2], parts[3], parts[4]);
                    }
                    break;
                }

                if (t != null) {
                    if (parts[1].equals("1")) {
                        t.markAsDone();
                    }
                    tasks.add(t);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }

    /**
     * Saves the current tasks to the specified file.
     * @param tasks The list of tasks to be stored.
     */
    public void save(TaskList tasks) {
        try {
            // Ensure the directory exists before attempting to write the file
            File directory = new File("data");
            if (!directory.exists()) {
                directory.mkdirs();
            }

            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < tasks.size(); i++) {
                fw.write(tasks.get(i).toSaveFormat() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}