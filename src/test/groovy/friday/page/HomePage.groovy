package friday.page


import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

class HomePage extends Page {

    private WebDriver driver
    private GdprPage gdprPage

    private static final String title = "wizardTitle"
    private static final By CHANGE_OF_INSURER_BUTTON = By.cssSelector('[value="keepingCar"]')
    private static final By NEW_INSURER_BUTTON = By.cssSelector('[value="buyingCar"]')
    private static final By INCEPTION_DATE_INPUT = By.name("inceptionDate")
    private static final By SUBMIT_BUTTON = By.cssSelector('[type="submit"]')
    private static final By ERROR_MESSAGE = By.xpath('//*[contains(@class, "ValidationLabel")]')
    private static final By INFORMATION_MESSAGE = By.xpath('//*[contains(@class, "HelpText")]')

    HomePage(WebDriver driver) {
        super(driver)
        this.driver = driver
        driver.get("https://hello.friday.de/quote/selectPrecondition")
        gdprPage = new GdprPage(driver)
    }

    void fillStartInsuranceDay(String startDayValue) {
        gdprPage.acceptAlert()
        waitUntilPageIsLoaded()
        fillInscuranceStartDay(startDayValue)
        clickSubmitButton()
    }

    void goThrough(boolean isNewInsurer = false) {
        gdprPage.acceptAlert()
        waitUntilPageIsLoaded()
        if (isNewInsurer) {
            selectNewInsurer()
            checkIfInfirmationMessageIsVisible()
        } else {
            selectChangeInsurer()
        }
        clickSubmitButton()
    }

    boolean checkErrorMessage(String expectedErrorMessage) {
        String errorMessage = getText(ERROR_MESSAGE)

        errorMessage.equals(expectedErrorMessage)
    }

    boolean checkIfSubmitButtonIsDisable() {
        String classAttribute = getClassAttribute(SUBMIT_BUTTON)

        classAttribute.contains("Button__disabled")
    }

    boolean startInsuranceInputIsEmpty() {
        getText(INCEPTION_DATE_INPUT).isEmpty()
    }

    private void waitUntilPageIsLoaded() {
        waitForVisible(getElementByDataTest(title))
    }

    private void selectNewInsurer() {
        clickOnElement(NEW_INSURER_BUTTON)
    }

    private void selectChangeInsurer() {
        clickOnElement(CHANGE_OF_INSURER_BUTTON)
    }

    private void fillInscuranceStartDay(String value) {
        fillInput(INCEPTION_DATE_INPUT, value)
    }

    private void clickSubmitButton() {
        clickOnElement(SUBMIT_BUTTON)
    }

    private void checkIfInfirmationMessageIsVisible() {
        waitForVisible(INFORMATION_MESSAGE)
    }
}