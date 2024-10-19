package compiler;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Page_Identifier_Property {

        // Create a Properties object
        public static Properties properties = new Properties();
        static String Projectpath = System.getProperty("user.dir");
        public static void main(String[] args)
        {
            applicationGetProperties();
            pageIdentifierGetProperties();
        }
        public static void applicationGetProperties() {

            // Load the properties file
            try (InputStream input = new FileInputStream(Projectpath + "\\src\\main\\resources\\application.properties")) {
                // Load the properties from the input stream
                properties.load(input);

                // Access the properties
                //Application Properties
                String driverFilePath = properties.getProperty("driver.file.path");
                String websiteUrl = properties.getProperty("website.url");
                String screenshotFilePath = properties.getProperty("screenshot.file.path");

                // Print the values
                System.out.println("driver.file.path: " +driverFilePath);
                System.out.println("website.url: " +websiteUrl);
                System.out.println("screenshot.file.path: " +screenshotFilePath);

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
        public static void pageIdentifierGetProperties() {

            // Load the properties file
            try (InputStream input = new FileInputStream(Projectpath + "\\src\\main\\resources\\page_identifiers.properties")) {
                // Load the properties from the input stream
                properties.load(input);

                // Access the properties
                //Page Identifier Properties
                String headerListXpath = properties.getProperty("header.list.xpath");
                String firstTableXpath = properties.getProperty("first.table.xpath");
                String tableRowXpath = properties.getProperty("table.row.xpath");
                String tableHeaderColXpath = properties.getProperty("table.header.col.xpath");
                String tableColXpath = properties.getProperty("table.col.xpath");
                String stableLnkXpath = properties.getProperty("stable.lnk.xpath");

                // Print the values
                System.out.println("header.list.xpath: " +headerListXpath);
                System.out.println("first.table.xpath: " +firstTableXpath);
                System.out.println("table.row.xpath: " +tableRowXpath);
                System.out.println("table.header.col.xpath: " +tableHeaderColXpath);
                System.out.println("table.col.xpath: " +tableColXpath);
                System.out.println("stable.lnk.xpath: " +stableLnkXpath);

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

    }


