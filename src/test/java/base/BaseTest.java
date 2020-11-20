package base;

import com.google.j2objc.annotations.Property;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import io.qameta.allure.FileSystemResultsWriter;
import io.qameta.allure.util.PropertiesUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import util.Browser;

import java.io.File;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.util.Properties;

import static org.apache.log4j.PropertyConfigurator.configure;
import static org.testng.FileAssert.fail;

public class BaseTest {
    private static final Logger logTest = Logger.getLogger(BaseTest.class);
    private final String BASE_URL = System.getProperty("https://www.amazon.com.tr", "https://www.amazon.com.tr");
    protected static WebDriver driver;
    public static ExtentTest test;
    public static ExtentReports extent;
    private static Browser browser = new Browser();

    private static String testTrainingBasicName;
    private StringBuffer verificationErrors = new StringBuffer();
    protected static final File DEFAULT_RESULTS_DIRECTORY = new File("target/allure-results");

    private StopWatch pageLoad = new StopWatch();
    StopWatch stopwatch = new StopWatch();

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setDriver(RemoteWebDriver driver) {
        BaseTest.driver = driver;
    }

    private static long duration;

    private static FileSystemResultsWriter getDefaultWriter() {
        final Properties properties = PropertiesUtils.loadAllureProperties();
        final String path = properties.getProperty("allure.results.directory", "allure-results");
        return new FileSystemResultsWriter(Paths.get(path));
    }

    @Property("allure.results.directory")
    protected File resultsDirectory = DEFAULT_RESULTS_DIRECTORY;
 
    @BeforeSuite
    public void before() {
         extent = new ExtentReports("./target/surefire-reports/ExtentReport.html", true);
    }

    @BeforeMethod
    public void setUp(Method method) {
            stopwatch.reset();
            stopwatch.start();
            testTrainingBasicName = System.getProperty("testTrainingName", "");
            configure("./properties/log4j.properties");
            browser.createLocalDriver();
            BaseTest.getDriver().navigate().to(BASE_URL);
            duration = System.currentTimeMillis();
    }

    @AfterMethod
    public void tearDown() {
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
        driver.quit();
        stopwatch.stop();
        logTest.info(stopwatch.toString());
    }

}
