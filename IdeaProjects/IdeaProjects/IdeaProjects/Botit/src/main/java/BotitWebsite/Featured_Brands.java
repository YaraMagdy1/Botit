package BotitWebsite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class Featured_Brands {
    WebDriver driver;
    public Featured_Brands(WebDriver driver){
        this.driver=driver;
    }
    public String GetTitle(){
        WebElement TitlePageElement = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div"));
        String TitlePage= TitlePageElement.toString();
        return TitlePage;
    }

    String AllVendorsName = "";
    ArrayList<String> All_Vendors_Website = new ArrayList<>();
    public String RelatedItemsAtFeaturedPage() {
        int i = 1;
       try {
           WebElement AllVendorsElement = driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/ul/li[" + i + "]/div[2]/div[1]/h2"));
           AllVendorsName = AllVendorsElement.getText();
           All_Vendors_Website.add(AllVendorsName);
           i++;
       }catch (Exception e){
           System.out.println(e);
       }
       return String.valueOf(All_Vendors_Website);
    }
}
