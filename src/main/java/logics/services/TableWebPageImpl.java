package logics.services;

import logics.common.Core;
import logics.pages.TableWebPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.LogGenerator;
import utils.ScreenCapture;

import java.io.IOException;
import java.util.*;

public class TableWebPageImpl {
    public LogGenerator logGenerator = new LogGenerator();
    public ScreenCapture screenCapture = new ScreenCapture();

    public void printFirstTableDetails() throws IOException {
        Core core = new Core();
        TableWebPage tableWebPage = new TableWebPage();

        // driver initialize and open website
        WebDriver driver = core.openApplication();

        // get the list of table headers
        List<WebElement> listOfHeaders = tableWebPage.formTableHeaderElements(driver);

        if (listOfHeaders.size() >0) {
            logGenerator.info("listOfHeaders is successfully loaded with headers");
        }
        else {
            logGenerator.error("listOfHeaders is empty");
        }
        ArrayList<String> headearsList = new ArrayList<>();
        for (WebElement header : listOfHeaders) {
            headearsList.add(header.getText());
        }

        logGenerator.info("First table headers are successfully loaded in headearsList");

        // get the table element
        WebElement table  = tableWebPage.formTableElement(driver);

        // get table rows
        List<WebElement> rows = tableWebPage.formTableRowElements(table);
        if (rows.size() >0) {
            logGenerator.info("rows of tables are successfully loaded");
        }
        else {
            logGenerator.error("rows of tables get empty");
        }
        // preparing table data
        List<Map<String, String>> list = new ArrayList<>();
        logGenerator.info("Performing an operation for first table");
        for (WebElement row : rows) {
            List<WebElement> cellsh = tableWebPage.formTableRowColsHrElements(row);
            List<WebElement> cells = tableWebPage.formTableRowColsElements(row);
            cells.addAll(cellsh);

            if (cells.size() >0) {
                logGenerator.info("cells of table are successfully loaded");
            }
            else {
                logGenerator.error("cells of table get empty");
            }

            if (cells.size() == headearsList.size()) {
                LinkedHashMap<String, String> rowdata = new LinkedHashMap<>();
                for (int i = 0; i < cells.size(); i++) {
                    rowdata.put(headearsList.get(i), cells.get(i).getText());
                }
                list.add(rowdata);

            }


        }
        //*** Print the table ***//

        for (Map<String, String> row : list) {
            System.out.println(row);
        }
        logGenerator.info("1st table-data collection is successful");

        core.closeBrowser(driver);

    }

    public void moveTabToTabCaptureScreenShots() throws IOException, InterruptedException {
        Core core = new Core();
        TableWebPage tableWebPage = new TableWebPage();

        WebDriver driver = core.openApplication();
        WebElement stableLink = tableWebPage.formStableLinkElement(driver);

        // click on stable link
        core.performLeftClickOnElement(driver, stableLink);

        //*** windows handling part ***///
        String parent = core.getCurrentBrowserTab(driver);
        Set<String> setOfChilds = core.getBrowserTabs(driver);
        logGenerator.info("performing to open a new tab");
        for (String child : setOfChilds) {
            if (!parent.equalsIgnoreCase(child)) {
                core.switchWindow(driver, child);
                logGenerator.info("currently we are on second page");

                //***** taking screenshot for child page
                Random random = new Random();
                int randomNumber = random.nextInt(10000);
                screenCapture.takeScreenshot(driver, ".\\screenshots\\childpage_"+randomNumber+".png");
                Thread.sleep(1000);

                core.closeBrowserTab(driver);
            }
            core.switchWindow(driver, parent);
        }
        logGenerator.info("we moved on first page");

        //***** taking screenshot for parent page
        Random random = new Random();
        int randomNumber = random.nextInt(10000);
        screenCapture.takeScreenshot(driver, ".\\screenshots\\parentpage_"+randomNumber+".png");

        core.closeBrowser(driver);
    }
}
