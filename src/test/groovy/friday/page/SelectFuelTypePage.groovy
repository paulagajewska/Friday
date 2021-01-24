package friday.page

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

class SelectFuelTypePage extends Page {

    private WebDriver driver

    private static final String TITLE = "wizardTitle"
    private static final String PAGE_URL = "/selectFuelType"
    private static final By FUEL_TYPE_LIST = By.name("fuelType")

    SelectFuelTypePage(WebDriver driver) {
        super(driver)
        this.driver = driver
    }

    void selectFuelTypeFromList() {
        if(isPageLoaded()) selectRandomFuelType()
    }

    private boolean isPageLoaded(){
        waitUntilSpinnerIsNotVisible()
        waitForVisible(getElementByDataTest(TITLE))
        def currentUrl = getUrl()
        currentUrl.contains(PAGE_URL)
    }

    private void selectRandomFuelType(){
        selectRandom(FUEL_TYPE_LIST)
    }

}
