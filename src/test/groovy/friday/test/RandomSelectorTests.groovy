package friday.test

import friday.driver.Environment
import friday.result.ScreenShoot
import friday.page.*
import org.openqa.selenium.WebDriver
import spock.lang.Specification

class RandomSelectorTests extends Specification {

    private HomePage homePage
    private WebDriver driver
    private RegisterOwnerPage registerOwnerPage
    private SelectVehiclePage selectVehiclePage
    private SelectModelPage selectModelPage
    private SelectBodyTypePage selectBodyTypePage
    private SelectFuelTypePage selectFuelTypePage
    private SelectEnginePowerPage selectEnginePowerPage
    private SelectEnginePage selectEnginePage
    private RegistrationDatePage registrationDatePage
    private BirthDatePage birthDatePage

    def setup() {
        driver = Environment.getDriver()
        homePage = new HomePage(driver)
        registerOwnerPage = new RegisterOwnerPage(driver)
        selectVehiclePage = new SelectVehiclePage(driver)
        selectModelPage = new SelectModelPage(driver)
        selectBodyTypePage = new SelectBodyTypePage(driver)
        selectFuelTypePage = new SelectFuelTypePage(driver)
        selectEnginePowerPage = new SelectEnginePowerPage(driver)
        selectEnginePage = new SelectEnginePage(driver)
        registrationDatePage = new RegistrationDatePage(driver)
        birthDatePage = new BirthDatePage(driver)
    }

    def cleanup() {
        ScreenShoot.takeScreenShoot(driver)
        driver.close()
    }

    def """User wants to change insurer and user is owner and car is new
        When user randomly selects vehicle to insurance
        Then user sees birth day page"""() {
        given: 'User goes through preconditions page ane selects change car insurer'
        homePage.goThrough()

        and: 'User goes through registers owner page and selects that user is a owner and car is new'
        registerOwnerPage.goThrough()

        and: 'User selects random vehicle from most popular column'
        selectVehiclePage.selectFromMostPopular()

        and: 'User selects random model from most popular column'
        selectModelPage.selectFromMostPopular()

        and: 'If possible user selects vehicle body type from list'
        selectBodyTypePage.selectBodyTypeFromList()

        and: 'If possible user selects vehicle fuel type from list'
        selectFuelTypePage.selectFuelTypeFromList()

        and: 'If possible user selects vehicle engine power from list'
        selectEnginePowerPage.selectEnginePowerFromList()

        and: 'If possible user selects vehicle engine from list '
        selectEnginePage.selectEngineFromList()

        when: 'User fills registration date'
        registrationDatePage.goThrough()

        then: 'User sees birth day page'
        birthDatePage.isPageLoaded()
    }
}
