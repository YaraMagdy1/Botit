package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Dashboard {

    private WebDriver driver;
    By OrderLink = By.xpath("/html/body/div/div/div/div[1]/div[2]/ul/li[2]/a");

    By AccountLink = By.xpath("/html/body/div/div/div/div[1]/div[2]/ul/li[6]/a");
    public  Dashboard(WebDriver driver){
        this.driver= driver;
    }

    //locators
   //
    // String URL = "http://transmission-dev.azurewebsites.net/orders";

    public String getURL()
    {
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String URL = driver.getCurrentUrl();
        return URL;

    }

    public Orders Navigatetoorders(){
        driver.findElement(OrderLink).click();
        return new Orders(driver);
    }

    public Account NavigatetoAccount(){
        driver.findElement(AccountLink).click();
        return new Account(driver);
    }


}
