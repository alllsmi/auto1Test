package tests;

import infrastructure.Config;
import infrastructure.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = DriverFactory.getDriverInstance(Config.isWindows);
    }

    @AfterMethod
    public void quit() {
        if (driver != null) {
            driver.quit();
        }
    }
}
