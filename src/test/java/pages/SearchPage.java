package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class SearchPage extends PageObject {

    private static final String url = "https://www.autohero.com/de/search/";
    private static final String registerDateXpath = "//span[text()='Erstzulassung ab']";
    private static final String yearDropDownXpath = "//select[@name='yearRange.min']";
    private static final String activeFilterXpath = "//li[@data-qa-selector='active-filter']";
    private static final String sortDropDownXpath = "//select[@name='sort']";
    private static final String resultsYearsXpath = "//a[@data-qa-selector='ad']//li[@data-qa-selector='spec'][1]";
    private static final String resultsCountXpath = "//div[@data-qa-selector-type='LIST']/div";
    private static final String resultsPricesXpath = resultsCountXpath + "[%s]//div[@data-qa-selector='price']";

    public SearchPage(WebDriver extDriver) {
        super(extDriver);
        log.info("Navigate to search page");
        driver.get(url);
    }

    public void selectYear(String year) {
        log.info("Expand year dropdown");
        click(By.xpath(registerDateXpath));
        log.info("Select year: " + year);
        selectDropDownByText(By.xpath(yearDropDownXpath), year);
        waitForElementClickable(By.xpath(activeFilterXpath));
    }

    public void selectSortType(String sortType) {
        log.info("Select sort type: " + sortType);
        selectDropDownByText(By.xpath(sortDropDownXpath), sortType);
    }

    public void verifyYearMoreOrEquals(String year) {
        log.info("Verify displayed years");
        List<WebElement> years = driver.findElements(By.xpath(resultsYearsXpath));
        for (WebElement e : years){
            Assert.assertTrue(Integer.parseInt(e.getText().substring(2)) >= Integer.parseInt(year));
        }
    }

    public void verifyPriceDescending() {
        log.info("Verify displayed prices");
        List<WebElement> prices = driver.findElements(By.xpath(resultsCountXpath));
        for (int i = 0; i < prices.size() - 1; i++){
            Assert.assertTrue(getPrice(i+1) >= getPrice(i+2));
        }
    }

    private float getPrice(int index){
        String s = driver.findElement(By.xpath(String.format(resultsPricesXpath, index))).getText();
        return Float.parseFloat(s.substring(0, s.length() - 2));
    }

}
