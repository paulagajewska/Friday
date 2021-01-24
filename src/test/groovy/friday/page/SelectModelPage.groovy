package friday.page

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

class SelectModelPage extends Page {

    private WebDriver driver

    private static final String TITLE = "wizardTitle"
    private static final String PAGE_URL = "/selectModel"
    private static final By MOST_POPULAR_MODEL_LIST = By.xpath('//*[@class="SingleClickListField__optionGroup--MNnWx"][1]/button')
    private static final By MODEL_FILTER_INPUT = By.name("modelFilter")
    private static final By FILTERED_MODEL_LIST = By.name("model")

    SelectModelPage(WebDriver driver) {
        super(driver)
        this.driver = driver
    }

    void selectFromMostPopular() {
        waitUntilPageIsLoaded()
        selectRandomFromMostPopular()
    }

    void selectDefinedModel(String model){
        waitUntilPageIsLoaded()
        fillModelInFilterInput(model)
        selectFilteredModel()
    }

    private void waitUntilPageIsLoaded() {
        waitUntilSpinnerIsNotVisible()
        waitForVisible(getElementByDataTest(TITLE))
        def currentUrl = getUrl()
        if (!currentUrl.contains(PAGE_URL)) throw new IllegalAccessException("Incorrect page is loaded")
    }

    private void selectRandomFromMostPopular() {
        selectRandom(MOST_POPULAR_MODEL_LIST)
    }

    private fillModelInFilterInput(String value) {
        fillInput(MODEL_FILTER_INPUT, value)
    }

    private void selectFilteredModel() {
        clickOnElement(FILTERED_MODEL_LIST)
    }

}
