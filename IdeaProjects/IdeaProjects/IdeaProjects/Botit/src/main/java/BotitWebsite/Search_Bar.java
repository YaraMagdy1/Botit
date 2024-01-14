package BotitWebsite;

import com.amazonaws.services.dynamodbv2.model.Select;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import com.amazonaws.services.dynamodbv2.xspec.B;
import jdk.jfr.internal.test.WhiteBox;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.amazonaws.services.securityhub.model.Product;
import com.google.common.collect.Iterators;
import com.mongodb.client.*;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.bson.Document;
import org.openqa.selenium.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static com.mongodb.client.model.Filters.bitsAllClear;
import static com.mongodb.client.model.Filters.eq;
import static java.lang.Thread.sleep;

public class Search_Bar  {


    WebDriver driver;

    ArrayList<String> Count_Of_Products = new ArrayList<>();
    ArrayList<String> Count_Of_Vendors = new ArrayList<>();

    public Search_Bar(WebDriver driver) {
        this.driver = driver;
    }

    public void GetItemsResults(){
        List<WebElement> Items = driver.findElements(By.xpath("/html/body/div[7]/div[1]/div/div/div[2]/ul"));

    }

    public String SearchForEmptyValue() {

        driver.findElement(By.xpath("/html/body/div[2]/div/form/div/label[2]")).click();
        WebElement MessageElement =driver.findElement(By.xpath("/html/body/div[5]/div/div/p"));
        String Message = MessageElement.getText();
        return Message;
    }
ArrayList<String > Result_Items = new ArrayList<>();
    public int CountProduct(){
        int counter = 1;
        String m;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[7]/div[1]/div/div/div[2]/ul")));

        WebElement Page_Num = driver.findElement(By.xpath("/html/body/div[7]/div[2]/div/div/div/ul/li[1]/a"));
        if(Page_Num != null){
        WebElement Items = driver.findElement(By.xpath("html/body/div[7]/div[1]/div/div/div[2]/ul"));
        List<WebElement> list= Items.findElements(By.tagName("li"));
        int y =list.size();
        for (int i=0 ; i<y ; i++){
            m =list.get(i).getText().toString();
            Result_Items.add(m);
        }try {

            for (int j =2 ; j>1 ; j++) {
                driver.findElement(By.xpath("/html/body/div[7]/div[2]/div/div/div/ul/li["+j+"]/a")).click();
                WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(2000));
                //wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[7]/div[1]/div/div/div[2]/ul")));

                //driver.findElement(By.xpath("/html/body/div[7]/div[2]/div/div/div/ul/li[3]/a")).click();
                wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[7]/div[1]/div/div/div[2]/ul")));
                WebElement Items2 = driver.findElement(By.xpath("html/body/div[7]/div[1]/div/div/div[2]/ul"));
                List<WebElement> list2 = Items2.findElements(By.tagName("li"));
                for (int k = 0; k < y; k++) {
                    String m2 = list.get(k).getText().toString();
                    Result_Items.add(m2);
                }
            }
        }catch (Exception e){
            int j=3;
                if (j<=3) {
                    for( j=3 ; j>=3 ; j++) {
                        driver.findElement(By.xpath("/html/body/div[7]/div[2]/div/div/div/ul/li[" + (j + 1) + "]/a")).click();
                        WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(2000));
                        wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[7]/div[1]/div/div/div[2]/ul")));
                        WebElement Items3 = driver.findElement(By.xpath("html/body/div[7]/div[1]/div/div/div[2]/ul"));
                        List<WebElement> list3 = Items3.findElements(By.tagName("li"));
                        for (int i = 0; i < y; i++) {
                            m = list.get(i).getText().toString();
                            Result_Items.add(m);
                        }
                    }
        }
        }

    }    return Result_Items.size();
    }
    public int CountForSearchProducts(){
        int counter = 1;
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
       // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("html/body/div[7]/div[1]/div/div/div[2]/ul")));

       // driver.findElement(By.xpath("/html/body/div[7]/div[2]/div/div/div/ul/li[7]/a")).click();

try {
    WebElement Items = driver.findElement(By.xpath("html/body/div[7]/div[1]/div/div/div[2]/ul"));

    List<WebElement> list = Items.findElements(By.tagName("li"));

    //System.out.println(list);
    int y = list.size();
    for (int i = 0; i < y; i++) {
        //   String r = String.valueOf(list.get(i));
        String m = list.get(i).getText().toString();
        Count_Of_Products.add(m);
        //boolean Displayed = driver.findElement(By.xpath("/html/body/div[7]/div[2]/div/div/div/ul/li[7]")).isDisplayed();
        //if(Displayed== true && i == 9){
        //driver.findElement(By.tagName("a[@title='next page']")).click();
        //button.click
        i = 0;//html/body/div[7]/div[2]/div/div/div/ul/li[2]/a
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5000));
        wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[7]/div[1]/div/div/div[2]/ul/li[1]")));
        //WebElement Items2 = driver.findElement(By.xpath("/html/body/div[7]/div[1]/div/div/div[2]/ul"));
        //List<WebElement> list2= Items2.findElements(By.tagName("li"));
        //list = list2;
        //y= list2.size();
        continue;
    }
}catch (Exception e){
    return Count_Of_Products.size();
}
           // String y =list.get(i).getText();
           // String u = null;

        /*boolean displayed = driver.findElement(By.xpath("/html/body/div[7]/div[2]/div/div/div/ul/li[7]")).isDisplayed();
        if(displayed){
            for (int i = 1; i <= counter; i++) {
                try {
                    WebElement ProductNameElement = driver.findElement(By.xpath("/html/body/div[7]/div[1]/div/div/div[2]/ul/li["+ i +"]"));
                    if (ProductNameElement.isDisplayed()) {
                        String ProductName = ProductNameElement.getText();
                        Count_Of_Products.add(ProductName);
                        counter += 1;
                        if(i == 10 ){

                        }
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

        }*/
        return Count_Of_Products.size();
    }

    public String ClickOnExitButton() throws IOException {
        driver.findElement(By.xpath("/html/body/div[5]/div/form/div/label[1]/span")).click();
        WebElement EnterValueElement = driver.findElement(By.xpath("/html/body/div[5]/div/form/div/input"));
        String EnterValue = EnterValueElement.getText();
        //EnterValue.sendKeys(Product_Name);
        if (EnterValue.equals("")) {
            return "true";
        } else {
            return "false";
        }
    }

    ArrayList<String> Find_Exist_Brand = new ArrayList<>();
    ArrayList<String> Not_Found_Exist_Brand = new ArrayList<>();
    ArrayList<String> Find_Exist_Item = new ArrayList<>();
    ArrayList<String> Not_Found_Exist_Item = new ArrayList<>();
    ArrayList<String> Not_Find_Common_Item_Barnd = new ArrayList<>();
    ArrayList<String> Find_Common_Item_Brand = new ArrayList<>();
    MongoClient mongoClient = MongoClients.create("mongodb+srv://transmission_dev:K1IPfykYMq6FAUv6@botit-dev.jwtve.mongodb.net/botitdev?retryWrites=true&w=majority");
    MongoDatabase database = mongoClient.getDatabase("botitdev");
    public void GoBackToHomePage(){
        driver.findElement(By.xpath("/html/body/div[4]/div/div[1]/div[2]/ul/li[1]/a")).click();
    }
    public ArrayList<String> Search(String Input) {

        driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[1]/form/input")).sendKeys(Input);
        driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[1]/form/button")).click();

        int Count_Products = CountForSearchProducts();
        //int Count_Products = CountProduct();
        int Count_Vendors = CountForSearchVendors();

        //-----------Common search----------
        if(Count_Products >0 && Count_Vendors >0) {
            for (int i = 1; i <= Count_Of_Products.size(); i++) {
                WebElement ProductElement = driver.findElement(By.xpath("/html/body/div[7]/div[1]/div/div/div[2]/ul/li["+i+"]/div[2]/h2"));
                String Product = ProductElement.getText();
                if (Product.compareToIgnoreCase(Input) >= 0 || Product.compareToIgnoreCase(Input)<=0) {
                    Find_Common_Item_Brand.add(Product);
                } else {
                    Not_Find_Common_Item_Barnd.add(Product);
                }
            }
            for (int j = 1; j <= Count_Of_Vendors.size(); j++) {
                WebElement VendorElement = driver.findElement(By.xpath("/html/body/div[6]/div[1]/div/div[2]/ul/li["+j+"]/div[2]/div[1]/h2"));
                String Vendor = VendorElement.getText();
                if (Vendor.compareToIgnoreCase(Input) >= 0 || Vendor.compareToIgnoreCase(Input) <= 0) {
                    Find_Common_Item_Brand.add(Vendor);
                } else {
                    Not_Find_Common_Item_Barnd.add(Vendor);
                }
            }
            return Not_Find_Common_Item_Barnd;
        }//------------Search For Item-------------
        else if(Count_Products > 0 && Count_Vendors == 0){
            for (int i = 1; i <= Count_Of_Products.size(); i++) {
                WebElement ProductNameElement = driver.findElement(By.xpath("/html/body/div[7]/div[1]/div/div/div[2]/ul/li["+ i +"]/div[2]/h2"));
                String ProductName = ProductNameElement.getText();
                //Get_Items_Website.add(ProductName);
                if (Input.compareToIgnoreCase(ProductName) >= 0 || Input.compareToIgnoreCase(ProductName) <= 0) {
                    Find_Exist_Item.add(ProductName);
                } else {
                    Not_Found_Exist_Item.add(ProductName);
                }

            }
            return Not_Found_Exist_Item;
        }//----------Search For Vendor-------------
        else if(Count_Products == 0&& Count_Vendors > 0){
            for (int i = 1; i <= Count_Of_Vendors.size(); i++) {
                WebElement VendorNameElement = driver.findElement(By.xpath("/html/body/div[6]/div[1]/div/div[2]/ul/li["+ i +"]/div[2]/div[1]/h2"));
                String VendorName = VendorNameElement.getText();
                //Get_Items_Website.add(ProductName);
                if (VendorName.compareToIgnoreCase(Input) >= 0 || VendorName.compareToIgnoreCase(Input) <= 0) {
                    Find_Exist_Brand.add(VendorName);
                } else {
                    Not_Found_Exist_Brand.add(VendorName);
                }
            }
            return Not_Found_Exist_Brand;
        }//---------Search For Not Found Item and Vendor--------------
        else if (Count_Products == 0 && Count_Vendors ==0) {
            WebElement MessageElement = driver.findElement(By.xpath("/html/body/div[8]/div/div/p"));
            String AlertMessage = MessageElement.getText();
            if(AlertMessage.equals("No result found")){
            UnExisted_Item_Brand.add(Input);
            }else {
                Find_UnExisted_Item_Brand.add(Input);
            }
            return Find_UnExisted_Item_Brand;
        }
        return null;
    }
    ArrayList<String> Find_Exist_Brand2 = new ArrayList<>();
    ArrayList<String> Not_Found_Exist_Brand2 = new ArrayList<>();
    ArrayList<String> Find_Exist_Item2 = new ArrayList<>();
    ArrayList<String> Not_Found_Exist_Item2 = new ArrayList<>();
    ArrayList<String> Not_Find_Common_Item_Barnd2 = new ArrayList<>();
    ArrayList<String> Find_Common_Item_Brand2 = new ArrayList<>();
    ArrayList<String> UnExisted_Item_Brand2 =new ArrayList<>();
    ArrayList<String> Find_UnExisted_Item_Brand2 =new ArrayList<>();
    public ArrayList<String> Check_Search_Bar_Page(String Value){
        driver.findElement(By.xpath("/html/body/div[5]/div/form/div/input")).sendKeys(Value);
        driver.findElement(By.xpath("/html/body/div[5]/div/form/div/label[2]")).click();
        int Count_Products = CountForSearchProducts();
        int Count_Vendors = CountForSearchVendors();

        //-----------Common search----------
        if(Count_Products >0 && Count_Vendors >0) {
            for (int i = 1; i <= Count_Of_Products.size(); i++) {
                WebElement ProductElement = driver.findElement(By.xpath("/html/body/div[7]/div[1]/div/div/div[2]/ul/li["+i+"]/div[2]/h2"));
                String Product = ProductElement.getText();
                if (Product.compareToIgnoreCase(Value) >= 0 || Product.compareToIgnoreCase(Value)<=0) {
                    Find_Common_Item_Brand2.add(Product);
                } else {
                    Not_Find_Common_Item_Barnd2.add(Product);
                }
            }
            for (int j = 1; j <= Count_Of_Vendors.size(); j++) {
                WebElement VendorElement = driver.findElement(By.xpath("/html/body/div[6]/div[1]/div/div[2]/ul/li["+j+"]/div[2]/div[1]/h2"));
                String Vendor = VendorElement.getText();
                if (Vendor.compareToIgnoreCase(Value) >= 0 || Vendor.compareToIgnoreCase(Value) <= 0) {
                    Find_Common_Item_Brand2.add(Vendor);
                } else {
                    Not_Find_Common_Item_Barnd2.add(Vendor);
                }
            }
            return Not_Find_Common_Item_Barnd2;
        }//------------Search For Item-------------
        else if(Count_Products > 0 && Count_Vendors == 0){
            for (int i = 1; i <= Count_Of_Products.size(); i++) {
                WebElement ProductNameElement = driver.findElement(By.xpath("/html/body/div[7]/div[1]/div/div/div[2]/ul/li["+ i +"]/div[2]/h2"));
                String ProductName = ProductNameElement.getText();
                //Get_Items_Website.add(ProductName);
                if (ProductName.compareToIgnoreCase(Value) >= 0 || ProductName.compareToIgnoreCase(Value) <= 0) {
                    Find_Exist_Item2.add(ProductName);
                } else {
                    Not_Found_Exist_Item2.add(ProductName);
                }

            }
            return Not_Found_Exist_Item2;
        }//----------Search For Vendor-------------
        else if(Count_Products == 0&& Count_Vendors > 0){
            for (int i = 1; i <= Count_Of_Vendors.size(); i++) {
                WebElement VendorNameElement = driver.findElement(By.xpath("/html/body/div[6]/div[1]/div/div[2]/ul/li["+ i +"]/div[2]/div[1]/h2"));
                String VendorName = VendorNameElement.getText();
                //Get_Items_Website.add(ProductName);
                if (VendorName.compareToIgnoreCase(Value) >= 0 || VendorName.compareToIgnoreCase(Value) <= 0) {
                    Find_Exist_Brand2.add(VendorName);
                } else {
                    Not_Found_Exist_Brand2.add(VendorName);
                }
            }
            return Not_Found_Exist_Brand2;
        }//---------Search For Not Found Item and Vendor--------------
        else if (Count_Products == 0 && Count_Vendors ==0) {
            WebElement MessageElement = driver.findElement(By.xpath("/html/body/div[5]/div/div/p"));
            String AlertMessage = MessageElement.getText();
            if(AlertMessage.equals("No result found")){
                UnExisted_Item_Brand2.add(Value);
            }else {
                Find_UnExisted_Item_Brand2.add(Value);
            }
            return Find_UnExisted_Item_Brand;
        }
        return null;
    }
    public boolean ClearTheDataFromSearchPage(String Input2){
       WebElement SearchInput= driver.findElement(By.xpath("/html/body/div[5]/div/form/div/input"));
       SearchInput.sendKeys(Input2);
       driver.findElement(By.xpath("/html/body/div[5]/div/form/div/label[1]/span")).click();
       if(SearchInput.equals(null)){
           return true;
       }else {
           return false;
       }
    }
   /* public String Pagination(String i) throws InterruptedException {
       // for (int i=1;i<)
        wait(5);    //wait until 'loader'  loading
        List<WebElement> pagination = driver.findElements(By.xpath("/html/body/div[7]/div[2]/div/div/div/ul/li[1]"));
        sleep(5000);
        if (pagination.size() > 0) {
            System.out.println("pagination exists and size=>"+pagination.size());
            int page_no=pagination.size();
            for (int i = 2; i <= pagination.size(); i++) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//page-navigation/div/div/span")));    //for
                //scroller move
                js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//page-navigation/div/div/span/a["+i+"]")));
                wait(5);      //wait
            }
        } else {
            System.out.println("no pagination");
        }

//        WebElement FirstPage= driver.findElement(By.xpath("/html/body/div[7]/div[2]/div/div/div/ul/li[1]"));
    }*/

    /*public ArrayList<String> Search(String Input) {

        driver.findElement(By.xpath("/html/body/div[2]/div/form/div/input")).sendKeys(Input);
        MongoCollection<Document> collection1 = database.getCollection("Items");
        FindIterable<Document>ItemDoc =collection1.find(eq("nam.en",Input));
        Iterator ItemSize = ItemDoc.iterator();
        int SizeOfItemsDB = Iterators.size(ItemSize);
        collection1.find(eq("nam.en",Input));
        Iterator ItemSize2 = ItemDoc.iterator();
        int SizeOfItemsDB2 = Iterators.size(ItemSize);

        MongoCollection<Document> collection2 = database.getCollection("Vendors");
        FindIterable<Document> VendorDoc = collection2.find(eq("name.en", Input));
        Iterator VendorSize = VendorDoc.iterator();
        int SizeOfVendorDB = Iterators.size(VendorSize);
        //common Word
        if (SizeOfItemsDB > 0 && SizeOfVendorDB > 0) {
            //if (SizeOfItemsDB == Count_Of_Products.size()) {
            //For check Item
            for (int i = 1; i <= Count_Of_Products.size(); i++) {
                WebElement ProductElement = driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/ul/li[" + i + "]/div[2]/h2/a"));
                String Product = ProductElement.getText();
                if (Input.contains(Product)) {
                    Find_Common_Item_Brand.add(Input);
                } else {
                    Not_Find_Common_Item_Barnd.add(Input);
                }
            }
            //For Check Vendor
            for (int j = 1; j <= Count_Of_Vendors.size(); j++) {
                WebElement VendorElement = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/ul/li[" + j + "]/div[2]/div[1]/h2/a"));
                String Vendor = VendorElement.getText();
                if (Input.contains(Vendor)) {
                    Find_Common_Item_Brand.add(Input);
                } else {
                    Not_Find_Common_Item_Barnd.add(Input);
                }
            }
            return Not_Find_Common_Item_Barnd;

        } else if (SizeOfItemsDB > 0 && SizeOfVendorDB == 0) {
            //if (SizeOfItemsDB == Count_Of_Products.size()) {
            for (int i = 1; i <= Count_Of_Products.size(); i++) {
                WebElement ProductNameElement = driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/ul/li[" + i + "]/div[2]/h2"));
                String ProductName = ProductNameElement.getText();
                //Get_Items_Website.add(ProductName);
                if (Input.equals(ProductName)) {
                    Find_Exist_Item.add(Input);
                } else {
                    Not_Found_Exist_Item.add(Input);
                }
            }
            return Not_Found_Exist_Item;
            //  } else {
            //  return "The number of appearing Items should be " + SizeOfItemsDB + " but it equals= " + Count_Of_Products.size();
            //    }
        } else if (SizeOfItemsDB == 0 && SizeOfVendorDB > 0) {
            //  if (SizeOfVendorDB == Count_Of_Vendors.size()) {
            for (int i = 1; i <= Count_Of_Vendors.size(); i++) {
                WebElement VendorNameElement = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/ul/li[" + i + "]/div[2]/div[1]/h2"));
                String VendorName = VendorNameElement.getText();
                //Get_Items_Website.add(ProductName);
                if (Input.equals(VendorName)) {
                    Find_Exist_Brand.add(Input);
                } else {
                    Not_Found_Exist_Brand.add(Input);
                }
            }
            return Not_Found_Exist_Brand;
        }
        WebElement MessageElement = driver.findElement(By.xpath("/html/body/div[5]/div/div/p"));
        String AlertMessage = MessageElement.getText();
        if(AlertMessage.equals("No result found")){
            UnExisted_Item_Brand.add(Input);
        }else {
            Find_UnExisted_Item_Brand.add(Input);
            return Find_UnExisted_Item_Brand;
        }
        return null;
    }*/
    ArrayList<String> UnExisted_Item_Brand = new ArrayList<>();
    ArrayList<String> Find_UnExisted_Item_Brand = new ArrayList<>();

    public int CountForSearchVendors() {
        int counter = 1;
        for (int i = 1; i <= counter; i++) {
            //int i = 1;
            //  List<WebElement> VendorNameElement = (List<WebElement>) driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/ul/li[" + i + "]/div[2]/div[1]/h2"));
            try {
                WebElement VendorNameElement = driver.findElement(By.xpath("/html/body/div[6]/div[1]/div/div[2]/ul/li["+ i +"]"));

                if (VendorNameElement.isDisplayed()) {
                    //  WebElement VendorNameElement2 = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/ul/li[" + i + "]/div[2]/div[1]/h2"));
                    String VendorName = VendorNameElement.getText();
                    Count_Of_Vendors.add(VendorName);
                    counter += 1;
                }
            } catch (Exception e) {
                //return Count_Of_Vendors.size();
                System.out.println(e);
            }
        }
        return Count_Of_Vendors.size();
    }
    String[][] Get_All_Products_Sheet;
    public String[][] ReadProductsFromExcel() throws IOException {
        XSSFRow row;
        XSSFCell cell;
        try {
            FileInputStream file = new FileInputStream(new File("C:\\Users\\admin\\Downloads\\Search for Items.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            // get sheet number
            int sheetCn = workbook.getNumberOfSheets();
            for (int cn = 0; cn < sheetCn; cn++) {
                // get 0th sheet data
                XSSFSheet sheet = workbook.getSheetAt(cn);
                // get number of rows from sheet
                int rows = sheet.getPhysicalNumberOfRows();
                // get number of cell from row
                int cells = sheet.getRow(cn).getPhysicalNumberOfCells();
                // value = new String[rows][cells];
                Get_All_Products_Sheet = new String[rows][cells];
                for (int r = 0; r < rows; r++) {
                    row = sheet.getRow(r); // bring row
                    if (row != null) {
                        for (int c = 0; c < cells; c++) {
                            cell = row.getCell(c);
                            if (cell != null) {
                                // Process the cell value
                                DataFormatter dataFormatter = new DataFormatter();
                                String cellValue = dataFormatter.formatCellValue(cell);
                                Get_All_Products_Sheet[r][c] = cell.toString();
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return Get_All_Products_Sheet;
    }

    public BotitWebsite.Vendor_Details Vendor_Details;
    public BotitWebsite.Featured_Categories Featured_Categories;
    public BotitWebsite.Product_Details Product_Details;
    public Common_Methods Common_Methods;
    String Vendor_Name;
    public Vendor_Details ClickOnViewBtnForVendor() throws InterruptedException {
        WebElement VendorNameElement = driver.findElement(By.xpath("/html/body/div[6]/div[1]/div/div[2]/ul/li/div[2]/div[1]/h2"));
        Vendor_Name =VendorNameElement.getText();
        driver.findElement(By.xpath("/html/body/div[6]/div[1]/div/div[2]/ul/li/div[2]/div[2]/a")).click();
        return new Vendor_Details(driver);
    }
    String Product_Name;
    public Product_Details ClickOnViewBtnForProduct() throws InterruptedException {
        WebElement ProductNameElement = driver.findElement(By.xpath("/html/body/div[7]/div[1]/div/div/div[2]/ul/li[1]/div[2]/h2"));
        Product_Name =ProductNameElement.getText();
        driver.findElement(By.xpath("/html/body/div[6]/div[1]/div/div[2]/ul/li/div[2]/div[2]/a")).click();
        return new Product_Details(driver);
    }
    /*   String StepName;
        CountForSearchVendors();
        for (int i = 1; i < Count_Of_Vendors.size(); i++) {
            String NameOfVendor = Count_Of_Vendors.get(i);
            driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/ul/li[" + i + "]/div[2]/div[2]/a")).click();
            String Title = Vendor_Details.TitleOfVendor();
            if (NameOfVendor == Title) {
                StepName = "Step1 Right Navigate with Matched Vendor";
                Common_Methods.Screenshot(StepName);
                driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[2]/ul/li[1]/a/p")).click();
            } else {
                StepName = "Step1 Not Matched Vendor";
                Common_Methods.Screenshot(StepName);
                driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[2]/ul/li[1]/a/p")).click();
            }
        }
    }*/

   /* public void ClickOnViewItemBtn() throws InterruptedException {
        String StepName;
        CountForSearchProducts();
        for (int i = 1; i < Count_Of_Products.size(); i++) {
            String NameOfProduct = Count_Of_Products.get(i);
            driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/ul/li[" + i + "]/div[3]/div[2]/a")).click();
            String TitleOfProduct = Product_Details.CheckTitleOfItem();
            if (NameOfProduct.equals(TitleOfProduct)) {
                StepName = "Step1 Right Navigate with Matched Product";
                Common_Methods.Screenshot(StepName);
                driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/ul/li[1]/a")).click();
            } else {
                StepName = "Step1 Not Matched Product";
                Common_Methods.Screenshot(StepName);
                driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/ul/li[1]/a")).click();
            }
        }
    }*/


    String[][] Get_All_Vendors_Sheet = null;
    int ArrayLengthOfVendors;

    public String[][] ReadVendorsFromExcel() throws IOException {
        XSSFRow row;
        XSSFCell cell;
        try {
            FileInputStream file = new FileInputStream(new File("C:\\Users\\admin\\Downloads\\Search for Vendors.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            // get sheet number
            int sheetCn = workbook.getNumberOfSheets();
            for (int cn = 0; cn < sheetCn; cn++) {
                // get 0th sheet data
                XSSFSheet sheet = workbook.getSheetAt(cn);
                // get number of rows from sheet
                int rows = sheet.getPhysicalNumberOfRows();
                // get number of cell from row
                int cells = sheet.getRow(cn).getPhysicalNumberOfCells();
                // value = new String[rows][cells];
                Get_All_Vendors_Sheet = new String[rows][cells];
                for (int r = 0; r < rows; r++) {
                    row = sheet.getRow(r); // bring row
                    if (row != null) {
                        for (int c = 0; c < cells; c++) {
                            cell = row.getCell(c);
                            if (cell != null) {
                                // Process the cell value
                                DataFormatter dataFormatter = new DataFormatter();
                                String cellValue = dataFormatter.formatCellValue(cell);
                                Get_All_Vendors_Sheet[r][c] = cell.toString();
                            }
                        }
                    }
                }
            }
           // ArrayLengthOfVendors = Get_All_Vendors_Sheet.length;
        } catch (Exception e) {
            System.out.println(e);
        }
        return Get_All_Vendors_Sheet;
    }
}
