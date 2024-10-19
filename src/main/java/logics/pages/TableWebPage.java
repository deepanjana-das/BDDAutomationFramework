package logics.pages;

import logics.common.Core;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.PropertyReader;

import java.io.IOException;
import java.util.List;

public class TableWebPage {

    public PropertyReader propertyReader = new PropertyReader();

    public WebElement formTableElement(WebDriver driver) throws IOException {
        Core core = new Core();

        return core.findElementByXpath(driver, propertyReader.pageIdentifierGetProperties("first.table.xpath"));
    }

    public List<WebElement> formTableHeaderElements(WebDriver driver) throws IOException{
        Core core = new Core();

        return core.findElementsByXpath(driver, propertyReader.pageIdentifierGetProperties("header.list.xpath"));
    }

    public List<WebElement> formTableRowElements(WebElement element) throws IOException{
        Core core = new Core();

        return core.findElementsByXpathOfAnotherElement(element, propertyReader.pageIdentifierGetProperties("table.row.xpath"));
    }

    public List<WebElement> formTableRowColsHrElements(WebElement element) throws IOException{
        Core core = new Core();

        return core.findElementsByXpathOfAnotherElement(element, propertyReader.pageIdentifierGetProperties("table.header.col.xpath"));
    }

    public List<WebElement> formTableRowColsElements(WebElement element) throws IOException{
        Core core = new Core();

        return core.findElementsByXpathOfAnotherElement(element, propertyReader.pageIdentifierGetProperties("table.col.xpath"));
    }

    public WebElement formStableLinkElement(WebDriver driver) throws IOException{
        Core core = new Core();

        return core.findElementByXpath(driver, propertyReader.pageIdentifierGetProperties("stable.lnk.xpath"));
    }
}
