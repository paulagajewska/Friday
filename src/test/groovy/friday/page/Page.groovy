package friday.page

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.WebDriverWait

import java.time.Duration

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElements

class Page {

    private WebDriver driver
    private static Random random = new Random()
    private static final MAX_TIMEOUT_SECONDS = 10

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(MAX_TIMEOUT_SECONDS))

    Page(WebDriver driver) {
        this.driver = driver
    }

    void waitForVisible(By locator) {
        wait.until(presenceOfElementLocated(locator))
    }

    void waitForVisible(WebElement element) {
        wait.until(visibilityOfAllElements(element))
    }

    void waitUntilSpinnerIsNotVisible(){
        //TODO Try to catch spinner and wait until is not present
        sleep(2000)
    }

    WebElement getWebElement(By locator) {
        driver.findElement(locator)
    }

    List<WebDriver> getWebElements(By locator) {
        driver.findElements(locator)
    }

    WebElement getElementByDataTest(String value) {
        getWebElement(By.cssSelector('[data-test-id="' + value + '"]'))
    }

    void clickOnElement(By locator) {
        waitForVisible(locator)
        getWebElement(locator).click()
    }

    void clickOnElement(WebElement element) {
        element.click()
    }

    void fillInput(By locator, String value) {
        waitForVisible(locator)
        def element = getWebElement(locator)
        element.click()
        element.clear()
        element.sendKeys(value)
    }


    String getUrl() {
        driver.getCurrentUrl();
    }

    boolean isVisible(By locator) {
        waitForVisible(locator)
        return getWebElement(locator).isDisplayed()
    }

    String getText(By locator) {
        waitForVisible(locator)
        getWebElement(locator).getText()
    }

    String getClassAttribute(By locator) {
        waitForVisible(locator)
        getWebElement(locator).getAttribute("class")
    }

    void selectRandom(By locator) {
        List<WebElement> elements = getWebElements(locator)
        def size = elements.size()
        size > 1 ? clickOnElement(elements.get(random.nextInt(elements.size()))) :
                clickOnElement(elements.get(0))
    }
}
