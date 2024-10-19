package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    public LogGenerator logGenerator = new LogGenerator();
    // Create a Properties object
    public Properties applicationproperties = new Properties();
    public Properties pageIdentifierproperties = new Properties();



    // Method to read from application.properties
    public String applicationGetProperties(String key) throws IOException {

        String value = null;
        InputStream input = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\application.properties");

        // Load properties from application.properties
        applicationproperties.load(input);
        // Fetch the value for the provided key
        value = applicationproperties.getProperty(key);
        logGenerator.info("success, able to find application.properties");
        return value;
    }
    // Method to read from page_identifier.properties
    public String pageIdentifierGetProperties(String key) throws IOException {

        String value = null;
        InputStream input = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\page_identifiers.properties");

        // Load properties from pageIdentifier.properties
        pageIdentifierproperties.load(input);
        // Fetch the value for the provided key
        value = pageIdentifierproperties.getProperty(key);
        logGenerator.info("success, able to find page_identifier.properties");
        return value;
    }

}
