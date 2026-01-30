package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class MainPageObject {
    protected static AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }

    public void assertElementHasText(By by, String expectedText, String errorMessage) {
        WebElement element = waitForElementPresent(by, errorMessage + " (элемент не найден)", 5);
        String actualText = element.getText();

        if (!actualText.contains(expectedText)) {
            String fullErrorMessage = String.format(
                    "%s. Ожидаемый текст: '%s', Фактический текст: '%s'",
                    errorMessage, expectedText, actualText
            );
            Assert.fail(fullErrorMessage);
        }
    }

    public static WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by, error_message, 5);
    }

    public static WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    public void swipeUp(int timeOfSwipe) {
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);

        Map<String, Object> params = new HashMap<>();
        params.put("startX", x);
        params.put("startY", start_y);
        params.put("endX", x);
        params.put("endY", end_y);
        params.put("duration", timeOfSwipe);

        driver.executeScript("mobile: dragGesture", params);
    }

    public void swipeUpQuick() {
        swipeUp(200);
    }

    public void swipeUpToFindElement(By by, String error_message, int max_swipes) {
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0) {

            if (already_swiped > max_swipes) {
                waitForElementPresent(by, "Cannot find element by swiping up. \n" + error_message, 0);
                return;
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }

    public void swipeElementToLeft(By by, String error_message) {
        WebElement element = waitForElementPresent(by, error_message, 10);

        Map<String, Object> params = new HashMap<>();
        params.put("elementId", ((RemoteWebElement) element).getId());
        params.put("direction", "left");
        params.put("percent", 0.9);

        driver.executeScript("mobile: swipeGesture", params);
    }

    public int getAmountOfElements(By by) {
        List elements = driver.findElements(by);
        return elements.size();
    }

    public void assertElementNotPresent(By by, String error_message, long timeoutInSeconds) {
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements > 0) {
            String default_amount = "An element '" + by.toString() + "' supposed to be not present";
            throw new AssertionError(default_amount + " " + error_message);
        }
    }

    public String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }

    public void assertElementPresent(By by, String error_message) {
        if (getAmountOfElements(by) == 0) {
            throw new AssertionError("Cannot find element by locator " + by + " " + error_message);
        }
    }
}
