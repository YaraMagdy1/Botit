package BotitWebsite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class Vendor_Details {
    WebDriver driver;
    public Vendor_Details(WebDriver driver){
        this.driver=driver;
    }
    String VendorTitle;
    public String TitleOfVendor() throws InterruptedException {
        WebElement TitleElement = driver.findElement(By.xpath("/html/body/div[5]/div/div/div[1]/div[1]/h2"));
        VendorTitle =TitleElement.getText();
        Thread.sleep(2800);
        driver.findElement(By.xpath("/html/body/div[4]/div/div[1]/div[2]/ul/li[1]/a/p")).click();
        return VendorTitle;
    }
    String ProductTitle;
    public String TitleOfProduct() throws InterruptedException {
        WebElement TitleElement2 = driver.findElement(By.xpath("/html/body/div[7]/div[1]/div/div/div[2]/ul/li[1]/div[2]/h2"));
        ProductTitle =TitleElement2.getText();
        Thread.sleep(2800);
        driver.findElement(By.xpath("/html/body/div[4]/div/div[1]/div[2]/ul/li[1]/a/p")).click();
        return ProductTitle;
    }
    ArrayList<String> Related_Items_Website =new ArrayList<>();
    public String RelatedItemsAtVendorPage() {
        int i = 1;
       try {
           WebElement RelatedItemsElement = driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div[3]/ul/li["+i+"]/div[2]/div[1]/h2"));
           String RelatedItems = RelatedItemsElement.getText();
           Related_Items_Website.add(RelatedItems);
           i++;
       }catch (Exception e){
           System.out.println(e);
       }
        return String.valueOf(Related_Items_Website);
    }
    /*public String GetTitleOfVendor() {
            WebElement VendorNameElement = driver.findElement(By.xpath("/html/body/div[5]/div/div/div[1]/div[1]/h2"));
            String VendorName = VendorNameElement.getText();
            return VendorName;
    }*/
}
