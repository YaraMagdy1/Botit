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
        WebElement TitleOfItemElement = driver.findElement(By.xpath("/html/body/div[5]/div/div[2]/div[2]/div[1]/h2"));
        TitleOfItem = TitleOfItemElement.getText();
        driver.findElement(By.xpath("/html/body/div[4]/div/div[1]/div[2]/ul/li[1]/a/p")).click();
        return TitleOfItem;
    }
    public String GetDiscountPrice() throws InterruptedException {
        String DiscountPrice = driver.findElement(By.xpath("")).toString();
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[4]/div/div[1]/div[2]/ul/li[1]/a/p")).click();
        return DiscountPrice;
    }
    public String CheckThePercentage() throws InterruptedException {
        WebElement PercentageElement = driver.findElement(By.xpath(""));
        String Percentage = PercentageElement.getText();
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[4]/div/div[1]/div[2]/ul/li[1]/a/p")).click();
        return Percentage;
    }
}
