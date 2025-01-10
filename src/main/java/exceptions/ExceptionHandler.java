package exceptions;

import org.openqa.selenium.*;
import utils.LogGenerator;

import java.util.NoSuchElementException;

public class ExceptionHandler {

    public static void handleException(Exception ex){
        LogGenerator logGenerator = new LogGenerator();
        switch (ex) {
            case NoSuchElementException noSuchElementException ->
                    logGenerator.error(String.format("No Such Element Found Exception caught: %s", ex.getMessage()));
            case StaleElementReferenceException staleElementReferenceException ->
                    logGenerator.error(String.format("Stale Element Reference Exception caught: %s", ex.getMessage()));
            case ElementNotInteractableException elementNotInteractableException ->
                    logGenerator.error(String.format("Element Not Interactable Found Exception caught: %s", ex.getMessage()));
            case TimeoutException timeoutException ->
                    logGenerator.error(String.format("Time out Exception caught: %s", ex.getMessage()));
            case WebDriverException webDriverException ->
                    logGenerator.error(String.format("Web Driver Exception caught: %s", ex.getMessage()));
            case WebDriverException webDriverException ->
                    logGenerator.error(String.format("Illegel State exception: %s", ex.getMessage()));
            default -> logGenerator.error(String.format("An Unknown exception caught: %s", ex.getMessage()));
        }
    }
}
