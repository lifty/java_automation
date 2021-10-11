package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUi extends MainPageObject {

    private static final String
            SAVED_LISTS = "//android.widget.FrameLayout[@content-desc='Saved']",
            SEARCH_FIELD = "//*[@class='android.widget.TextView' and @resource-id='']",
            SEARCH_FIELD_VALUE = "Search Wikipedia";

    public NavigationUi(AppiumDriver driver) {
        super(driver);
    }

    public void clickSavedLists() throws InterruptedException {
        this.waitForElementAndClick(
                By.xpath(SAVED_LISTS),
                "Cannot find Saved button", 5);
    }

    public void assertSearchFieldHasText() {
        this.assertElementHasText(
                By.xpath(SEARCH_FIELD), SEARCH_FIELD_VALUE,
                "Text in the search field does not match " + SEARCH_FIELD_VALUE, 5);
    }
}
