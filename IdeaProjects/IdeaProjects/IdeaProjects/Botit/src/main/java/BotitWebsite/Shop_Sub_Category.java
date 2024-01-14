package BotitWebsite;

import com.amazonaws.services.kinesisvideo.model.DASHTimestampRange;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;

public class Shop_Sub_Category {
    WebDriver driver;
    private By CategoryBox = By.tagName("a");
    public Shop_Sub_Category(WebDriver driver){
        this.driver=driver;
    }
        public String GetPageURL() throws InterruptedException {
            String CurrenrURL = driver.getCurrentUrl();
            Thread.sleep(2500);
            return CurrenrURL;
    }
    public String CategNameFromPath(){
        WebElement PathElement = driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div[1]/ul/li[2]/span"));
        String Path =PathElement.getText();
        return Path;
    }
    int CountAtPage;
    ArrayList<String> Name_OF_Result = new ArrayList<>();
    public int CountAtThePage(){
    try {
        int i=1;
        WebElement NameOfResultElement = driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div[3]/ul/li["+i+"]/div[2]/div[1]/h2"));
        String NameOfResult = NameOfResultElement.getText();
        Name_OF_Result.add(NameOfResult);
        i++;
    }catch (Exception e){
        return Name_OF_Result.size();
    }
    return Name_OF_Result.size();
    }
    public Product_Details ClickOnViewItemBtn() throws InterruptedException {
        /*for (int i = 1; i < Name_OF_Result.size(); i++) {
            driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div[3]/ul/li[" + i + "]/div[2]/div[2]/div[2]")).click();
            Thread.sleep(2000);
        }*/
        Thread.sleep(3200);
        driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div[3]/ul/li[1]/div[2]/div[2]/div[2]/a")).click();

        return new Product_Details(driver);
    }


}
