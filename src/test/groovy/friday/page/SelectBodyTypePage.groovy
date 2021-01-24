package friday.page

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

class SelectBodyTypePage extends Page {

    private WebDriver driver

    private static final String TITLE = "wizardTitle"
    private static final String PAGE_URL = "/selectBodyType"
    private static final By BODY_TYPE_LIST = By.name("bodyType")

    SelectBodyTypePage(WebDriver driver) {
        super(driver)
        this.driver = driver
    }

    void selectBodyTypeFromList() {
        if (isPageLoaded()) selectRandomBodyType()
    }

    private boolean isPageLoaded() {
        waitUntilSpinnerIsNotVisible()
        waitForVisible(getElementByDataTest(TITLE))
        def currentUrl = getUrl()
        return currentUrl.contains(PAGE_URL)
    }

    private void selectRandomBodyType() {
        selectRandom(BODY_TYPE_LIST)
    }

}
