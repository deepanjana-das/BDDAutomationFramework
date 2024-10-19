package compiler;

import logics.services.TableWebPageImpl;

import java.io.IOException;

public class WebTableOperations {

    public static void main(String[] args) throws IOException, InterruptedException {
        TableWebPageImpl tableWebPageImpl = new TableWebPageImpl();

        // print table data
        tableWebPageImpl.printFirstTableDetails();

        // capture SS from different tabs
        tableWebPageImpl.moveTabToTabCaptureScreenShots();
    }
}
