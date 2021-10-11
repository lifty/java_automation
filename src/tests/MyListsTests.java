package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUi;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    @Test
    public void testSaveTwoArticles() throws InterruptedException
    {
        String search_line = "Java",
                first_article_desc = "Object-oriented programming language",
                second_article_desc = "Indonesian island",
                folder_desc = "2 articles",
                second_article_contence = "Javanese";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        NavigationUi NavigationUi = new NavigationUi(driver);
        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);

        SearchPageObject.skipFirstScreen();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForSearchResult(search_line);
        SearchPageObject.clickByArticleWithSubstring(first_article_desc);
        ArticlePageObject.saveArticle();
        ArticlePageObject.closeArticle();
        SearchPageObject.clickByArticleWithSubstring(second_article_desc);
        ArticlePageObject.saveArticle();
        ArticlePageObject.closeArticle();
        SearchPageObject.leaveSearch();
        NavigationUi.clickSavedLists();
        MyListsPageObject.openFolderByDesc(folder_desc);
        MyListsPageObject.swipeArticleToDelete(first_article_desc);
        MyListsPageObject.clickArticlePresentByDesc(second_article_desc);
        ArticlePageObject.checkArticleDescription(second_article_contence);
    }
}
