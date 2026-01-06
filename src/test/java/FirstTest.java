import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.URL;
import java.util.List;

public class FirstTest {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "C:/Users/user/Desktop/JavaAppiumAutomatoin/JavaAppiumAutomatoin/apiks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void firstTest() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find search input",
                5
        );
        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
                15
        );
    }

    @Test
    public void testCancelSearch() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search",
                5
        );
        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "X is still present on the page",
                5
        );
    }

    @Test
    public void testSearchFieldText() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search container",
                5
        );
        assertElementHasText(
                By.id("org.wikipedia:id/search_src_text"),
                "Search…",
                "Search field does not contain 'Search…'"
        );
    }

    @Test
    public void testSearchAndCancel() {

        String searchWord = "Java";
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                searchWord,
                "Cannot find search input",
                5
        );


        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']"),
                "No search results found for '" + searchWord + "'",
                15
        );


        List<WebElement> searchResults = driver.findElements(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']")
        );


        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search",
                5
        );

        waitForElementNotPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']"),
                "Search results are still present after canceling search",
                5
        );


        waitForElementNotPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']"),
                "Search results are still present after canceling search",
                5
        );
        WebElement searchField = waitForElementPresent(
                By.id("org.wikipedia:id/search_src_text"),
                "Search field not found",
                5
        );

        String searchFieldText = searchField.getText();
        Assert.assertTrue(
                "Search field should be empty or contain placeholder after cancel, but contains: '" + searchFieldText + "'",
                searchFieldText.isEmpty() || searchFieldText.equals("Search…") || searchFieldText.equals("Search Wikipedia")
        );
    }

    @Test
    public void testSearchResultsMostlyContainQuery() {
        String query = "Java";

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search container",
                5
        );
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                query,
                "Cannot find search input field",
                5
        );

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']"),
                "No search results appeared for query: " + query,
                10
        );

        List<WebElement> resultCards = driver.findElements(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']")
        );

        int matches = 0;
        for (WebElement card : resultCards) {
            String titleText = "";
            try {
                WebElement titleElement = card.findElement(By.id("org.wikipedia:id/page_list_item_title"));
                titleText = titleElement.getText().trim();
            } catch (Exception ignored) {
                continue;
            }

            if (!titleText.isEmpty() && titleText.toLowerCase().contains(query.toLowerCase())) {
                matches++;
            }
        }
        Assert.assertTrue(
                "None of the " + resultCards.size() + " visible search results contain the word '" + query + "'",
                matches > 0
        );
    }

    private void assertElementHasText(By by, String expectedText, String errorMessage) {
        WebElement element = waitForElementPresent(by, errorMessage + " (элемент не найден)", 5);

        String actualText = element.getText();

        if (!actualText.contains(expectedText)) {
            String fullErrorMessage = String.format("Фактический текст: '%s'",
                    errorMessage, expectedText, actualText);
            Assert.fail(fullErrorMessage);
        }
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by, error_message, 5);
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }
}