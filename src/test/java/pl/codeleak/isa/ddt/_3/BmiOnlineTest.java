package pl.codeleak.isa.ddt._3;

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
public class BmiOnlineTest {

    RemoteWebDriver driver;

    @BeforeClass
    public static void beforeAll() {
        WebDriverManager.firefoxdriver().setup();
    }

    @Before
    public void setUp() throws Exception {
        // setup Firefox or Chrome driver
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() throws Exception {
        // quite the driver
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @FileParameters(value = "classpath:bmi.csv", mapper = CsvWithHeaderMapper.class)
    public void verifiesBmiValueBasedOnFixedInputs(String weight, String height, String result) {

        // TODO Open bmi online page
       driver.get("http://bmi-online.pl/");

        // TODO Get overlay
        WebElement cookieOverlay = driver.findElement(By.cssSelector(".t-a-c__overlay"));

        // TODO Accept cookies
        WebElement acceptCookieBooton = driver.findElement(By.cssSelector(".t-a-c__box__btn__label"));
        acceptCookieBooton.click();

        // TODO Wait for overlay to be invisible
        new WebDriverWait(driver,5)
                .until(ExpectedConditions.invisibilityOf(cookieOverlay));

        // TODO Set bmi form
        WebElement weightInput = driver.findElement(By.cssSelector("input[name=weight]"));
        weightInput.clear();
        weightInput.sendKeys(weight);

        WebElement heightInput = driver.findElement(By.cssSelector("input[name=height]"));
        heightInput.clear();
        heightInput.sendKeys(height);

        // TODO Submit the form and get bmi result page

        WebElement submit = driver.findElement(By.cssSelector("button[type=submit]"));
        submit.click();

        // TODO Get bmi value (number only)
        WebElement resultEl = driver.findElement(By.cssSelector(".result-v1__title > strong:nth-child(1)"));

        // TODO Assert value
        assertThat(resultEl.getText()).isEqualTo(result);

    }
}
