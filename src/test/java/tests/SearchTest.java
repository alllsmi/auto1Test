package tests;

import org.testng.annotations.Test;
import pages.SearchPage;

public class SearchTest extends BaseTest {

    private static final String year = "2015";
    private static final String sortType = "Höchster Preis";

    @Test
    public void searchFilters(){
        SearchPage searchPage = new SearchPage(driver);
        searchPage.selectYear(year);
        searchPage.selectSortType(sortType);
        searchPage.verifyYearMoreOrEquals(year);
        searchPage.verifyPriceDescending();
    }
}
