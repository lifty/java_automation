package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUi extends MainPageObject {

    private static final String
            SAVED_LISTS = "xpath://android.widget.FrameLayout[@content-desc='Saved']",
            SEARCH_FIELD = "xpath://*[@class='android.widget.TextView' and @index=1]",
            SEARCH_FIELD_VALUE = "Search Wikipedia";

    public NavigationUi(AppiumDriver driver) {
        super(driver);
    }

    public void clickSavedLists() throws InterruptedException {
        this.waitForElementAndClick(
                SAVED_LISTS,
                "Cannot find Saved button", 5);
    }

    public void assertSearchFieldHasText() {
        this.assertElementHasText(
                SEARCH_FIELD, SEARCH_FIELD_VALUE,
                "Text in the search field does not match " + SEARCH_FIELD_VALUE, 5);
    }
}
