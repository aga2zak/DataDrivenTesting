package pl.codeleak.isa.ddt._6;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// TODO Implement the page
class BmiPage {

    RemoteWebDriver driver;

    BmiPage(RemoteWebDriver driver) {
        this.driver = driver;
    }


    public void navigateTo() {
        driver.get("http://bmi-online.pl/");
    }

    BmiResultPage submit() {
        return new BmiResultPage(driver);
    }

   public void acceptCookie() {
        WebElement cookieOverlay = driver.findElement(By.cssSelector(".t-a-c__overlay"));

        WebElement acceptCookieBooton = driver.findElement(By.cssSelector(".t-a-c__box__btn__label"));
        acceptCookieBooton.click();

        new WebDriverWait(driver,5)
                .until(ExpectedConditions.invisibilityOf(cookieOverlay));
    }

    public void setBmiForm(String weight, String height) {
        WebElement weightInput = driver.findElement(By.cssSelector("input[name=weight]"));
        weightInput.clear();
        weightInput.sendKeys(weight);

        WebElement heightInput = driver.findElement(By.cssSelector("input[name=height]"));
        heightInput.clear();
        heightInput.sendKeys(height);
    }


}
