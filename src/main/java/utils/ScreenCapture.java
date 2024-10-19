package utils;

import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


public class ScreenCapture {

    // Full Page screenshot capture method
    public void takeScreenshot(WebDriver driver, String newFilePath) throws IOException {

        // Capture full-page screenshot using AShot
        Screenshot screenshot = new AShot().
                shootingStrategy(ShootingStrategies.viewportPasting(2000)).
                coordsProvider(new WebDriverCoordsProvider()).
                takeScreenshot(driver);

        // Save the screenshot
        ImageIO.write(screenshot.getImage(),"png",new File(newFilePath));
    }
}
