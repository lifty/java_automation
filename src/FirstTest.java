import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.URL;
import java.util.List;
import java.util.Objects;

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

    @Test
    public void testSwipeUpSearchResults() throws InterruptedException
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Skip')]"),
                "Cannot find Skip element",
                5);
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5);
        String input_key = "Appium";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Search field element",
                5,
                input_key);
        int element_sum = driver.findElements(By.id("org.wikipedia:id/page_list_item_title")).size();
        boolean compare_counted_elements = element_sum > 0;
        assert compare_counted_elements: "The number of articles is " + element_sum +", we are expecting at least 1";
        swipeUpToFindElement(
                By.xpath("//*[@text='RTTS']"), "Cannot find RTTS article", 3);
    }

    @Test
    public void testSaveTwoArticles() throws InterruptedException
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
        waitForElementAndClick(
                By.xpath("//*[@text='Object-oriented programming language']"),
                "Cannot find searched article",
                5);
        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Save']"),
                "Cannot find Save button",
                5);
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find Back button",
                5);
        waitForElementAndClick(
                By.xpath("//*[@text='Indonesian island']"),
                "Cannot find searched article",
                5);
        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Save']"),
                "Cannot find Save button",
                5);
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find Back button",
                5);
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@index=0]"),
                "Cannot find Back button",
                5);
        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='Saved']"),
                "Cannot find Saved button",
                5);
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/item_reading_list_statistical_description' and contains(@text,'2 articles')]"),
                "Cannot find a list with 2 articles",
                5);
        swipeElementToLeft(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='Object-oriented programming language']"),
                "Cannot find saved article");
        waitForElementNotPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='Object-oriented programming language']"),
                "Cannot delete saved article",
                5);
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='Indonesian island']"),
                "Cannot find and click the article that should stay in the list",
                5);
        waitForElementPresent(By.xpath("//android.webkit.WebView[@content-desc='Java']//*[contains(@content-desc,'Javanese')]"),
                "Cannot prove the article is the expected one",
                5);

    }

    @Test
    public void testCheckArticleTitle() throws InterruptedException
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
        waitForElementAndClick(
                By.xpath("//*[@text='Object-oriented programming language']"),
                "Cannot find searched article",
                5);
        assertElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find the title of the article");
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    @Test
    public void testScreenRotation() throws InterruptedException
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Skip')]"),
                "Cannot find Skip element",
                5);
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5);
        rotateScreenLS();
        assertElementPresentAndRerotate(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input after landscape rerotation");
        rotateScreenPT();
        assertElementPresent(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input after portrait rerotation");

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
        driver.hideKeyboard();
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

    protected void swipeUp(int timeOfSwipe)
    {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);
        action.press(x, start_y).waitAction(timeOfSwipe).moveTo(x, end_y).release().perform();
    }

    protected void swipeUpQuick()
    {
        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by, String error_message, int max_swipes) throws InterruptedException
    {
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0)
        {
            if (already_swiped > max_swipes)
            {
                waitForElementPresent(by, "Cannot find element by swiping up. \n" + error_message, 0);
                return;
            }
            swipeUpQuick();
            Thread.sleep(1000);
            ++already_swiped;
        }
    }

    protected void swipeElementToLeft(By by, String error_message)
    {
        WebElement element = waitForElementPresent(by, error_message, 10);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int y = (upper_y + lower_y) / 2;
        TouchAction action = new TouchAction(driver);
        action.press(right_x, y).waitAction(150).moveTo(left_x, y).release().perform();
    }

    private int getAmountOfElements(By by)
    {
        List elements = driver.findElements(by);
        return elements.size();
    }

    private void assertElementPresent(By by, String error_message)
    {
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements == 0) {
            String default_message = "An element '" + by.toString() + "' supposed to be presented";
            throw new AssertionError(default_message + "\n" + error_message);
        }
    }

    private void assertElementPresentAndRerotate(By by, String error_message)
    {
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements == 0) {
            String current_rotation = driver.getOrientation().toString();
            if (!Objects.equals(current_rotation, "PORTRAIT")) {
                rotateScreenPT();
            }
            String default_message = "An element '" + by.toString() + "' supposed to be presented";
            throw new AssertionError(default_message + "\n" + error_message);
        }

    }
//
//    private void assertElementNotPresent(By by, String error_message)
//    {
//        int amount_of_elements = getAmountOfElements(by);
//        if (amount_of_elements > 0) {
//            String default_message = "An element '" + by.toString() + "' supposed to be not presented";
//            throw new AssertionError(default_message + " " + error_message);
//        }
//    }

    private void rotateScreenLS()
    {
        driver.rotate(ScreenOrientation.LANDSCAPE);
        return;
    }

    private void rotateScreenPT()
    {
        driver.rotate(ScreenOrientation.PORTRAIT);
        //driver.runAppInBackground(5);
        return;
    }
}
