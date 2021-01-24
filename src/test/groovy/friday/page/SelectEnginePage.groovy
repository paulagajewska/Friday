package friday.page

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

class SelectEnginePage extends Page {

    private WebDriver driver

    private static final String TITLE = "wizardTitle"
    private static final String PAGE_URL = "/selectEngine"
    private static final By ENGINE_LIST = By.name("engine")

    SelectEnginePage(WebDriver driver) {
        super(driver)
        this.driver = driver
    }

    void selectEngineFromList() {
        if(isPageLoaded()) selectRandomEngine()
    }

    private boolean isPageLoaded(){
        waitUntilSpinnerIsNotVisible()
        waitForVisible(getElementByDataTest(TITLE))
        def currentUrl = getUrl()
        currentUrl.contains(PAGE_URL)
    }

    private selectRandomEngine() {
        selectRandom(ENGINE_LIST)
    }

}
