package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

public class CoreTestCase extends TestCase {

    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";

    protected AppiumDriver driver;
    private static String AppiumURL = "http://0.0.0.0:4723/wd/hub/";

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();

        DesiredCapabilities capabilities = this.desiredCapabilitiesByPlatformEnv();

        driver = this.driverByPlatformEnv();
    }

    @Override
    protected void tearDown() throws Exception
    {
        driver.quit();
        super.tearDown();
    }

    public void rotateScreenPt() {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    public void rotateScreenLs() {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    public void backgroundApp(int seconds) {
        driver.runAppInBackground(Duration.ofSeconds(seconds));
    }

    private DesiredCapabilities desiredCapabilitiesByPlatformEnv() throws Exception {
        String platform = System.getenv("PLATFORM");
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (platform.equals(PLATFORM_ANDROID)) {
            capabilities.setCapability("platformName","Android");
            capabilities.setCapability("deviceName","AndroidTestDevice");
            capabilities.setCapability("orientation","PORTRAIT");
            capabilities.setCapability("platformVersion","10.0");
            capabilities.setCapability("automationName","Appium");
            capabilities.setCapability("appPackage","org.wikipedia");
            capabilities.setCapability("appActivity",".main.MainActivity");
            capabilities.setCapability("app","C:/Users/Crowdsystems/Documents/JavaAppiumAutomation/apks/wikipedia.apk");
        }
        else if (platform.equals(PLATFORM_IOS)) {
            capabilities.setCapability("platformName","iOS");
            capabilities.setCapability("deviceName","iPhone SE");
            capabilities.setCapability("platformVersion","11.3");
            capabilities.setCapability("app","C:/Users/Crowdsystems/Documents/JavaAppiumAutomation/apks/wikipedia.app");
        }
        else {
            throw new Exception("Cannot get run platform from env variable to set capabilities. Platform value is " + platform);
        }
        return capabilities;
    }

    private AppiumDriver driverByPlatformEnv() throws Exception {
        String platform = System.getenv("PLATFORM");
        if (platform.equals(PLATFORM_ANDROID)) {
            driver = new AndroidDriver(new URL(AppiumURL), this.desiredCapabilitiesByPlatformEnv());
        } else if (platform.equals(PLATFORM_IOS)) {
            driver = new IOSDriver(new URL(AppiumURL), this.desiredCapabilitiesByPlatformEnv());
        } else {
            throw new Exception("Cannot get run platform from env variable to set driver type. Platform value is " + platform);
        }

        return driver;
    }
}
