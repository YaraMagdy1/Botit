package BotitWebsite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class Product_Details {
    WebDriver driver;
    public BotitWebsite.Offers Offers;
    private By NameOfItem = By.tagName("a");
    public Product_Details(WebDriver driver){
        this.driver=driver;
    }
    String TitleOfItem="";
    ArrayList<String> Valid_Matched_Items =new ArrayList<>();

    public String CheckTitleOfItem() throws InterruptedException {
        WebElement TitleOfItemElement = driver.findElement(By.xpath("/html/body/div[5]/div/div[2]/div[2]/div[1]/h2"));
        TitleOfItem = TitleOfItemElement.getText();
        return TitleOfItem;
    }
    public Download_Page ClickOnDownloadAppBtn() throws InterruptedException {
        driver.findElement(By.xpath("/html/body/div[5]/div/div[2]/div[2]/div[5]/a")).click();
        Set<String> handles = driver.getWindowHandles();
        Iterator it = handles.iterator();
        String ParentID = (String) it.next();
        String ChildID = (String) it.next();
        driver.switchTo().window(ChildID);
        Thread.sleep(3000);
        //driver.close();
        return new Download_Page(driver);
    }
    public void GoBackToHomePage(){
        driver.findElement(By.xpath("/html/body/div[4]/div/div[1]/div[2]/ul/li[1]/a")).click();
    }
    public void CheckTheFooter(){
        driver.findElement(By.xpath("/html/body/div[6]/div/div"));
    }
    public String GetDiscountPrice() throws InterruptedException {
        WebElement DiscountPriceElement = driver.findElement(By.xpath("/html/body/div[5]/div/div[2]/div[2]/div[2]/span"));
        String DiscountPrice = DiscountPriceElement.getText();
        Thread.sleep(2500);
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
