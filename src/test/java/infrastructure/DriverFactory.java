package infrastructure;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class DriverFactory {

    public static WebDriver getDriverInstance(boolean isWindows) {
        return createNewChrome(isWindows);
    }

    private static ChromeDriver createNewChrome(boolean isWindows) {
        if (isWindows){
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        }
        else {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        }
        ChromeDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }
}
