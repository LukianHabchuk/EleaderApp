package service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static constants.FileNames.OUTPUT_FILE_NAME;

public class WriteXML {

    public void writeFile(List<String> lines) throws IOException {
        Files.write(Paths.get(OUTPUT_FILE_NAME), lines);
        Logger.getAnonymousLogger()
                .log(Level.INFO, "file written successfully");
    }
}