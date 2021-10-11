package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
            TITLE = "org.wikipedia:id/view_page_title_text",
            DESCRIPTION_CONTENCE_TPL = "//*[contains(@content-desc,'{SUBSTRING}')]",
            FOOTER_ELEMENT = "//*[@text='View page in browser']",
            SAVE_BUTTON = "//android.widget.TextView[@text='Save']",
            BACK_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

//    TEMPLATES_METHODS
    private static String getArticleContenceElement(String substring) {
        return DESCRIPTION_CONTENCE_TPL.replace("{SUBSTRING}", substring);
    }
//    TEMPLATES_METHODS

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(
                By.id(TITLE),
                "Cannot find the title of the article", 10);
    }

    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void swipeToFooter() throws InterruptedException {
        this.swipeUpToFindElement(
                By.xpath(FOOTER_ELEMENT),
                "Cannot find the end of the article", 20);
    }

    public void saveArticle() throws InterruptedException {
        this.waitForElementAndClick(
                By.xpath(SAVE_BUTTON),
                "Cannot find Save button", 5);
    }

    public void closeArticle() throws InterruptedException {
        this.waitForElementAndClick(
                By.xpath(BACK_BUTTON),
                "Cannot find Back button", 5);
    }

    public void checkArticleDescription(String substring) {
        String search_result_xpath = getArticleContenceElement(substring);
        this.waitForElementPresent(
                By.xpath(search_result_xpath),
                "Cannot prove the article is the expected one", 5);
    }

    public void assertTitlePresent() {
        this.assertElementPresent(
                By.id(TITLE), "Cannot find the title of the article");
    }
}
