package friday.test

import friday.driver.Environment
import friday.result.ScreenShoot
import friday.page.HomePage
import org.openqa.selenium.WebDriver
import spock.lang.Specification

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class IncorrectInsuranceStartDayTests extends Specification {

    private Random random = new Random()
    private HomePage homePage
    private WebDriver driver

    def setup() {
        driver = Environment.getDriver()
        homePage = new HomePage(driver)
    }

    def cleanup(){
        ScreenShoot.takeScreenShoot(driver)
        driver.close()
    }

    def "When user enters date from past as insurance start day then user stays on preconditions page and sees error message"() {
        given:
        LocalDate date = LocalDate.now().minusDays(random.nextInt(1000))
        String startInsuranceDate = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))

        and:
        String expectedErrorMessage = "Hups! Dieses Datum liegt in der Vergangenheit. Bitte überprüfe deine Eingabe."

        when: 'User fills incorrect date'
        homePage.fillStartInsuranceDay(startInsuranceDate)

        then: 'Error message is visible with correct message'
        homePage.checkErrorMessage(expectedErrorMessage)

        and: 'Submit button is disabled'
        homePage.checkIfSubmitButtonIsDisable()
    }

    def "When user enters date too long in the future as insurance start day then stays on preconditions page and sees error message"() {
        given:
        LocalDate date = LocalDate.now().plusYears(random.nextInt(10))
        String startInsuranceDate = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))

        and:
        String expectedErrorMessage = "Hups! Dieses Datum liegt zu weit in der Zukunft. Bitte überprüfe deine Eingabe."

        when: 'User fills incorrect date'
        homePage.fillStartInsuranceDay(startInsuranceDate)

        then: 'Error message is visible with correct message'
        homePage.checkErrorMessage(expectedErrorMessage)

        and: 'Submit button is disabled'
        homePage.checkIfSubmitButtonIsDisable()
    }

    def "When user enters not digit characters as start insurance date then stays on preconditions page and sees error message"() {
        given:
        String expectedErrorMessage = "Erforderlich"

        when: 'User fills incorrect date'
        homePage.fillStartInsuranceDay("aa.bb.ccdd")

        then: 'Error message is visible with correct message'
        homePage.checkErrorMessage(expectedErrorMessage)

        and: 'Submit button is disabled'
        homePage.checkIfSubmitButtonIsDisable()

        and: 'Start insurance input is empty'
        homePage.startInsuranceInputIsEmpty()
    }
}
