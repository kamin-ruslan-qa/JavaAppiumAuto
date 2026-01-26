import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;
import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;

import java.util.List;


//public class FirstTest extends CoreTestCase
//{

//    @Test
//    // Задание :2
//    public void testSearchFieldText() {
//        MainPageObject.waitForElementAndClick(
//                By.id("org.wikipedia:id/search_container"),
//                "Cannot find search container",
//                5
//        );
//        MainPageObject.assertElementHasText(
//                By.id("org.wikipedia:id/search_src_text"),
//                "Search Wikipedia",
//                "Search field does not contain 'Search Wikipedia'"
//        );
//    }
//
//    @Test
//    // Задание :4 делать по желанию
//
//    public void testSearchResultsMostlyContainQuery() {
//        String query = "Java";
//
//        MainPageObject.waitForElementAndClick(
//                By.id("org.wikipedia:id/search_container"),
//                "Cannot find 'Search Wikipedia' input",
//                5
//        );
//        MainPageObject.waitForElementAndSendKeys(
//                By.id("org.wikipedia:id/search_src_text"),
//                "Java",
//                "Cannot find search input",
//                5
//        );
//
//        MainPageObject.waitForElementPresent(
//                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']"),
//                "Cannot find article title",
//                30
//        );
//
//        List<WebElement> titleElements = driver.findElements(
//                By.id("org.wikipedia:id/page_list_item_title")
//        );
//
//        int matches = 0;
//        for (WebElement titleElement : titleElements) {
//            String title = titleElement.getText();
//            if (title != null && !title.isEmpty()) {
//                System.out.println("Заголовок:" + title);
//                if (title.toLowerCase().contains(query.toLowerCase())) {
//                    matches++;
//                }
//            }
//        }
//        assertTrue(
//                "Expected at least 1 result containing '" + query + "', but found " + matches + " out of " + titleElements.size(),
//                matches >= 1
//        );
//    }
//    @Test
//    //Задание:5
//    public void testChangeScreenOrientationSearchResults() {
//
//        MainPageObject.waitForElementAndClick(
//                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
//                "Cannot find Search Wikipedia input",
//                5
//        );
//        MainPageObject.waitForElementAndSendKeys(
//                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
//                "Java",
//                "Cannot find search input",
//                5
//        );
//        MainPageObject.waitForElementAndClick(
//                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Java (programming language)']"),
//                "Cannot find Java in search",
//                5
//        );
//        MainPageObject.waitForElementPresent(
//                By.xpath("//android.widget.TextView[@text='Java (programming language)']"),
//                "Cannot find article title",
//                15
//        );
//        MainPageObject.waitForElementAndClick(
//                By.id("org.wikipedia:id/page_save"),
//                "Cannot find button to open article options",
//                5
//        );
//        MainPageObject.waitForElementAndClick(
//                By.xpath("//android.widget.Button[@resource-id='org.wikipedia:id/snackbar_action'][@text='Add to list']"),
//                "Cannot find 'Add to reading list'",
//                5
//        );
//        String name_of_folder = "Learning programming";
//        MainPageObject.waitForElementAndSendKeys(
//                By.id("org.wikipedia:id/text_input"),
//                "Learning programming",
//                "Cannot find input to set name of reading list",
//                5
//        );
//        MainPageObject.waitForElementAndClick(
//                By.xpath("//*[@text='OK']"),
//                "Cannot find 'OK' button",
//                5
//        );
//        MainPageObject.waitForElementAndClick(
//                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
//                "Cannot find button to close article options",
//                5
//        );
//        MainPageObject.waitForElementAndClick(
//                By.xpath("//*[@resource-id='org.wikipedia:id/search_close_btn']"),
//                "Cannot find 'X' button",
//                5
//        );
//        MainPageObject.waitForElementAndSendKeys(
//                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
//                "Appium",
//                "Cannot find search input",
//                5
//        );
//        MainPageObject. waitForElementAndClick(
//                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
//                "Cannot find Appium in search",
//                5
//        );
//        MainPageObject.waitForElementPresent(
//                By.xpath("//android.widget.TextView[@text='Appium']"),
//                "Cannot find article title",
//                15
//        );
//        MainPageObject.waitForElementAndClick(
//                By.id("org.wikipedia:id/page_save"),
//                "Cannot find button to open article options",
//                5
//        );
//        MainPageObject.waitForElementAndClick(
//                By.xpath("//android.widget.Button[@resource-id='org.wikipedia:id/snackbar_action'][@text='Add to list']"),
//                "Cannot find 'Add to reading list'",
//                5
//        );
//        MainPageObject.waitForElementAndClick(
//                By.xpath("//*[@text='" + name_of_folder + "']"),
//                "Cannot find created folder",
//                15
//        );
//        MainPageObject.waitForElementAndClick(
//                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
//                "Cannot find button to close article options",
//                5
//        );
//        MainPageObject.waitForElementAndClick(
//                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
//                "Cannot find button to close article options",
//                5
//        );
//        MainPageObject.waitForElementAndClick(
//                By.xpath("//android.widget.FrameLayout[@content-desc='Saved']"),
//                "Cannot find 'Saved' button",
//                5
//        );
//        MainPageObject.waitForElementAndClick(
//                By.xpath("//android.widget.Button[@resource-id='org.wikipedia:id/negativeButton'][@text='Not now']"),
//                "Cannot find 'Not now' button",
//                5
//        );
//        MainPageObject.waitForElementAndClick(
//                By.xpath("//*[@text='" + name_of_folder + "']"),
//                "Cannot find created folder",
//                15
//        );
//        MainPageObject.swipeElementToLeft(
//                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title' and @text='Appium']"),
//                "Cannot find saved article to delete"
//        );
//        MainPageObject.waitForElementNotPresent(
//                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
//                "Cannot deleted saved",
//                5
//        );
//        MainPageObject.waitForElementPresent(
//                By.xpath("//android.widget.TextView[@text='Java (programming language)']"),
//                "Makes sure that the second article remains.",
//                15
//        );
//        MainPageObject.waitForElementAndClick(
//                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Java (programming language)']"),
//                "Cannot find Java in search",
//                5
//        );
//        MainPageObject.waitForElementPresent(
//                By.xpath("//android.widget.TextView[@text='Java (programming language)']"),
//                "Cannot find article title",
//                15
//        );
//    }
//
//    @Test
//    // Задание :6
//    public void testArticleHasTitle() {
//        MainPageObject.waitForElementAndClick(
//                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
//                "Cannot find Search Wikipedia input",
//                5
//        );
//        MainPageObject.waitForElementAndSendKeys(
//                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
//                "Appium",
//                "Cannot find search input",
//                5
//        );
//        MainPageObject.waitForElementAndClick(
//                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
//                "Cannot find Appium in search",
//                5
//        );
//        MainPageObject.assertElementPresent(
//                By.xpath("//android.widget.TextView[@text='Appium']"),
//                "Cannot find article title"
//        );
//
//    }
//}

