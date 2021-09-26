import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","6.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","C:/Users/Crowdsystems/Documents/JavaAppiumAutomation/apks/wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub/"), capabilities);

    }

    @After
    public void tearDown() {

        driver.quit();

    }

    @Test
    public void testSearchFieldIncludesText() throws InterruptedException
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Skip')]"),
                "Cannot find Skip element",
                5);
        assertElementHasText(
                By.xpath("//*[@class='android.widget.TextView' and @resource-id='']"),
                "Search Wikipedia",
                "The element does not contain expected text value",
                5);
    }

    @Test
    public void testFindArticlesAndCancelSearch() throws  InterruptedException
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Skip')]"),
                "Cannot find Skip element",
                5);
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5);
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Search field element",
                5,
                "Java");
        int element_sum_before = driver.findElements(By.id("org.wikipedia:id/page_list_item_title")).size();
        boolean compare_counted_elements = element_sum_before > 1;
        assert compare_counted_elements: "The number of articles is " + element_sum_before +", we are expecting at least several elements";
        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot clear the element",
                5);
        waitForElementNotPresent(
                By.id("org.wikipedia:id/page_list_item_title"),
                "The element is still presented",
                5);
        int element_sum_after = driver.findElements(By.id("org.wikipedia:id/page_list_item_title")).size();
        int element_sum_expected = 0;
        Assert.assertEquals(
                "The number of articles is " + element_sum_after + ", expected " + element_sum_expected,
                element_sum_expected,
                element_sum_after);
    }

    @Test
    public void testCheckWordInSearchResults() throws InterruptedException
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Skip')]"),
                "Cannot find Skip element",
                5);
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5);
        String input_key = "Java";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Search field element",
                5,
                input_key);
        int element_sum = driver.findElements(By.id("org.wikipedia:id/page_list_item_title")).size();
        boolean compare_counted_elements = element_sum > 0;
        assert compare_counted_elements: "The number of articles is " + element_sum +", we are expecting at least 1";
        for (int i = 0; i < element_sum; i++)
        {
            String create_xpath = "//*[@class='android.view.ViewGroup' and @index="+ i+"]//*[contains(@text, '" + input_key + "')]";
            waitForElementPresent(
                    By.xpath(create_xpath),
                    "Value " + input_key + " is not found in an article with index " + i,
                    5);
        }
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) throws InterruptedException
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        Thread.sleep(1000);
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String error_message, long timeoutInSecond, String keys) throws InterruptedException
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSecond);
        element.sendKeys(keys);
        Thread.sleep(1000);
        return element;
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    private WebElement assertElementHasText(By by, String expected_value, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        String actual_value = element.getAttribute("text");
        Assert.assertEquals(error_message,expected_value,actual_value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }
}
