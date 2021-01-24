package friday.test

import friday.driver.Environment
import friday.result.ScreenShoot
import friday.page.*
import org.openqa.selenium.WebDriver
import spock.lang.Specification

import java.time.LocalDate

class IncorrectRegistrationDateTests extends Specification {

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
    }

    def cleanup() {
        ScreenShoot.takeScreenShoot(driver)
        driver.close()
    }

    def "When user enters registration date for car older than 40 years then stays on registration page and sees error message"() {
        given:
        String expectedErrorMessage = "Du kannst leider keine Fahrzeuge bei uns versichern, die älter als 40 Jahre sind."

        and: 'User goes through preconditions page ane selects change car insurer'
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

        when: 'User enters registration date for car older than 40 years'
        registrationDatePage.fillRegistrationDate(LocalDate.now().minusYears(40).minusMonths(1))

        then: 'Error message is visible with correct message'
        registrationDatePage.checkErrorMessage(expectedErrorMessage)

        and: 'Continue button is disabled'
        registrationDatePage.checkIfContinueButtonIsDisable()
    }

    def "When user enters registration date for car too far from future then stays on registration page and sees error message"() {
        given:
        String expectedErrorMessage = "Hups! Dieses Datum liegt zu weit in der Zukunft. Bitte überprüfe deine Eingabe."

        and: 'User goes through preconditions page ane selects change car insurer'
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

        when: 'User enters registration date for car too far from future'
        registrationDatePage.fillRegistrationDate(LocalDate.now().plusYears(2))

        then: 'Error message is visible with correct message'
        registrationDatePage.checkErrorMessage(expectedErrorMessage)

        and: 'Continue button is disabled'
        registrationDatePage.checkIfContinueButtonIsDisable()
    }

    def "When user tries not to fill registration date for car then user stays on registration page and sees error message"() {
        given:
        String expectedErrorMessage = "Hups! Das klingt unwahrscheinlich. Bitte überprüfe deine Eingabe."

        and: 'User goes through preconditions page ane selects change car insurer'
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

        when: 'User enters registration date for car too far from future'
        registrationDatePage.notFillRegistrationDate()

        then: 'Error message is visible with correct message'
        registrationDatePage.checkErrorMessage(expectedErrorMessage)

        and: 'Continue button is disabled'
        registrationDatePage.checkIfContinueButtonIsDisable()
    }

    def "When user enters not digit characters as registration date for car then user stays on registration page and sees error message"() {
        given:
        String expectedErrorMessage = "Hups! Das klingt unwahrscheinlich. Bitte überprüfe deine Eingabe."

        and: 'User goes through preconditions page ane selects change car insurer'
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

        when: 'User enters registration date for car too far from future'
        registrationDatePage.fillRegistrationDate("aa.bbbb")

        then: 'Error message is visible with correct message'
        registrationDatePage.checkErrorMessage(expectedErrorMessage)

        and: 'Continue button is disabled'
        registrationDatePage.checkIfContinueButtonIsDisable()

        and: 'Registration date input is empty'
        registrationDatePage.registrationInputInEmpty()

    }

}