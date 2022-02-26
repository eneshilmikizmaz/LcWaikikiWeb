
import com.thoughtworks.gauge.Step;

import com.thoughtworks.gauge.datastore.DataStore;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerAdapter;

import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class StepImplementation extends BaseTest {
    private static Log4jLoggerAdapter logger = (Log4jLoggerAdapter) LoggerFactory
            .getLogger(StepImplementation.class);

    public StepImplementation() {
        PropertyConfigurator.configure("log4j.properties");
    }

    HashMap<String, String> specStore = new HashMap<String, String>();

    public String getSpecData(String key) {
        return specStore.get(key);
    }

    public void saveSpecData(String key, String value) {
        specStore.put(key, value);
    }

    @Step("<page> doğrulama <xpth>")
    public void checkHomePage(String page, String xpth) {
        //driver.findElement(By.xpath("//*[@class='section inspiring']"));
        try {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpth)));
            Assert.assertTrue((page + " görüntülenmiyor!"), element.isDisplayed());
            logger.info(page + " görüntüleniyor..");
        } catch (Exception e) {
            logger.error(xpth + "  ...xpth'li element görüntülenemiyor!");
        }
    }

    @Step("<xpth> li erkek kategorisine hover yapılır , <xpth2> li gömlek kategorisi seçilir")
    public void hoverMenPage(String xpth, String xpth2) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpth)));
            Actions action = new Actions(driver);
            action.moveToElement(element).build().perform();
            logger.info("Erkek sayfasına hover yapıldı..");
            WebElement element1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpth2)));
            element1.click();
            logger.info("Gömlek sayfasına tıklandı..");
        } catch (Exception e) {
            logger.error(xpth + "  ...xpth'li element görüntülenemiyor!");
        }

    }

    @Step("<xpth> li <element> e tıklanır")
    public void clickXpath(String xpth, String element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            WebElement ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpth)));
            ele.click();
            logger.info(element + "  tıklandı..");
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e) {
            logger.error(xpth + "  ...xpth'li " + element + " görüntülenemiyor!");
        }

    }

    @Step("<xpth> li elementin <attribute> attribute değeri <savedkey> ile depolanır")
    public void storeElements(String xpth, String attribute, String savedkey) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpth)));
        String storeText = element.getAttribute(attribute);
        saveSpecData(savedkey, storeText);
        logger.info(storeText + " depolandı");
    }

    @Step("<xpth> li favori ürün <attribute> ve <savedkey> ile doğrulanır <number>")
    public void verificationElements(String xpth, String attribute, String savedkey, String number) {

        try {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpth)));
            Assert.assertEquals(savedkey + " ..hatalı!!", element.getAttribute(attribute), getSpecData(number));
            logger.info(savedkey + ". Ürün doğrulandı..");
        } catch (Exception e) {
            logger.error(xpth + " ..xpath bulunamadı!!");
        }
    }

    @Step("fav checkmark <xpth> sayısı doğrulanır")
    public void favCheckMark(String xpth){
        try {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            List<WebElement> elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpth)));
            Assert.assertTrue("Tümünü seç hatalı!!", elements.size() == 3);
            logger.info("Tümünü seç başarılı..");
        }catch (Exception e){
            logger.error("Xpath hatalı!!");
        }
    }
//Key li elementin attribute değerini savedkey le depola
    ////span[@class='fav-checkmark']
}


