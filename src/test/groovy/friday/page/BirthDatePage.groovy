package friday.page

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

class BirthDatePage extends Page {

    private WebDriver driver

    private static final String TITLE = "wizardTitle"
    private static final String PAGE_URL = "/enterBirthDate"
    private static final By BIRTH_DAY_INPUT = By.name("birthDate")

    BirthDatePage(WebDriver driver) {
        super(driver)
        this.driver = driver
    }

    void isPageLoaded() {
        waitUntilSpinnerIsNotVisible()
        waitForVisible(getElementByDataTest(TITLE))
        def currentUrl = getUrl()
        currentUrl.contains(PAGE_URL) && isVisible(BIRTH_DAY_INPUT)
    }
}
