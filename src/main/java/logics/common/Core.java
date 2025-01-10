package logics.common;

import exceptions.ExceptionHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import utils.LogGenerator;
import utils.PropertyReader;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Core {

    public PropertyReader propertyReader = new PropertyReader();
    public LogGenerator logGenerator = new LogGenerator();

    public WebDriver openApplication() {
        /*
         * To initialize web driver and open a webpage in browser
         */
        try {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + propertyReader.applicationGetProperties("driver.file.path"));
            WebDriver driver;
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get(propertyReader.applicationGetProperties("website.url"));
            logGenerator.info("application is started");
            return driver;
        } catch (Exception e) {
            ExceptionHandler.handleException(e);
            return null;
        }

    }

    public WebDriver gotoWebPage(String url) {
        /*
         * To initialize web driver and open a webpage in browser
         */
        try {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + propertyReader.applicationGetProperties("driver.file.path"));
            WebDriver driver;
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get(url);
            logGenerator.info("application started");
            return driver;
        } catch (Exception e) {
            ExceptionHandler.handleException(e);
            return null;
        }

    }

    public WebElement findElementById(WebDriver driver, String elementId){
        /**
         *  To find an element by ID
         * @param driver
         * @param elementId
         * @return WebElement
         */
        try{
            return driver.findElement(By.id(elementId));
        }catch (Exception ex){
            ExceptionHandler.handleException(ex);
            driver.quit();
            return null;
        }

    }

    public WebElement findElementByXpath(WebDriver driver, String elementXpath){
        /**
         *  To find an element by Xpath
         * @param driver
         * @param elementXpath
         * @return WebElement
         */
        try{
            return driver.findElement(By.xpath(elementXpath));
        }catch (Exception ex){
            ExceptionHandler.handleException(ex);
            driver.quit();
            return null;
        }
    }

    public List<WebElement> findElementsByXpath(WebDriver driver, String elementXpath){
        /**
         *  To find list of elements by Xoath
         * @param driver
         * @param elementXpath
         * @return list of WebElement
         */
        try{
            return driver.findElements(By.xpath(elementXpath));
        }catch (Exception ex){
            ExceptionHandler.handleException(ex);
            return Collections.emptyList();
        }
    }

    public List<WebElement> findElementsByXpathOfAnotherElement(WebElement element, String elementXpath){
        /**
         *  To find list of elements by Xoath
         * @param element
         * @param elementXpath
         * @return list of WebElement
         */
        try{
            return element.findElements(By.xpath(elementXpath));
        }catch (Exception ex){
            ExceptionHandler.handleException(ex);
            return Collections.emptyList();
        }

    }

    public void performLeftClickOnElement(WebDriver driver, WebElement element){
        try{
            new Actions(driver).keyDown(Keys.LEFT_CONTROL).click(element).build().perform();
        }catch (Exception ex){
            ExceptionHandler.handleException(ex);

        }
    }

    public void switchWindow(WebDriver driver, String window){
        try {
            driver.switchTo().window(window);
        }
        catch(Exception ex){
            ExceptionHandler.handleException(ex);
            }
    }

    public void closeBrowserTab(WebDriver driver){
        driver.close();
    }

    public void closeBrowser(WebDriver driver){
        driver.quit();
    }

    public String getCurrentBrowserTab(WebDriver driver){
        try {
            return driver.getWindowHandle();
        } catch (Exception ex) {
            ExceptionHandler.handleException(ex);
        }
        return "";
    }

    public Set<String> getBrowserTabs(WebDriver driver){
        try {
            return driver.getWindowHandles();
        } catch (Exception ex) {
            ExceptionHandler.handleException(ex);
            return Collections.emptySet();
        }
    }

    // Method to check if an element is present
    public boolean isPresent(WebDriver driver, String elementXpath) {
        try {
            // Use findElement and catch the NoSuchElementException if element is not found
            WebElement element = driver.findElement(By.xpath(elementXpath));
            return element.isDisplayed();  // Optional check to see if the element is visible
        } catch (Exception ex) {
            ExceptionHandler.handleException(ex);
            return false;
        }
    }
}
