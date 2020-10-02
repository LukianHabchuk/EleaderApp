package service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static constants.FileNames.INPUT_FILE_NAME;

public class ReadXML {

    public List<String> readFile() {

        List<String> lines = new ArrayList<>();
        File accountsFile = new File(INPUT_FILE_NAME);

        try (Scanner scanner = new Scanner(accountsFile)) {
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            Logger.getAnonymousLogger().log(Level.WARNING, "No file to read.");
        }
        Logger.getAnonymousLogger().log(Level.INFO, "file read successfully");
        return lines;
    }
}
