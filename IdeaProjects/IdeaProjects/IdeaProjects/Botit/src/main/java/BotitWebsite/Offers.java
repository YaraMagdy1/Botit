package BotitWebsite;

import com.mongodb.client.*;
import com.mongodb.client.model.Projections;
import org.bson.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.ne;

public class Offers {
    WebDriver driver;

    public Offers(WebDriver driver) {
        this.driver = driver;
    }

    MongoClient mongoClient = MongoClients.create("mongodb+srv://transmission_dev:K1IPfykYMq6FAUv6@botit-dev.jwtve.mongodb.net/botitdev?retryWrites=true&w=majority");
    MongoDatabase database = mongoClient.getDatabase("botitdev");
    ArrayList<String> Name_OF_All_Items_Website = new ArrayList<>();
    ArrayList<String> Discount_Price_Website = new ArrayList<>();
    ArrayList<String> Discount_Status_true = new ArrayList<>();
    ArrayList<String> Discount_Status_false = new ArrayList<>();
    ArrayList<String> Get_All_Items_Name_DB = new ArrayList<>();
    ArrayList<String> Price_After_Discount_DB = new ArrayList<>();
    ArrayList<String> Percentage_DB = new ArrayList<>();
    ArrayList<String> Real_Price_DB = new ArrayList<>();
    ArrayList<String> Percentage_Website = new ArrayList<>();
    public BotitWebsite.Product_Details Product_Details;
    ArrayList<String> Real_Price_Website = new ArrayList<>();
    ArrayList<String> Name_Of_Refreshing_Items = new ArrayList<>();
    ArrayList<String> Repeated_Items = new ArrayList<>();
    ArrayList<String> Changed_Items = new ArrayList<>();
    public String CheckDefaultCaseOfOffersSection() {
        String RightArrowStatus = "";
        String LeftArrowStatus = "";
        GetAllNameOfItems();
        MultiClickOnLeftArrow();
        if (Name_OF_All_Items_Website.size() > 3) {
            RightArrowStatus = driver.findElement(By.xpath("/html/body/div[9]/div/div[1]/div[2]/div[2]")).getAttribute("aria-disabled");
            LeftArrowStatus = driver.findElement(By.xpath("/html/body/div[9]/div/div[1]/div[2]/div[1]")).getAttribute("aria-disabled");

            if (RightArrowStatus.contains("false") && LeftArrowStatus.contains("true")) {
                return RightArrowStatus; // return->false
            } else {
                return LeftArrowStatus; //return-> true
            }
        } else {
            return "Scrollable buttons are not available OR Items is Less than 3";
        }
    }
    public String SingleClickOnRightArrow() {
        WebElement LastItemElement = driver.findElement(By.xpath("/html/body/div[9]/div/div[2]/div[2]/div/div/div[3]"));
        String LastItem = LastItemElement.getText();
        driver.findElement(By.xpath("/html/body/div[9]/div/div[1]/div[2]/div[2]")).click();
        try {
            WebElement NewFirstItemElement = driver.findElement(By.xpath("/html/body/div[9]/div/div[2]/div[2]/div/div/div[4]"));
            String NewFirstItem = NewFirstItemElement.getText();
            String LeftArrowAvailability = driver.findElement(By.xpath("/html/body/div[9]/div/div[1]/div[2]/div[1]")).getAttribute("aria-disabled").toString();
            String RightArrowAvailability = driver.findElement(By.xpath("/html/body/div[9]/div/div[1]/div[2]/div[2]")).getAttribute("aria-disabled").toString();
            if (LeftArrowAvailability.contains("false") && RightArrowAvailability.contains("false")) {
                if (LastItem != NewFirstItem) {
                    return "true";
                } else {
                    return "false";
                }
            } else {
                return "false";
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return "true";
    }
    public String MultiClickOnRightArrow() {
        String RightArrow = driver.findElement(By.xpath("/html/body/div[9]/div/div[1]/div[2]/div[2]")).getAttribute("aria-disabled").toString();
        try {
            while (RightArrow != "true") {
                driver.findElement(By.xpath("/html/body/div[9]/div/div[1]/div[2]/div[2]")).click();
            }
            return "true";
        } catch (Exception e) {
            String LeftArrow = driver.findElement(By.xpath("/html/body/div[9]/div/div[1]/div[2]/div[1]")).getAttribute("aria-disabled").toString();
            if (LeftArrow == "false") {
                return "we reached to the last Item with gray displayed";
            }
            return "false";
        }
    }
        public String MultiClickOnLeftArrow() {
            String LeftArrow = driver.findElement(By.xpath("/html/body/div[9]/div/div[1]/div[2]/div[1]")).getAttribute("aria-disabled").toString();
            try {
                while (LeftArrow != "true") {
                    driver.findElement(By.xpath("/html/body/div[9]/div/div[1]/div[2]/div[1]")).click();
                }
                return "true";
            } catch (Exception e) {
                String Rightbutton = driver.findElement(By.xpath("/html/body/div[9]/div/div[1]/div[2]/div[2]")).getAttribute("aria-disabled").toString();
                if (Rightbutton == "false") {
                    return "we reached to the First Item with gray displayed";

                }
            }
            return "true";
        }

    public String SingleClickOnLeftArrow() {
        GetAllNameOfItems();
        int i=Name_OF_All_Items_Website.size();
        MultiClickOnRightArrow();
        WebElement LastItemElement = driver.findElement(By.xpath("/html/body/div[9]/div/div[2]/div[2]/div/div/div["+ i +"]/div[2]/div[1]/h2"));
        String LastItem = LastItemElement.getText();
        driver.findElement(By.xpath("/html/body/div[9]/div/div[1]/div[2]/div[1]")).click();
        try {
            WebElement NewFirstItemElement = driver.findElement(By.xpath("/html/body/div[9]/div/div[2]/div[2]/div/div/div["+ (i-3) +"]/div[2]/div[1]/h2"));
            String NewFirstItem = NewFirstItemElement.getText();
            String LeftArrowAvailability = driver.findElement(By.xpath("/html/body/div[9]/div/div[1]/div[2]/div[1]")).getAttribute("aria-disabled").toString();
            String RightArrowAvailability = driver.findElement(By.xpath("/html/body/div[9]/div/div[1]/div[2]/div[2]")).getAttribute("aria-disabled").toString();
            if (LeftArrowAvailability.contains("false") && RightArrowAvailability.contains("false")) {
                if (LastItem != NewFirstItem) {
                    return "true";
                } else {
                    return "false";
                }
            } else {
                return "false";
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return "true";
    }
    public String GetAllNameOfItems() {

        String AllItems = "";
        String RightButton= driver.findElement(By.xpath("/html/body/div[9]/div/div[1]/div[2]/div[2]")).getAttribute("aria-disabled");
        int i;
        try {
            for (i = 1; i < 4; i++) {
                WebElement AllItemsElement = driver.findElement(By.xpath("/html/body/div[9]/div/div[2]/div[2]/div/div/div[" + i + "]/div[2]/div[1]/h2"));
                AllItems = AllItemsElement.getText();
                Name_OF_All_Items_Website.add(AllItems);
            }
            if (i == 4) {
                while (RightButton != "true") {
                    driver.findElement(By.xpath("/html/body/div[9]/div/div[1]/div[2]/div[2]")).click();
                    Thread.sleep(1500);
                    WebElement ItemNameAfterClickingOnRightButton = driver.findElement(By.xpath("/html/body/div[9]/div/div[2]/div[2]/div/div/div[" + i + "]/div[2]/div[1]/h2"));
                    AllItems = ItemNameAfterClickingOnRightButton.getText();
                    Name_OF_All_Items_Website.add(AllItems);
                    i++;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return String.valueOf(Name_OF_All_Items_Website);
    }
    public boolean CheckItemsInDB() {
        GetAllNameOfItems();
        String ItemFromDB;
        try {
            for (int i = 0; i < Name_OF_All_Items_Website.size(); i++) {
                String CheckItem = Name_OF_All_Items_Website.get(i);
                MongoCollection<Document> collection1 = database.getCollection("Items");
                Document doc1 = collection1.find(eq("name.en", CheckItem)).first();
                if (doc1 != null) {
                    Document A = (Document) doc1.get("name");
                    ItemFromDB = A.get("en").toString();
                    Get_All_Items_Name_DB.add(ItemFromDB);
                } else {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return true;
    }
    public String GetDiscountItemsFromWebsite() {
        GetAllNameOfItems();
        int i;
        String PriceAfterDiscount;
        String RightButton=driver.findElement(By.xpath("/html/body/div[9]/div/div[1]/div[2]/div[2]")).getAttribute("aria-disabled");
        try {
            for (i = 1; i < Name_OF_All_Items_Website.size(); i++) {
                WebElement PriceAfterDiscountElement = driver.findElement(By.xpath("/html/body/div[9]/div/div[2]/div[2]/div/div/div[" + i + "]/div[2]/div[2]/div[1]/span"));
                PriceAfterDiscount = PriceAfterDiscountElement.getText();
                Discount_Price_Website.add(PriceAfterDiscount);
            }
            if (i == 4) {
                while (RightButton != "true") {
                    driver.findElement(By.xpath("/html/body/div[9]/div/div[1]/div[2]/div[2]")).click();
                    Thread.sleep(1500);
                    WebElement PriceAfterDiscountElement1 = driver.findElement(By.xpath("/html/body/div[9]/div/div[2]/div[2]/div/div/div[" + i + "]/div[2]/div[2]/div[1]/span"));
                    PriceAfterDiscount = PriceAfterDiscountElement1.getText();
                    Discount_Price_Website.add(PriceAfterDiscount);
                    i++;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return String.valueOf(Discount_Price_Website);
    }
    public String CheckDiscountStatusFromDB() {
        GetAllNameOfItems();
        String ItemName;
        for (int i = 0; i < Name_OF_All_Items_Website.size(); i++){
            ItemName = Name_OF_All_Items_Website.get(i);
            MongoCollection<Document> collection2 = database.getCollection("Items");
            Document doc1 = collection2.find(eq("name.en", ItemName))
                    .projection(new Document("discount", 1))
                    .first();
            if (doc1 != null) {
                Document discount = (Document) doc1.get("discount");
                String GetActive = discount.get("active").toString();
                String GetValue = discount.get("value").toString();
                if (GetActive != "") {
                    if (GetValue != "") {
                        if (GetActive.contains("true")) {
                            Discount_Status_true.add(ItemName);
                        } else if (GetActive == "false") {
                            Discount_Status_false.add(ItemName);
                        }
                    }
                }
            }
        }
        return String.valueOf(Discount_Status_true);
    }
    public String CalculateDiscount() {
        CheckDiscountStatusFromDB();
        String ItemName;
        MongoCollection<Document> collection1 = database.getCollection("Items");
        for (int i = 0; i < Discount_Status_true.size(); i++) {
            ItemName = Discount_Status_true.get(i);
            Document doc2 = collection1.find(eq("name.en", ItemName))
                    .first();
            if (doc2 != null) {
                int Price = (int) doc2.get("price");
                Real_Price_DB.add(String.valueOf(Price));
                Document discount = (Document) doc2.get("discount");
                String PercentageOfDiscount = discount.get("value").toString();
                Percentage_DB.add(PercentageOfDiscount);
                int s = 100 - Integer.parseInt(PercentageOfDiscount.intern());
                int NewPrice = (s * Price) / 100;
                Price_After_Discount_DB.add(String.valueOf(NewPrice));
            }
        }
        return String.valueOf(Price_After_Discount_DB);
    }
    public boolean CompareDiscountedItems() {
        GetDiscountItemsFromWebsite();
        CalculateDiscount();
        for (int i = 0; i < Discount_Price_Website.size(); i++) {

            if (Discount_Price_Website.contains(Price_After_Discount_DB.get(i))) {
                return true;
            } else {
                return false;
            }

        }
        return true;
    }
    public boolean ComparePercentage() {
        GetAllNameOfItems();
        CalculateDiscount();
        String Percentage;
        try {
            for (int i = 1; i < Name_OF_All_Items_Website.size(); i++) {
                WebElement PercentageElement = driver.findElement(By.xpath("/html/body/div[9]/div/div[2]/div[2]/div/div/div[" + i + "]/div[1]/span"));
                Percentage = PercentageElement.getText();
                Percentage_Website.add(Percentage);
            }
            for (int j = 0; j < Percentage_Website.size(); j++)
                if (Percentage_Website.contains(Percentage_DB.get(j))) {

                } else {
                    return false;
                }
        } catch (Exception e) {
            System.out.println(e);
        }
        return true;
    }
    public boolean CheckPriceBeforeDiscount() {
        GetDiscountItemsFromWebsite();
        String RealPriceFromWebsite;
        CalculateDiscount();
        String ItemName;
        try {
            for (int i = 1; i < Discount_Price_Website.size(); i++) {
                WebElement RealPriceElement = driver.findElement(By.xpath("/html/body/div[9]/div/div[2]/div[2]/div/div/div[" + i + "]/div[2]/div[2]/div[1]/p"));
                RealPriceFromWebsite = RealPriceElement.getText();
                Real_Price_Website.add(RealPriceFromWebsite);
            }
            for (int j = 0; j < Real_Price_Website.size(); j++) {
                if (Real_Price_Website.contains(Real_Price_DB.get(j))) {

                } else {
                    return false;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return true;
    }
    /*public String GetVendorsIdFromDB() {
        GetAllNameOfItems();
        GetAllVendorsName();
        String VendorId = "";
        String ItemName1="";
        VerifyItemsInDB();
        try {
            MongoCollection<Document> collection1 = database.getCollection("Items");
            for (int j = 0; j < Get_Popular_Products.size(); j++) {
                ItemName1 = Get_Popular_Products.get(j);
                Document doc1 = collection1.find(eq("name.en", ItemName1))
                        .projection(new Document("_vendor", 1))
                        .first();
                if (doc1 != null) {
                    String x= doc1.get("_vendor").toString();
                    DB_Vendors_ID.add(x);
                }
            }
        }catch (Exception e) {
            System.out.println(e);
        }
        return String.valueOf(DB_Vendors_ID);
    }//get all related vendors
    public void CheckRelatedVendors(){ //After deployment

    }*/
    public String RefreshingPage() {
        GetAllNameOfItems();
        driver.get("file:///C:/Users/admin/Desktop/Botit/Index.html");
        driver.navigate().refresh();
        String RefreshingItems = "";
        String Rightbutton = driver.findElement(By.xpath("/html/body/div[9]/div/div[1]/div[2]/div[2]")).getAttribute("aria-disabled").toString();
        int i;
        try {
            for (i = 1; i < 4; i++) {
                WebElement AllItemsElement = driver.findElement(By.xpath("/html/body/div[9]/div/div[2]/div[2]/div/div/div[" + i + "]/div[2]/div[1]/h2"));
                RefreshingItems = AllItemsElement.getText();
                Name_Of_Refreshing_Items.add(RefreshingItems);
            }
            if (i == 4) {
                while (Rightbutton != "true") {
                    driver.findElement(By.xpath("/html/body/div[9]/div/div[1]/div[2]/div[2]")).click();
                    Thread.sleep(1500);
                    WebElement ItemNameAfterClickingOnRightButton = driver.findElement(By.xpath("/html/body/div[9]/div/div[2]/div[2]/div/div/div[" + i + "]/div[2]/div[1]/h2"));
                    RefreshingItems = ItemNameAfterClickingOnRightButton.getText();
                    Name_Of_Refreshing_Items.add(RefreshingItems);
                    i++;
                }
            }
            for (int j = 0; j < Name_OF_All_Items_Website.size(); j++) {
                if (Name_OF_All_Items_Website.contains(Name_Of_Refreshing_Items.get(j))) {
                    //System.out.println("An Item" +RefreshingItems+ "is repeated after refreshing page");
                    Repeated_Items.add(RefreshingItems);
                } else {
                    Changed_Items.add(RefreshingItems);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return String.valueOf(Repeated_Items);
    }
    public BotitWebsite.Common_Methods Common_Methods;
    ArrayList<String> Valid_Matched_Items =new ArrayList<>();
    ArrayList<String> NotValid_Matched_Items =new ArrayList<>();
    String Title;
    public String TitleOfOfferSection(){
        WebElement Titleelement = driver.findElement(By.xpath("/html/body/div[9]/div/div[1]/div[1]/h2"));
        Title = Titleelement.getText();
        return Title;
    }
   /* public ArrayList<String> ClickOnViewItemButton(){
        String StepName;
        for(int i=1 ; i<= Name_OF_All_Items_Website.size(); i++) {
           String NameOfITem= Name_OF_All_Items_Website.get(i);
           driver.findElement(By.xpath("/html/body/div[9]/div/div[2]/div[2]/div/div/div["+i+"]/div[2]/div[2]/div[2]/a")).click();
           String Title= Product_Details.CheckTitleOfItem();
           if(NameOfITem== Title){
               Valid_Matched_Items.add(NameOfITem);
               StepName="Step1 Right Navigate with Matched Items";
               Common_Methods.Screenshot(StepName);
               driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/ul/li[1]")).click();
           }else {
               NotValid_Matched_Items.add(NameOfITem);
               StepName="Step1 Not Matched Items";
               Common_Methods.Screenshot(StepName);
               driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/ul/li[1]")).click();
           }
       }
        return NotValid_Matched_Items;
    }*/
   String ItemName;
   String DiscountItme;
    public Product_Details ClickOnViewBtn(){
        //for (int i=1 ; i<3 ; i++) {
            WebElement ItemNameElement = driver.findElement(By.xpath("/html/body/div[9]/div/div[2]/div[2]/div/div/div[1]/div[2]/div[1]/h2/a"));
            ItemName = ItemNameElement.getText();
            WebElement DiscountItmeElement = driver.findElement(By.xpath("/html/body/div[9]/div/div[2]/div[2]/div/div/div[1]/div[2]/div[2]/div[1]/span"));
            DiscountItme = DiscountItmeElement.getText();
            driver.findElement(By.xpath("/html/body/div[9]/div/div[2]/div[2]/div/div/div[1]/div[2]/div[2]/div[2]/a")).click();

        return new Product_Details(driver);
    }
    public Product_Details ClickOnItemName (){
        driver.findElement(By.xpath("/html/body/div[9]/div/div[2]/div[2]/div/div/div[2]/div[2]/div[1]/h2")).click();
        return new Product_Details(driver);
    }
    public Product_Details ClickOnImageOfItem(){
        driver.findElement(By.xpath("/html/body/div[9]/div/div[2]/div[2]/div/div/div[2]/div[1]/a")).click();
        return new Product_Details(driver);
    }
    public Offers_Page ClickOnSeeMoreButton(){
        driver.findElement(By.xpath("")).click();
        return new Offers_Page(driver);
    }
    public Vendor_Details ClickOnVendorName(){
        driver.findElement(By.xpath("")).click();
        return new Vendor_Details(driver);
    }


}

