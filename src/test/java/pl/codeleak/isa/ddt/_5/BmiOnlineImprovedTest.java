package pl.codeleak.isa.ddt._5;

import io.github.bonigarcia.wdm.WebDriverManager;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import junitparams.mappers.CsvWithHeaderMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class BmiOnlineImprovedTest {

    RemoteWebDriver driver;

    @BeforeClass
    public static void beforeAll() {
        WebDriverManager.firefoxdriver().setup();
    }

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        }
    }


    @Test
    @FileParameters(value = "classpath:bmi.csv", mapper = CsvWithHeaderMapper.class)
    public void verifiesBmiValue(String weight, String height, String result) {
        navigateToBmipage();
        acceptCookies();
        setBmiFrom(weight, height);
        submitForm();
        String bmiResult = getBmiResult();
        assertThat(bmiResult).isEqualTo(result);
    }


    private void navigateToBmipage() {
        driver.get("http://bmi-online.pl/");
    }

    private void acceptCookies() {
        WebElement cookieOverlay = driver.findElement(By.cssSelector(".t-a-c__overlay"));

        WebElement acceptCookieBooton = driver.findElement(By.cssSelector(".t-a-c__box__btn__label"));
        acceptCookieBooton.click();

        new WebDriverWait(driver,5)
                .until(ExpectedConditions.invisibilityOf(cookieOverlay));
    }

    private void setBmiFrom(String weight, String height) {
        WebElement weightInput = driver.findElement(By.cssSelector("input[name=weight]"));
        weightInput.clear();
        weightInput.sendKeys(weight);

        WebElement heightInput = driver.findElement(By.cssSelector("input[name=height]"));
        heightInput.clear();
        heightInput.sendKeys(height);
    }

    private void submitForm() {
        WebElement submit = driver.findElement(By.cssSelector("button[type=submit]"));
        submit.click();
    }

    private String getBmiResult() {
         WebElement resultEl = driver.findElement(By.cssSelector(".result-v1__title > strong:nth-child(1)"));
         return resultEl.getText();
    }
}
