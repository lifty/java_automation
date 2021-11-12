package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {

    private static final String
            SKIP_ELEMENT = "id:org.wikipedia:id/fragment_onboarding_skip_button",
            SEARCH_INIT_ELEMENT = "id:org.wikipedia:id/search_container",
            SEARCH_INPUT = "xpath://*[contains(@text,'Search Wikipedia')]",
            SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn",
            SEARCH_IS_EMPTY = "id:org.wikipedia:id/search_empty_container",
            BACK_BUTTON = "xpath://android.widget.ImageButton[@index=0]",
            SEARCH_RESULT_BY_TITLE_AND_DESC_TPL = "xpath://*[@class='android.view.ViewGroup' and @index={i}]//*[@resource-id='org.wikipedia:id/page_list_item_title' and contains(@text,'{TITLE}')]/..//*[@resource-id='org.wikipedia:id/page_list_item_description' and contains(@text,'{DESCRIPTION}')]",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']//*[contains(@text,'{SUBSTRING}')]",
            SEARCH_RESULT_TITLE_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_title']";
    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

//    TEMPLATES_METHODS
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultSearchByTitleAndDescriptionElement(String i, String title, String desc) {
        String index_replaced = SEARCH_RESULT_BY_TITLE_AND_DESC_TPL.replace("{i}", i);
        String title_replaced = index_replaced.replace("{TITLE}", title);
        return title_replaced.replace("{DESCRIPTION}", desc);
    }
//    TEMPLATES_METHODS

    public void skipFirstScreen() throws InterruptedException {
        this.waitForElementAndClick(
                SKIP_ELEMENT,
                "Cannot find Skip element", 5);
    }

    public void initSearchInput() throws InterruptedException {
        this.waitForElementPresent(
                SEARCH_INIT_ELEMENT,
                "Cannot find search input after clicking search init element", 5);
        this.waitForElementAndClick(
                SEARCH_INIT_ELEMENT,
                "Cannot find and click search init element", 5);
    }

    public void typeSearchLine(String search_line) throws InterruptedException {
        this.waitForElementAndSendKeys(
                SEARCH_INPUT,
                "Cannot find Search field element", 5, search_line);
    }

    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(
                search_result_xpath,
                "Cannot find search result with substring " + substring, 5);
    }

    public void clickByArticleWithSubstring(String substring) throws InterruptedException {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(
                search_result_xpath,
                "Cannot find search result with substring " + substring, 10);
    }

    public void waitForElementByTitleAndDescription(String title, String desc, int element_sum_to_check) throws InterruptedException {
        typeSearchLine(title + " " + desc);
        assertArticleAmountGT(2);
        for (int i = 0; i < element_sum_to_check; i++) {
            String search_result_xpath = getResultSearchByTitleAndDescriptionElement(Integer.toString(i), title, desc);
            this.waitForElementPresent(
                    search_result_xpath,
                    "Cannot find search result with title " + title + " and description " + desc + " by xpath " + search_result_xpath, 5);
        }
    }

    public void checkArticlesIncludeSearchLine(int element_sum, String search_line) {
        for (int i = 0; i < element_sum; i++)
        {
            String create_xpath = "//*[@class='android.view.ViewGroup' and @index="+ i+"]//*[contains(@text, '" + search_line + "')]";
            this.waitForElementPresent(
                    create_xpath,
                    "Value " + search_line + " is not found in an article with index " + i,
                    5);
        }
    }

    public void waitAndClickSearchCancel() throws InterruptedException {
        this.waitForElementAndClick(
                SEARCH_CANCEL_BUTTON,
                "Cannot find search cancel button to click", 5);
        this.waitForElementPresent(
                SEARCH_IS_EMPTY,
                "Search empty container is not presented", 5);
    }

    public void leaveSearch() throws InterruptedException {
        this.waitForElementAndClick(
                BACK_BUTTON,
                "Cannot find Back button", 5);
    }

    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(
                SEARCH_RESULT_TITLE_ELEMENT,
                "Cannot find any search result", 5);
        return this.getAmountOfElements(SEARCH_RESULT_TITLE_ELEMENT);
    }

    public void waitForEmptyResult() {
        this.waitForElementNotPresent(
                SEARCH_RESULT_TITLE_ELEMENT,
                "The search result is still presented",
                5);
    }

    public void assertThereIsNoSearchResult() {
        this.assertElementNotPresent(
                SEARCH_RESULT_TITLE_ELEMENT,
                "Search result supposed not to be presented");
    }

    public int assertArticleAmountGT(int gt) {
        int element_sum = getAmountOfFoundArticles();
        boolean compare_counted_elements = element_sum > gt;
        assert compare_counted_elements: "The number of articles is " + element_sum +", we are expecting at least " + (gt + 1);
        return element_sum;
    }

    public void assertSearchFieldPresent() {
        this.assertElementPresent(
                SEARCH_INIT_ELEMENT,
                "Cannot find 'Search Wikipedia' input");
    }
}
