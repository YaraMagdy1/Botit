package BotitWebsite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Shop_Sub_Category {
    WebDriver driver;
    private By CategoryBox = By.tagName("a");
    public Shop_Sub_Category(WebDriver driver){
        this.driver=driver;
    }
        public String GetPageURL(){
            String CurrenrURL = driver.getCurrentUrl();
            return CurrenrURL;
        }

}
