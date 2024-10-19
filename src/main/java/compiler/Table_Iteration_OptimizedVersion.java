package compiler;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Table_Iteration_OptimizedVersion {

    public static WebDriver driver;
    private static String logFilePath = "custom_log.log";
    // Log generation
    // Method to write a log message to a file
    public static void log(String level, String message) throws IOException {
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
            throw new RuntimeException(e);
        }
    }
    // Info log level method
    public static void info(String message) throws IOException {
        log("INFO", message);
    }
    // Warning log level method
    public static void warning(String message) throws IOException {
        log("Warning", message);
    }
    // Error log level method
    public static void error(String message) throws IOException {
        log("ERROR", message);
    }

    // Full Page screenshot capture method
    public static void screenshot(String newFilePath) throws IOException {

        // Capture full-page screenshot using AShot
        Screenshot screenshot = new AShot().
                shootingStrategy(ShootingStrategies.viewportPasting(2000)).
                coordsProvider(new WebDriverCoordsProvider()).
                takeScreenshot(driver);

        // Save the screenshot
        ImageIO.write(screenshot.getImage(),"png",new File(newFilePath));
    }
    public static void main(String[] args) throws InterruptedException, IOException {

        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//drivers//chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://googlechromelabs.github.io/chrome-for-testing/");
        info("application started");


        //******* table Headers ****//

        List<WebElement> listOfHeaders = driver.findElements(By.xpath("(//table/thead//tr)[1]//th"));
        ArrayList<String> HeadearsList = new ArrayList<>();

        //*****table ****//

        WebElement table = driver.findElement(By.xpath("(//table/thead//following-sibling::tbody)[1]"));
        List<WebElement> rows = table.findElements(By.xpath(".//tr"));


        //******** Headers adding in a List  *****//
        for (WebElement header : listOfHeaders) {
            HeadearsList.add(header.getText());
        }
        System.out.println(HeadearsList);

        //******** adding table into a List of map  *****//
        List<Map<String, String>> list = new ArrayList<>();
        info("Performing an operation for first table");
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.xpath(".//th"));
            List<WebElement> cellsh = row.findElements(By.xpath(".//td"));
            cells.addAll(cellsh);

            if (cells.size() == HeadearsList.size()) {
                LinkedHashMap<String, String> rowdata = new LinkedHashMap<>();
                for (int i = 0; i < cells.size(); i++) {
                    rowdata.put(HeadearsList.get(i), cells.get(i).getText());

                }
                list.add(rowdata);

            }


        }
        //*** Print the table ***//

        for (Map<String, String> row : list) {
            System.out.println(row);
        }
        info("1st table-data collection is successful");

        // *** now clicking on first channel which is "stable" to open a new window***//

        WebElement Stable = driver.findElement(By.xpath("(//table/thead//following-sibling::tbody)[1]//tr[1]//th"));

        Actions a = new Actions(driver);
        a.keyDown(Keys.LEFT_CONTROL).click(Stable).build().perform();

        //*** windows handling part ***///
        String parent = driver.getWindowHandle();
        Set<String> setOfchilds = driver.getWindowHandles();
        info("performing to open a new tab");
        for (String child : setOfchilds) {
            if (!parent.equalsIgnoreCase(child)) {
                driver.switchTo().window(child);
                System.out.println("currently we are on second page");

                //***** taking screenshot for child page
               screenshot("C:\\Users\\USER\\Documents\\DemoScreenshots\\childpage.png");
                Thread.sleep(1000);
                driver.close();
            }
            driver.switchTo().window(parent);
        }
        System.out.println("we moved on first page");

        //***** taking screenshot for parent page
        screenshot("C:\\Users\\USER\\Documents\\DemoScreenshots\\parentpage.png");
        driver.quit();
    }
}
