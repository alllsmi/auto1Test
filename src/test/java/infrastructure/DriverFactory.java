package infrastructure;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class DriverFactory {

    public static WebDriver getDriverInstance(boolean isWindows) {
        return createNewChrome(isWindows);
    }

    private static ChromeDriver createNewChrome(boolean isWindows) {
        ChromeDriver driver;
        if (isWindows){
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        else {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("window-size=1920,1080");
            options.addArguments("--disable-dev-shm-usage");
            driver = new ChromeDriver(options);
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }
}
