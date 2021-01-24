package friday.page

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

class GdprPage extends Page {
    private WebDriver driver

    private static final By ALERT_BANNER = By.id("uc-banner-centered")
    private static final By ACCEPT_BUTTON = By.id("uc-btn-accept-banner")

    GdprPage(WebDriver driver) {
        super(driver)
        this.driver = driver
    }

    void acceptAlert(){
        if (isVisible(ALERT_BANNER)){
            clickOnElement(ACCEPT_BUTTON)
        }
    }

}
