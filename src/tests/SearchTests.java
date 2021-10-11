package tests;

import lib.CoreTestCase;
import lib.ui.NavigationUi;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    @Test
    public void testSearchFieldIncludesText() throws InterruptedException
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        NavigationUi NavigationUi = new NavigationUi(driver);

        SearchPageObject.skipFirstScreen();
        NavigationUi.assertSearchFieldHasText();
    }

    @Test
    public void testFindArticlesAndCancelSearch() throws  InterruptedException
    {
        String search_line = "Java";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.skipFirstScreen();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForSearchResult(search_line);
        SearchPageObject.assertArticleAmountGT(1);
        SearchPageObject.waitAndClickSearchCancel();
        SearchPageObject.waitForEmptyResult();
        SearchPageObject.assertThereIsNoSearchResult();
    }

    @Test
    public void testCheckWordInSearchResults() throws InterruptedException
    {
        String search_line = "Java";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.skipFirstScreen();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForSearchResult(search_line);
        int element_sum = SearchPageObject.assertArticleAmountGT(0);
        SearchPageObject.checkArticlesIncludeSearchLine(element_sum, search_line);
    }

    @Test
    public void testFindArticleWithTitleAndDescription() throws InterruptedException
    {
        String title = "Java",
                description = "program";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.skipFirstScreen();
        SearchPageObject.initSearchInput();
        SearchPageObject.waitForElementByTitleAndDescription(title, description, 3);
    }
}
