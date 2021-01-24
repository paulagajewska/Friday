package friday.test

import friday.driver.Environment
import friday.result.ScreenShoot
import friday.page.*
import org.openqa.selenium.WebDriver
import spock.lang.Specification
import spock.lang.Unroll

class SelectDefinedTests extends Specification {

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

    @Unroll
    def "User wants to fill insurer registration for #vehicle for #model"() {
        given: 'User goes through preconditions page ane selects new car insurer'
        homePage.goThrough(true)

        and: 'User goes through registers owner page and selects that user is not a owner and car is used'
        registerOwnerPage.goThrough(false, false)

        and: 'User selects #vehicle'
        selectVehiclePage.selectDefinedCar(vehicle)

        and: 'User selects #model'
        selectModelPage.selectDefinedModel(model)

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

        where:
        vehicle  | model
        "VOLVO"  | "S80"
        "VOLVO"  | "Volvo 940"
        "VOLVO"  | "XC70"
        "AUDI"   | "A2"
        "AUDI"   | "Q7"
        "AUDI"   | "Cabriolet"
        "TOYOTA" | "Auris"
        "TOYOTA" | "C-HR"
        "TOYOTA" | "Celica"
    }
}
