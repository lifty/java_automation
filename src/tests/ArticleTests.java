package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {

    @Test
    public void testCheckArticleTitle() throws InterruptedException
    {
        String search_line = "Java",
                article_desc = "Object-oriented programming language";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        SearchPageObject.skipFirstScreen();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForSearchResult(search_line);
        SearchPageObject.clickByArticleWithSubstring(article_desc);
        ArticlePageObject.assertTitlePresent();
    }
}
