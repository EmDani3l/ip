package memz;

import memz.tasks.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void load(TaskList tasks) {
        File f = new File(filePath);
        if (!f.exists()) return;

        try (Scanner s = new Scanner(f)) {
            while (s.hasNext()) {
                String[] parts = s.nextLine().split(" \\| ");
                Task t = null;
                switch (parts[0]) {
                case "T":
                    t = new Todo(parts[2]);
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
                    if (parts[1].equals("1")) t.markAsDone();
                    tasks.add(t);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }

    public void save(TaskList tasks) {
        try {
            new File("data").mkdirs(); // Create dir if missing
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