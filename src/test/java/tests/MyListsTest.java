package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListsTest extends CoreTestCase
{
    @Test
    public void testSaveFirstToMyList() {
        String name_of_folder;
        {
            SearchPageObject SearchPageObject = new SearchPageObject(driver);

            SearchPageObject.initSearchInput();
            SearchPageObject.typeSearchLine("Java");
            SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

            ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
            ArticlePageObject.waitForTitleElement();
            String article_title = ArticlePageObject.getArticleTitle();
            name_of_folder = "Learning programming";
            ArticlePageObject.addArticleToMyList(name_of_folder);

            NavigationUI NavigationUI = new NavigationUI(driver);
            NavigationUI.clickMyList();
            NavigationUI.closeArticle();
            NavigationUI.clickSaved();

            MyListPageObject MyListPageObject = new MyListPageObject(driver);
            MyListPageObject.clickNotNow();
            MyListPageObject.openFolderByName(name_of_folder);
            MyListPageObject.swipeArticleToDelete(article_title);

        }
    }
    @Test
    public void testSaveFirstArticleToMyList() {
        String name_of_folder;
        {
            SearchPageObject SearchPageObject = new SearchPageObject(driver);

//   Сохраняем статью Java
            SearchPageObject.initSearchInput();
            SearchPageObject.typeSearchLine("Java");
            SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

            ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
            ArticlePageObject.waitForTitleElement();
            String article_title = ArticlePageObject.getArticleTitle();
            name_of_folder = "Learning programming";
            ArticlePageObject.addArticleToMyList(name_of_folder);

            NavigationUI NavigationUI = new NavigationUI(driver);
            NavigationUI.clickMyList();

            SearchPageObject.clickCancelsTheSearch();

            //   Сохраняем статью Appium
            SearchPageObject.initSearchInput();
            SearchPageObject.typeSearchLine("Appium");
            SearchPageObject.clickByArticleWithSubstring("Appium");

            ArticlePageObject.waitForTitleElement();
            ArticlePageObject.addArticleToExistingList(name_of_folder);

            //Переходим в раздел "Saved"
            NavigationUI.clickMyList();
            NavigationUI.clickMyList();
            NavigationUI.clickSaved();

            //Удаляем статью Appium
            MyListPageObject MyListPageObject = new MyListPageObject(driver);
            MyListPageObject.clickNotNow();
            MyListPageObject.openFolderByName(name_of_folder);
            MyListPageObject.swipeArticleToDelete("Appium");
            MyListPageObject.waitForArticleToAppearByTitle(article_title);
            MyListPageObject.clickArticle("Java (programming language)");

            //Переходим в неё и убеждаемся, что title совпадает
            ArticlePageObject.waitForTitleElement();
        }
    }
}

