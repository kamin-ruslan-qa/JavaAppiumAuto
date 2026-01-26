package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject {
    private static final String
            MY_LIST_LINK = "//android.widget.ImageButton[@content-desc='Navigate up']",
            CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']",
            SAVED_LINK = "//android.widget.FrameLayout[@content-desc='Saved']";


    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }

    public void clickMyList() {
        this.waitForElementAndClick(
                By.xpath(MY_LIST_LINK),
                "Cannot find button to close article options",
                5
        );
    }

    public void clickSaved() {
        this.waitForElementAndClick(
                By.xpath(SAVED_LINK),
                "Cannot find 'Saved' button",
                5
        );
    }

    public void closeArticle() {
        this.waitForElementAndClick(
                By.xpath(CLOSE_ARTICLE_BUTTON),
                "Cannot find button to close article options",
                5
        );
    }


}