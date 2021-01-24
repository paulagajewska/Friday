package friday.driver


import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.safari.SafariDriver

class Environment {

    static WebDriver getDriver() {

        String browserType = System.getProperty("browser")

        BrowserType type = browserType != null
                ? BrowserType.valueOf(browserType.toUpperCase())
                : new IllegalArgumentException("Browser not specified. Available: " + BrowserType.values())

        switch (type) {
            case BrowserType.CHROME:
                System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver")
                return new ChromeDriver()
            case BrowserType.FIREFOX:
                System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver");
                return new FirefoxDriver()
            case BrowserType.SAFARI:
                return new SafariDriver()
        }

    }

}
