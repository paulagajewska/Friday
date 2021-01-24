package friday.result

import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.WebDriver
import org.openqa.selenium.io.FileHandler

import java.time.LocalDateTime

class ScreenShoot {

    static void takeScreenShoot(WebDriver driver) {
        TakesScreenshot screenshot = (TakesScreenshot) driver
        File source = screenshot.getScreenshotAs(OutputType.FILE)
        FileHandler.copyFile(source, new File("src/test/resources/screenshoots/" + LocalDateTime.now() + ".png"));
    }
}
