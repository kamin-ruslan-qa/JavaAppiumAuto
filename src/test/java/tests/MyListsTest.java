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
}
