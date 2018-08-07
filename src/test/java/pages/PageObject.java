package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageObject {

    protected WebDriver driver;
    protected Logger log = LoggerFactory.getLogger(this.getClass());

    public PageObject(WebDriver extDriver){
        this.driver = extDriver;
    }

    protected void click(By by){
        waitForElementVisibility(by);
        driver.findElement(by).click();
    }

    protected void waitForElementVisibility(By by){
        new WebDriverWait(driver, 10, 50)
                .until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    protected void selectDropDownByValue(By by, String text){
        new Select(driver.findElement(by)).selectByValue(text);
    }

    protected void selectDropDownByText(By by, String text){
        new Select(driver.findElement(by)).selectByVisibleText(text);
    }
}
