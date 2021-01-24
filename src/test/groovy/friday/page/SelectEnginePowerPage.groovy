package friday.page

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

class SelectEnginePowerPage extends Page {

    private WebDriver driver

    private static final String TITLE = "wizardTitle"
    private static final String PAGE_URL = "/selectEnginePower"
    private static final By ENGINE_POWER_LIST = By.name("enginePower")

    SelectEnginePowerPage(WebDriver driver) {
        super(driver)
        this.driver = driver
    }

    void selectEnginePowerFromList() {
        if (isPageLoaded()) selectRandomEnginePower()
    }

    private boolean isPageLoaded() {
        waitUntilSpinnerIsNotVisible()
        waitForVisible(getElementByDataTest(TITLE))
        def currentUrl = getUrl()
        currentUrl.contains(PAGE_URL)
    }

    private void selectRandomEnginePower() {
        selectRandom(ENGINE_POWER_LIST)
    }

}
