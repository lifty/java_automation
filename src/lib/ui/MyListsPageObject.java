package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject {

    public static final String
            FOLDER_BY_DESC_TPL = "xpath://*[@resource-id='org.wikipedia:id/item_reading_list_statistical_description' and contains(@text,'{FOLDER_DESC}')]",
            ARTICLE_BY_DESC_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='{ARTICLE_DESC}']";

    public MyListsPageObject (AppiumDriver driver) {
        super(driver);
    }

//    TEMPLATES_METHODS
    private static String getFolderXpathByDecs(String folder_desc) {
        return FOLDER_BY_DESC_TPL.replace("{FOLDER_DESC}", folder_desc);
    }
    private static String getArticleXpathByDecs(String article_desc) {
        return ARTICLE_BY_DESC_TPL.replace("{ARTICLE_DESC}", article_desc);
    }
//    TEMPLATES_METHODS

    public void openFolderByDesc(String folder_desc) throws InterruptedException {
        String folder_desc_xpath = getFolderXpathByDecs(folder_desc);
        this.waitForElementAndClick(
                folder_desc_xpath,
                "Cannot find folder by description " + folder_desc,
                5);
    }

    public void waitForArticlePresentByDesc (String article_desc) {
        String article_desc_xpath = getArticleXpathByDecs(article_desc);
        this.waitForElementPresent(
                article_desc_xpath,
                "Cannot find an article by description " + article_desc,
                5);
    }

    public void waitForArticleNotPresentByDesc (String article_desc) {
        String article_desc_xpath = getArticleXpathByDecs(article_desc);
        this.waitForElementNotPresent(
                article_desc_xpath,
                "Should be found 0 article by description " + article_desc,
                5);
    }

    public void swipeArticleToDelete(String article_desc) {
        String article_desc_xpath = getArticleXpathByDecs(article_desc);
        this.waitForArticlePresentByDesc(article_desc);
        this.swipeElementToLeft(
                article_desc_xpath,
                "Cannot find and delete saved article by description " + article_desc);
        this.waitForArticleNotPresentByDesc(article_desc);
    }

    public void clickArticlePresentByDesc(String article_desc) throws InterruptedException {
        String article_desc_xpath = getArticleXpathByDecs(article_desc);
        this.waitForElementAndClick(
                article_desc_xpath,
                "Cannot find an article by description " + article_desc,
                5);
    }
}
