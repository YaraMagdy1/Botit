package PromoCode;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PageLoginPromo {
    WebDriver driver;
    public PageLoginPromo(WebDriver driver){
        this.driver = driver;
    }

    By userName = By.cssSelector("input[id=\"username\"]");
    By password = By.cssSelector("input[name=\"password\"]");
    By signIn = By.cssSelector("button[type=\"submit\"]");


    public void login(){
        driver.findElement(userName).sendKeys("superadmin");
        driver.findElement(password).sendKeys("123456789");
        driver.findElement(signIn).click();
    }
}
