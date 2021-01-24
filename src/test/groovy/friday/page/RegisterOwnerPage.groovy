package friday.page

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

class RegisterOwnerPage extends Page {
    private WebDriver driver

    private static final String TITLE = "wizardTitle"
    private static final String PAGE_URL = "/selectRegisteredOwner"
    private static final By OWNER_CAR_BUTTON = By.cssSelector('[name="registeredOwner"][value="Yes"]')
    private static final By NOT_OWNER_CAR_BUTTON = By.cssSelector('[name="registeredOwner"][value="No"]')
    private static final By USED_CAR_BUTTON = By.cssSelector('[value="used"]')
    private static final By NEW_CAR_BUTTON = By.cssSelector('[value="brandNew"]')
    private static final By CONTINUE_BUTTON = By.cssSelector('[type="submit"]')

    RegisterOwnerPage(WebDriver driver) {
        super(driver)
        this.driver = driver
    }

    void goThrough(boolean isUserCar = true , boolean isNewCar = true) {
        waitUntilPageIsLoaded()
        isUserCar ? selectThatYouAreOwner() : selectThatYouAreNotOwner()
        isNewCar ? selectNewCar() : selectUsedCar()
        clickContinueButton()
    }

    private void waitUntilPageIsLoaded() {
        waitUntilSpinnerIsNotVisible()
        waitForVisible(getElementByDataTest(TITLE))
        def currentUrl = getUrl()
        if (!currentUrl.contains(PAGE_URL)) throw new IllegalAccessException("Incorrect page is loaded")
    }

    private void selectThatYouAreOwner() {
        clickOnElement(OWNER_CAR_BUTTON)
    }

    private void selectThatYouAreNotOwner() {
        clickOnElement(NOT_OWNER_CAR_BUTTON)
    }

    private void selectNewCar() {
        clickOnElement(NEW_CAR_BUTTON)
    }

    private void selectUsedCar() {
        clickOnElement(USED_CAR_BUTTON)
    }

    private void clickContinueButton() {
        clickOnElement(CONTINUE_BUTTON)
    }
}
