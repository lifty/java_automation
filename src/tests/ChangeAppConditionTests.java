package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase {

    @Test
    public void testScreenRotation() throws InterruptedException
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.skipFirstScreen();
        SearchPageObject.initSearchInput();
        this.rotateScreenLs();
        SearchPageObject.assertSearchFieldPresent();
        this.rotateScreenPt();
        SearchPageObject.assertSearchFieldPresent();
    }
}
