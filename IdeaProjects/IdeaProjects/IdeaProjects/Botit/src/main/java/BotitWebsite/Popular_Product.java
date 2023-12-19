package BotitWebsite;

import com.mongodb.client.*;
import com.mongodb.client.model.Projections;
import org.bson.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.bson.types.ObjectId;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import static com.mongodb.client.model.Filters.eq;

public class Popular_Product {
    WebDriver driver;
    ArrayList<String> Get_Popular_Products = new ArrayList<>();
    ArrayList<String> Get_Changing_Popular_Products = new ArrayList<>();
    ArrayList<String> Get_Popular_products_count = new ArrayList<>();
    ArrayList<String> ItemsWithNoDiscount = new ArrayList<>();
    ArrayList<String> ItemsWithDiscount = new ArrayList<>();
    ArrayList<String> Get_Vendors = new ArrayList<>();
    ArrayList<String> Get_Items = new ArrayList<>();
    ArrayList<String> Supported_Items = new ArrayList<String>();
    ArrayList<String> UnSupported_Items = new ArrayList<>();
    MongoClient mongoClient = MongoClients.create("mongodb+srv://transmission_dev:K1IPfykYMq6FAUv6@botit-dev.jwtve.mongodb.net/botitdev?retryWrites=true&w=majority");
    MongoDatabase database = mongoClient.getDatabase("botitdev");
    ArrayList<String> IdValue = new ArrayList<String>();
    ArrayList<String> ItemsNotFound = new ArrayList<String>();
    ArrayList<String> Instock = new ArrayList<String>();
    ArrayList<String> Outofstock = new ArrayList<String>();
    ArrayList<String> New_Refreshing_Items = new ArrayList<>();
    String ItemName = "";
    String Id = "";
    String NewProduct = "";
    String NewItem = "";
    String Product_Count = "";
    String Product_Name = "";
    String VendorId = "";
    ArrayList<String> DB_Vendors_ID = new ArrayList<>();
    ArrayList<String> UnRelated_Vendors = new ArrayList<>();
    ArrayList<String> DB_Vendors_Name = new ArrayList<>();
    String Items_Name = "";
    String Vendors_Name = "";
    String VendorName = "";
    ArrayList<String> Website_Vendors_Name = new ArrayList<>();
    ArrayList<String> Discounted_Items_DB = new ArrayList<>();
    ArrayList<String> Discounted_Items_Website = new ArrayList<>();
    String Price = "";

    public Popular_Product(WebDriver driver) {
        this.driver = driver;
    }
    public String GetCountOfPopularProduct() {
        String Count = "";
        int i = 1;
        try {
            while (i != 11) {
                WebElement product = driver.findElement(By.xpath("/html/body/div[7]/div/div[2]/ul/li["+ i +"]/div[2]/h2"));
                Product_Name = product.getText();
                Get_Popular_Products.add(Product_Name);
                WebElement Productcount = driver.findElement(By.xpath("/html/body/div[7]/div/div[2]/ul/li["+ i +"]/div[2]/h2"));
                Product_Count = Productcount.getText();
                Get_Popular_products_count.add(Product_Count);
                Get_Popular_products_count.size();
                i++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    public String GetAllPopularProducts() {
        try {
            for (int i = 1; i <= 10; i++) {
                WebElement product = driver.findElement(By.xpath("/html/body/div[7]/div/div[2]/ul/li[" + i + "]/div[2]/h2/a"));
                Product_Name = product.getText();
                Get_Popular_Products.add(Product_Name);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    public String VerifyItemsInDB() {
        GetCountOfPopularProduct();
        try {
            MongoCollection<Document> collection1 = database.getCollection("Items");
            for (int j = 0; j < 10; j++) {
                ItemName = Get_Popular_Products.get(j);
                Document doc1 = collection1.find(eq("name.en", ItemName))
                        .projection(new Document("Document._id", 1))
                        .first();
                if (doc1 != null) {
                    Id = doc1.toJson();
                    IdValue.add(Id);
                    return "true";
                } else {
                    ItemsNotFound.add(ItemName);
                    return "false";
                }
            }

        } catch (Exception e) {
        }
        return "true";

    }
    public String InstockItems() {
        String InstockStatus = "";
        GetCountOfPopularProduct();
        CheckItemAvailability();
        try {
            for (int i = 0; i < 10; i++) {
                MongoCollection<Document> collection = database.getCollection("Items");
                ItemName = Supported_Items.get(i);
                //ItemName = Get_Popular_Products.get(i);
                FindIterable<Document> iterDoc = collection.find(eq("name.en", ItemName))
                        .projection(Projections.include("variants.inventory.inStock"));
                Iterator it = iterDoc.iterator();
                Document x;
                while (it.hasNext()) {
                    x = (Document) it.next();
                    ArrayList<Document> y = (ArrayList<Document>) (x.get("variants"));
                    ArrayList<Document> z = (ArrayList<Document>) (y.get(0).get("inventory"));
                    if (z.size() != 0) {
                        String h = z.get(0).get("inStock").toString();
                        if (h == "true" || h == "[true]") {
                            Instock.add(ItemName);
                        }
                    } else {
                        Outofstock.add(ItemName);
                        return "false";
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return "true";
    }
    public String CheckItemAvailability() {
        GetCountOfPopularProduct();

        try {
            for (int i = 0; i < 10; i++) {
                MongoCollection<Document> collection = database.getCollection("Items");
                ItemName = Get_Popular_Products.get(i);
                FindIterable<Document> iterDoc = collection.find(eq("name.en", ItemName))
                        .projection(Projections.include("variants.inventory.inStock"));
                Iterator it = iterDoc.iterator();
                Document x;
                while (it.hasNext()) {
                    x = (Document) it.next();
                    ArrayList<Document> y = (ArrayList<Document>) (x.get("variants"));
                    if (y.size() != 0) {
                        ArrayList<Document> z = (ArrayList<Document>) (y.get(0).get("inventory"));
                        if (z.size() != 0) {
                            String h = z.get(0).get("inStock").toString();
                            if (h == "true" || h == "[true]") {
                                System.out.print("Item suppoed " + ItemName);
                                Supported_Items.add(ItemName);
                            }
                            else
                            {
                                System.out.print("Items not supported " + ItemName);
                                UnSupported_Items.add(ItemName);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Item" + ItemName + "not supported");
            UnSupported_Items.add(ItemName);
        }
        return String.valueOf(UnSupported_Items);
    }
    public String RefreshingPage() {
        GetAllPopularProducts();
        driver.get("file:///C:/Users/admin/Desktop/Botit/Index.html");
        driver.navigate().refresh();
        try {
            for (int i = 0; i <10; i++) {
                Get_Popular_Products.get(i);
                WebElement ItemNameElement = driver.findElement(By.xpath("/html/body/div[7]/div/div[2]/ul/li[" + (i+1) + "]/div[2]/h2/a"));
                NewItem = ItemNameElement.getText();
                New_Refreshing_Items.add(NewItem);
                if (Get_Popular_Products.contains(New_Refreshing_Items.get(i))) {
                    return "false";
                } else {
                    Get_Changing_Popular_Products.add(NewItem);
                    return "true";
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return "true";
    }
    public String StatusOfViewItemButton() {
        int y = 1;
        String Status = "";
        try {
            while (y != 11) {
                driver.findElement(By.xpath("/html/body/div[7]/div/div[2]/ul/li[" + y + "]/div[3]/div[2]/a")).click();
                Status = "Button is clickable";
                y++;
            }

        } catch (Exception e) {
            Status = "Button is not clickable";
        }
        return null;
    }
    public Product_Details ClickOnViewItemButton() {
            driver.findElement(By.xpath("/html/body/div[7]/div/div[2]/ul/li[1]/div[3]/div[2]/a")).click();
            return new Product_Details(driver);
    }
    String NameOfFirstItem="";
     public String GetTheFirstItemName(){
        // WebElement NameOfFirstItemElement = driver.findElement(By.xpath("/html/body/div[7]/div/div[2]/ul/li[1]/div[2]/h2/a"));
          WebElement NameOfFirstItemElement = driver.findElement(By.xpath("/html/body/div[7]/div/div[2]/ul/li[1]/div[2]/h2"));
         NameOfFirstItem = NameOfFirstItemElement.getText();
         return NameOfFirstItem;
         //html/body/div[7]/div/div[2]/ul/li[1]/div[2]/h2/a
     }

    public String GetAllVendorsName() {
        for (int i = 1; i <= 10; i++) {
                WebElement Vendor = driver.findElement(By.xpath("/html/body/div[7]/div/div[2]/ul/li[" + i+ "]/div[2]/span/a"));
                VendorName = Vendor.getText();
                Website_Vendors_Name.add(VendorName);
            }
            return String.valueOf(Website_Vendors_Name);
        }
    public String GetVendorsIdFromDB() {
        GetAllPopularProducts();
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
    }
    ArrayList<String> MatchedVendors = new ArrayList<>();
    public boolean CompareVendorsName(){
        GetVendorsIdFromDB();
        try {
                   for (int i = 0; i < DB_Vendors_ID.size(); i++) {
                        MongoCollection<Document> collection3 = database.getCollection("Vendors");
                        VendorId = DB_Vendors_ID.get(i);
                       Document doc2 = collection3.find(eq("_id", new ObjectId(VendorId)))
                               .projection(new Document("name.en",1))
                               .first();
                        if (doc2 != null) {
                            Document c = (Document) doc2.get("name");
                            Vendors_Name = c.get("en").toString();
                            DB_Vendors_Name.add(Vendors_Name);
                        }
                    }
                    for (int y = 0; y < 10; y++) {
                        if (DB_Vendors_Name.contains(Website_Vendors_Name.get(y))) {
                        } else {
                            return false;
                        }
                    }
            } catch (Exception e) {
            System.out.println(e);
        }
        return true;
    }

    public void CheckItemPrice() {
        InstockItems();
        try {
            MongoCollection<Document> collection1 = database.getCollection("Items");
            for (int j = 0; j < 10; j++) {
                ItemName = Instock.get(j);
                Document doc1 = collection1.find(eq("name.en", ItemName))
                        .projection(new Document("discount", 1))
                        .first();
                if (doc1 != null) {
                    Document discount = (Document) doc1.get("discount");

                  String GetActive = discount.get("active").toString();
                    String GetValue = discount.get("value").toString();
                    if(GetActive != "") {
                        //System.out.println(GetActive);
                        if(GetValue != "") {
                            if (GetActive == "true"){
                                ItemsWithDiscount.add(ItemName);
                            } else if (GetActive == "false" ) {
                                ItemsWithNoDiscount.add(ItemName);
                            }
                        }
                    }

                }
                else {
                    ItemsWithNoDiscount.add(ItemName);
                }
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    public  void CalculateItemAfterDiscount() {
        CheckItemPrice();
        String PoundType= "LE";
        MongoCollection<Document> collection1 = database.getCollection("Items");
        for (int j = 0; j < ItemsWithDiscount.size() ; j++) {
            ItemName = ItemsWithDiscount.get(j);
            Document doc1 = collection1.find(eq("name.en", ItemName))
                    .first();
            if (doc1 != null) {
                int Price = (int) doc1.get("price");

                Document discount = (Document) doc1.get("discount");
                String PercentageOfDiscount = discount.get("value").toString();
                int s = 100 - Integer.parseInt(PercentageOfDiscount.intern());
                int NewPrice = (s * Price) / 100;
                Discounted_Items_DB.add(String.valueOf(NewPrice));
            }
        }
    }
    public String ComperBetweenDiscountPrice (){
        CalculateItemAfterDiscount();
        try {
            for (int i = 1; i <= 10; i++) {
                WebElement PriceAfterDiscount = driver.findElement(By.xpath("/html/body/div[7]/div/div[2]/ul/li["+ i +"]/div[3]/div[1]/span"));
                Price = PriceAfterDiscount.getText();
                Discounted_Items_Website.add(Price);
            }
            for (int x = 0; x < 10; x++) {
                if (Discounted_Items_DB.contains(Discounted_Items_Website.get(x))) {
                    System.out.println("The Discounted price is calculate right");
                } else {
                    System.out.println("The Discounted of item:" + ItemName + "is not calculate correct");
                    return "false";
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return "true";
    }
}
