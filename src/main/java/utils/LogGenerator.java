package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogGenerator {

    private String logFilePath = ".\\logs\\custom_log.log";
    // Log generation
    // Method to write a log message to a file
    public void log(String level, String message) {
        // Get the current timestamp
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        // Format the log message
        String logMessage = String.format("[%s] [%s]: %s", timestamp, level, message);

        // Write the log message to the file
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(logFilePath,true));
            writer.write(logMessage);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            System.err.println("Failed to write log message: " + e.getMessage());
        }
    }
    // Info log level method
    public void info(String message) {
        log("INFO", message);
    }
    // Warning log level method
    public void warning(String message) {
        log("Warning", message);
    }
    // Error log level method
    public void error(String message) {
        log("ERROR", message);
    }

}
