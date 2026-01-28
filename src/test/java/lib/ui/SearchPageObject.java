package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPageObject extends MainPageObject {
    private static final String
            SEARCH_INIT_ELEMENT = "//*[contains(@text,'Search Wikipedia')]",
            SEARCH_INPUT = "//*[@resource-id='org.wikipedia:id/search_src_text']",
            SEARCH_CANCEL_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='{substring}']",
            SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_title']",
            SEARCH_EMPTY_RESULT_ELEMENT = "//*[@text='No results']",
            SEARCH_RESULTS_LIST = "//*[@resource-id='org.wikipedia:id/search_results_list']",
            SEARCH_RESULTS_ITEM = "//*[@resource-id='org.wikipedia:id/search_results_list']/android.view.ViewGroup",
            SEARCH_CANCEL_BUTTON_THE_CROSS = "org.wikipedia:id/search_close_btn";


    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TemplateS method */
    public static String getSearchResult(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{substring}", substring);
    }
    /* TemplateS method */

    public void initSearchInput() {
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT),
                "Cannot find search input after clicking search init element");

        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT),
                "Cannot find and click search init element",
                5
        );
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(By.xpath(SEARCH_CANCEL_BUTTON),
                "Cannot find search cancel button",
                5
        );
    }

    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(By.xpath(SEARCH_CANCEL_BUTTON),
                "Search cancel button is still present",
                5
        );
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(By.xpath(SEARCH_CANCEL_BUTTON),
                "Cannot find and click search cancel button",
                5
        );
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT),
                search_line,
                "Cannot find and type into search input",
                5
        );
    }

    public void waitForSearchResult(String substring) {
        String search_result_xpath = getSearchResult(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath),
                "Cannot find search result with substring" + substring
        );
    }

    public  void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getSearchResult(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath),
                "Cannot find and click search result with substring" + substring,
                10
        );
    }

    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "Cannot find anything by the request ",
                15
        );
        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));
    }

    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(By.xpath(SEARCH_EMPTY_RESULT_ELEMENT),
                "Cannot find empty result element",
                15
        );
    }

    public void assertThereIsNoResultOfSearch() {
        this.assertElementNotPresent(By.xpath(SEARCH_RESULT_ELEMENT),
                "We supposed not to find  any results",
                15
        );

    }
    public int SeveralArticlesWereFound(String search_results) {
        this.waitForElementPresent(
                By.xpath(SEARCH_RESULTS_LIST),
                "Cannot find results list",
                30
        );
        List<WebElement> searchResults = driver.findElements(By.xpath(SEARCH_RESULTS_ITEM));
        return searchResults.size();
    }
    public void clickCancelsTheSearch() {
        this.waitForElementAndClick(
                By.id(SEARCH_CANCEL_BUTTON_THE_CROSS),
                "Cannot find X to cancel search",
                5
        );
    }
    public void TheSearchResulIsMissing() {
        this.waitForElementNotPresent(
                By.xpath(SEARCH_RESULTS_LIST),
                "Search results are still present after canceling search",
                5
        );
    }

}
