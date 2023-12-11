package BotitWebsite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class Product_Details {
    WebDriver driver;
    public BotitWebsite.Offers Offers;
    private By NameOfItem = By.tagName("a");
    public Product_Details(WebDriver driver){
        this.driver=driver;
    }
    String TitleOfItem="";
    ArrayList<String> Valid_Matched_Items =new ArrayList<>();
    public String CheckTitleOfItem() {
        WebElement TitleOfItemElement = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[2]/div[2]"));
        TitleOfItem = TitleOfItemElement.getText();
        return TitleOfItem;
    }
    public String CheckThePercentage(){
        WebElement PercentageElement = driver.findElement(By.xpath(""));
        String Percentage = PercentageElement.getText();
        return Percentage;
    }


}
