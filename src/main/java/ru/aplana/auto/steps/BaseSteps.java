package ru.aplana.auto.steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.aplana.auto.pages.BasePage;
import ru.aplana.auto.util.TestProperties;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseSteps {

    protected static WebDriver driver;
    protected static String baseURL;
    public static Properties properties = TestProperties.getINSTANCE().getProperties();

    @Before
    public static void setUp() {

        switch (properties.getProperty("browser")) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "drv/chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "drv/geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            default:
                System.setProperty("webdriver.chrome.driver", "drv/chromedriver.exe");
                driver = new ChromeDriver();

        }
        baseURL = properties.getProperty("app.url");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(baseURL);

    }

    public static WebDriver getDriver() {
        return driver;
    }

    @After
    public static void tearDown() {
        driver.quit();
    }

    @Attachment(type = "image/png", value = "Screenshot")
    public static byte[] takeScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }


    public void switchWindow() {
        new BasePage().takeNewWindow();
    }

    public void checkPageTitle(String pageTitle){
        Assert.assertEquals(driver.getTitle(), pageTitle);
    }

}
