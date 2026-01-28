import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;


public class FirstTest extends CoreTestCase {

    //    @Test
//    // Задание :2
//    public void testSearchFieldText() {
//        MainPageObject.waitForElementAndClick(
//                By.id("org.wikipedia:id/search_container"),
//                "Cannot find search container",
//                5
//        );
//        MainPageObject.assertElementHasText(
//               By.id("org.wikipedia:id/search_src_text"),
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


