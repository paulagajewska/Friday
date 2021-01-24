package friday.page

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

class SelectVehiclePage extends Page {

    private WebDriver driver

    private static final String TITLE = "wizardTitle"
    private static final String PAGE_URL = "/selectVehicle"
    private static final By VEHICLE_FILTER_INPUT = By.name("makeFilter")
    private static final By MOST_POPULAR_CARS_LIST = By.xpath('//*[@class="SingleClickListField__optionGroup--MNnWx"][1]/button')
    private static final By FILTERED_CARS_LIST = By.name("make")

    SelectVehiclePage(WebDriver driver) {
        super(driver)
        this.driver = driver
    }

    void selectFromMostPopular() {
        waitUntilPageIsLoaded()
        selectRandomCarFromMostPopular()
    }

    void selectDefinedCar(String vehicle) {
        waitUntilPageIsLoaded()
        fillVehicleInFilterInput(vehicle)
        selectFilteredCar()
    }

    private void waitUntilPageIsLoaded() {
        waitUntilSpinnerIsNotVisible()
        waitForVisible(getElementByDataTest(TITLE))
        def currentUrl = getUrl()
        if (!currentUrl.contains(PAGE_URL)) throw new IllegalAccessException("Incorrect page is loaded")
    }

    private void selectRandomCarFromMostPopular() {
        selectRandom(MOST_POPULAR_CARS_LIST)
    }

    private fillVehicleInFilterInput(String value) {
        fillInput(VEHICLE_FILTER_INPUT, value)
    }

    private void selectFilteredCar() {
        clickOnElement(FILTERED_CARS_LIST)
    }
}
