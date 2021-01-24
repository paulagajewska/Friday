package friday.page

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class RegistrationDatePage extends Page {

    private WebDriver driver

    private static final String TITLE = "wizardTitle"
    private static final String PAGE_URL = "/enterRegistrationDate"
    private static final By CONTINUE_BUTTON = By.cssSelector('[type="submit"]')
    private static final By REGISTRATION_DATE_INPUT = By.name("monthYearFirstRegistered")
    private static final By ERROR_MESSAGE = By.xpath('//*[contains(@class, "ValidationLabel")]')

    RegistrationDatePage(WebDriver driver) {
        super(driver)
        this.driver = driver
    }

    void goThrough() {
        waitUntilPageIsLoaded()
        enterRegistrationDate()
        clickContinueButton()
    }

    void fillRegistrationDate(LocalDate date){
        waitUntilPageIsLoaded()
        enterRegistrationDate(date)
        clickContinueButton()
    }

    void fillRegistrationDate(String value){
        waitUntilPageIsLoaded()
        enterRegistrationDate(value)
        clickContinueButton()
    }

    void notFillRegistrationDate(){
        waitUntilPageIsLoaded()
        clickContinueButton()
    }

    private void waitUntilPageIsLoaded() {
        waitUntilSpinnerIsNotVisible()
        waitForVisible(getElementByDataTest(TITLE))
        def currentUrl = getUrl()
        if (!currentUrl.contains(PAGE_URL)) throw new IllegalAccessException("Incorrect page is loaded")
    }

    private void enterRegistrationDate(LocalDate date = LocalDate.now()){
        String registrationDate = date.format(DateTimeFormatter.ofPattern("MM.yyyy"))
        fillInput(REGISTRATION_DATE_INPUT, registrationDate)
    }

    private void enterRegistrationDate(String value){
        fillInput(REGISTRATION_DATE_INPUT, value)
    }

    boolean checkErrorMessage(String expectedErrorMessage) {
        String errorMessage = getText(ERROR_MESSAGE)

        errorMessage == expectedErrorMessage
    }

    boolean checkIfContinueButtonIsDisable() {
        String classAttribute = getClassAttribute(CONTINUE_BUTTON)

        classAttribute.contains("Button__disabled")
    }

    private void clickContinueButton() {
        clickOnElement(CONTINUE_BUTTON)
    }

    boolean registrationInputInEmpty(){
        getText(REGISTRATION_DATE_INPUT).isEmpty()
    }
}
