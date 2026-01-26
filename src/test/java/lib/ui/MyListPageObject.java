package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListPageObject extends MainPageObject {
    private static final String
            NOT_NOW_BUTTON = "//android.widget.Button[@resource-id='org.wikipedia:id/negativeButton'][@text='Not now']",
            FOLDER_BY_NAME_TPL = "//*[@text='{FOLDER_NAME}']",
    ARTICLE_BY_TITLE_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_title' and @text='{TITLE}']";

    private static String getFolderXpathByName(String name_of_folder)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }
    private static String getSaveArticleXpathByTitle(String article_title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

   public MyListPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void clickNotNow() {
        this.waitForElementAndClick(
                By.xpath(NOT_NOW_BUTTON),
                "Cannot find 'Not now' button",
                5
        );
    }
    public void openFolderByName(String name_of_folder) {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                By.xpath(folder_name_xpath),
                "Cannot find folder by name " + name_of_folder,
                15
        );
    }
    public void waitForArticleToAppearByTitle(String article_title) {
        String article_xpath = getFolderXpathByName(article_title);
        this.waitForElementPresent(
                By.xpath(article_xpath),
                "Cannot find saved article by title " + article_title,
                15
        );
    }
    public void waitForArticleToDisappearByTitle(String article_title) {
        String article_xpath = getFolderXpathByName(article_title);
        this.waitForElementNotPresent(
                By.xpath(article_xpath),
                "Saved article is still present with title " + article_title,
                15
        );
    }
    public void swipeArticleToDelete(String article_title) {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getFolderXpathByName(article_title);
        this.swipeElementToLeft(
                By.xpath(article_xpath),
                "Cannot find saved article to delete"
        );
        this.waitForArticleToDisappearByTitle(article_title);
    }
}
