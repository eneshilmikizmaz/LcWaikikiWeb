
import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;


public class BaseTest {

    protected static WebDriver driver;

    protected static WebDriverWait webDriverWait;

    private static Logger logger = LoggerFactory.getLogger(BaseTest.class);

    public static String browserName = "chrome";

    DesiredCapabilities capabilities = new DesiredCapabilities();


    public static String baseUrl = "https://www.lcwaikiki.com/tr-TR/TR";


    @BeforeScenario
    public void setUp() {


        ChromeOptions options = new ChromeOptions();
        capabilities = DesiredCapabilities.chrome();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-notifications");
        options.addArguments("--start-fullscreen");
        System.setProperty("webdriver.chrome.driver", "src/resources/chromedriver.exe");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

        driver.manage().window().fullscreen();

        driver.get(baseUrl);

    }

    @AfterScenario
    public void tearDown() {
        driver.quit();

    }

}