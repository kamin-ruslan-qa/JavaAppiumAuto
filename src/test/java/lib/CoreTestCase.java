package lib;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;
import java.time.Duration;

public class CoreTestCase extends TestCase {

    protected AppiumDriver driver;
    private static String AppiumURL = "http://127.0.0.1:4723/";


@Override
    protected void setUp() throws Exception
    {
        super.setUp();


        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:deviceName", "AndroidTestDevice");
        capabilities.setCapability("appium:platformVersion", "8");
        capabilities.setCapability("appium:appPackage", "org.wikipedia");
        capabilities.setCapability("appium:appActivity", ".main.MainActivity");
        capabilities.setCapability("appium:app", "C:/Users/user/Desktop/JavaAppiumAutomatoin/JavaAppiumAutomatoin/apiks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL(AppiumURL), capabilities);
        Thread.sleep(3000);
        driver.navigate().back();
        this.rotateScreenPortrait();
    }
@Override
    protected void tearDown() throws Exception {
        if (driver != null) {
            try {
                driver.executeScript("mobile: terminateApp", ImmutableMap.of("bundleId", "org.wikipedia"));
            } catch (Exception e) {
                driver.quit();

                super.tearDown();
            }
        }
    }
    protected void rotateScreenPortrait (){
        AndroidDriver androidDriver = (AndroidDriver) driver;
        androidDriver.rotate(ScreenOrientation.PORTRAIT);

    }
    protected void rotateScreenLandscape (){
        AndroidDriver androidDriver = (AndroidDriver) driver;
        androidDriver.rotate(ScreenOrientation.LANDSCAPE);
    }
    protected void backgroundApp(int seconds){
        AndroidDriver androidDriver = (AndroidDriver) driver;
        androidDriver.runAppInBackground(Duration.ofSeconds(seconds));
    }
}