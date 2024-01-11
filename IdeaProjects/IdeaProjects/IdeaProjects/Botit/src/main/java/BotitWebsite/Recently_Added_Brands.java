package BotitWebsite;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.ne;

public class Recently_Added_Brands {
    WebDriver driver;
    MongoClient mongoClient = MongoClients.create("mongodb+srv://transmission_dev:K1IPfykYMq6FAUv6@botit-dev.jwtve.mongodb.net/botitdev?retryWrites=true&w=majority");
    MongoDatabase database = mongoClient.getDatabase("botitdev");
    ArrayList<String> Vendore_Name_Website = new ArrayList<>();
    ArrayList<String> Vendors_Name_DB = new ArrayList<>();
    String VendorNameDB = "";
    public Recently_Added_Brands(WebDriver driver) {this.driver = driver;}
    public String CheckFunctionalityOfRightButton(){
        WebElement LstVendorElement = driver.findElement(By.xpath("/html/body/div[8]/div/div[2]/div/div/div[5]/div[2]/div[1]/h2"));
        String LastVendor = LstVendorElement.getText();
        try{
            driver.findElement(By.xpath("/html/body/div[8]/div/div[1]/div[2]/div[2]")).click();
            WebElement FirstVednorAfterClickOnRightArrowElement = driver.findElement(By.xpath("/html/body/div[8]/div/div[2]/div/div/div[6]/div[2]/div[1]/h2"));
            String FirstVednorAfterClickOnRightArrow =FirstVednorAfterClickOnRightArrowElement.getText();
            if(LastVendor != FirstVednorAfterClickOnRightArrow){
                return "true";
            }else {
                return "false";
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return "true";
    }
    public String MultipleRightbuttonClick() {
        String result = "";
        String RightButtonStatus = driver.findElement(By.xpath("/html/body/div[8]/div/div[1]/div[2]/div[2]")).getAttribute("aria-disabled");
        try {
            while (RightButtonStatus != "true") {
                driver.findElement(By.xpath("/html/body/div[8]/div/div[1]/div[2]/div[2]")).click();
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            String leftarrowEnabled = driver.findElement(By.xpath("/html/body/div[8]/div/div[1]/div[2]/div[1]")).getAttribute("aria-disabled").toString();
            if (leftarrowEnabled == "false") {
                result = "we reached to the last category with gray displayed";
                return result;
            }
        }
        result = "Vendors not more than 5";
        return result;
    }
    public String MultipleLeftbuttonClick() {
        String result = "";
        String LeftArrowStatus = "";
        try {
            LeftArrowStatus = driver.findElement(By.xpath("/html/body/div[8]/div/div[1]/div[2]/div[1]")).getAttribute("aria-disabled").toString();
            while (LeftArrowStatus != "true") {
                Thread.sleep(1000);
                driver.findElement(By.xpath("/html/body/div[8]/div/div[1]/div[2]/div[1]")).click();
            }
        } catch (Exception e) {
            if (LeftArrowStatus == "true") {
                result = "we reached to the First Vendor with gray displayed";
                return result;
            }
        }
        return null;
    }
    public String StatusOfLeftButton(){
        String Result = "";
        try{
           driver.findElement(By.xpath("/html/body/div[8]/div/div[1]/div[2]/div[1]")).click();
            Result = "Left Button is clickable";
            return Result;
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return Result;
    }
    public String DefualtCaseOfArrows() {
        Get_All_Name_Of_Vendors();
        MultipleLeftbuttonClick();
        String RightArrowStatus = "";
        String LeftArrowStatus = "" ;
        int VendorCount = Vendore_Name_Website.size();
        if ( VendorCount > 5) {
            RightArrowStatus = driver.findElement(By.xpath("/html/body/div[8]/div/div[1]/div[2]/div[2]")).getAttribute("aria-disabled");
            LeftArrowStatus = driver.findElement(By.xpath("/html/body/div[8]/div/div[1]/div[2]/div[1]")).getAttribute("aria-disabled");
            if (RightArrowStatus.contains("false") && LeftArrowStatus.contains("true")) {
                return LeftArrowStatus;
            } else {
                return RightArrowStatus;
            }
        }
        else {
            return "vendors are less than or equal 5";
        }
    }
    public String GetTiltleOfRecentlySection(){
        String TilteOfRecentlySection = driver.findElement(By.xpath("/html/body/div[8]/div/div[1]/div[1]/h2")).toString();
        return TilteOfRecentlySection;
    }
    int CountOfVendorWebsite;
    public String Get_All_Name_Of_Vendors() {
        try {
                for (int i=1; i <= 10; i++) {
                    WebElement VendorElement = driver.findElement(By.xpath("/html/body/div[8]/div/div[2]/div/div/div[" + i + "]/div[2]/div[1]"));
                    if(i != 6){
                    String VendorName = VendorElement.getText();
                    Vendore_Name_Website.add(VendorName);
                    }else {
                        int x = 6;
                        String RightButtonStatus = driver.findElement(By.xpath("/html/body/div[8]/div/div[1]/div[2]/div[2]")).getAttribute("aria-disabled");
                        while (RightButtonStatus != "true") {
                            driver.findElement(By.xpath("/html/body/div[8]/div/div[1]/div[2]/div[2]")).click();
                            Thread.sleep(1500);
                            WebElement VendorElement2 = driver.findElement(By.xpath("/html/body/div[8]/div/div[2]/div/div/div[" + x + "]/div[2]/div[1]"));
                            String VendorName = VendorElement2.getText();
                            Vendore_Name_Website.add(VendorName);
                            x++;
                        }
                    }
                    CountOfVendorWebsite = Vendore_Name_Website.size();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return String.valueOf(Vendore_Name_Website);
    }
    public boolean Check_Vendor_In_DB(){
        Get_All_Name_Of_Vendors();
        try {
            for (int i = 0; i < 10; i++) {
                String GetVendor = Vendore_Name_Website.get(i);
                MongoCollection<Document> collection1 = database.getCollection("Vendors");
                Document doc1 = collection1.find(eq("name.en",GetVendor)).first();
                if (doc1 != null) {
                    Document name = (Document) doc1.get("name");
                    VendorNameDB = name.get("en").toString();
                    Vendors_Name_DB.add(VendorNameDB);
                }else {
                    System.out.println("Vendor name not found in DB");
                    return false;
                }
            }
        }catch (Exception e)
        {
            System.out.println(e);
        }
        return true;
    }
    public Vendor_Details ClickOnViewProductButton() throws InterruptedException {

        driver.findElement(By.xpath("/html/body/div[8]/div/div[2]/div/div/div[1]/div[2]/div[2]/a")).click();
        return new Vendor_Details(driver);
    }
    public Featured_Brands ClickOnSeeMoreButton(){
        driver.findElement(By.xpath("")).click();
        return new Featured_Brands(driver);
    }
    String FirstVendorName="";
    public String GetTheFirstVendorName(){
        WebElement FirstVendorNameElement = driver.findElement(By.xpath("/html/body/div[8]/div/div[2]/div/div/div[2]/div[2]/div[1]/h2/a"));
        FirstVendorName = FirstVendorNameElement.getText();
        return FirstVendorName;
    }

}
