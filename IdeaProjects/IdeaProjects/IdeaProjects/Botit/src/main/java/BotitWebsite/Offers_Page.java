package BotitWebsite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class Offers_Page {
    WebDriver driver;
    public Offers_Page(WebDriver driver){
        this.driver=driver;
    }
    public String GetTitleOfPage(){
       WebElement TitlePageElement= driver.findElement(By.xpath("/html/body/div[2]/div/div[2]"));
       String TitlePage = TitlePageElement.getText();
       return TitlePage;
    }
    public BotitWebsite.Offers Offers;
    public BotitWebsite.Product_Details Product_Details;
    public BotitWebsite.Featured_Categories Featured_Categories;
    ArrayList<String> Valid_Matched_Items =new ArrayList<>();
    ArrayList<String> Not_Valid_Matched_Items =new ArrayList<>();
    public ArrayList<String> ClickOnViewItemButton(){
        Offers.GetAllNameOfItems();
        String StepName;
        for(int i=0 ; i< Offers.Name_OF_All_Items_Website.size(); i++) {
            String NameOfITem= Offers.Name_OF_All_Items_Website.get(i);
            driver.findElement(By.xpath("/html/body/div[9]/div/div[2]/div[2]/div/div/div[" + (i+1) + "]/div[2]/div[2]/div[2]")).click();
            String Title= Product_Details.CheckTitleOfItem();
            if(NameOfITem== Title){
                Valid_Matched_Items.add(NameOfITem);
                StepName="Step2 Right Navigate with Matched Items after clicking on see more btn";
                Featured_Categories.Screenshot(StepName);
                driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/ul/li[1]")).click();
            }else {
                Not_Valid_Matched_Items.add(NameOfITem);
                StepName="Step2 Not Matched Items After clicking on see more btn";
                Featured_Categories.Screenshot(StepName);
                driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/ul/li[1]")).click();
            }
        }
        return Not_Valid_Matched_Items;
    }
}
