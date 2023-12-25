package BotitWebsite;

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

import static com.mongodb.client.model.Filters.eq;
import static java.lang.Thread.sleep;

public class Search_Bar  {


    WebDriver driver;

    ArrayList<String> Count_Of_Products = new ArrayList<>();
    ArrayList<String> Count_Of_Vendors = new ArrayList<>();

    public Search_Bar(WebDriver driver) {
        this.driver = driver;
    }

    public void CountForSearchProducts() {
        int counter = 1;
        for (int i = 1; i <= counter; i++) {
            try {
                WebElement ProductNameElement = driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/ul/li[" + i + "]/div[2]/h2/a"));
                if (ProductNameElement.isDisplayed()) {
                    String ProductName = ProductNameElement.getText();
                    Count_Of_Products.add(ProductName);
                    counter += 1;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public String SearchForEmptyValue() {

        driver.findElement(By.xpath("/html/body/div[2]/div/form/div/label[2]")).click();
        String Message=driver.findElement(By.xpath("/html/body/div[5]/div/div/p")).toString();
        return Message;
    }

    public String ClickOnExitButton() throws IOException {
        String Product_Name = "hi";
        WebElement EnterValue = driver.findElement(By.xpath("/html/body/div[2]/div/form/div/input"));
        EnterValue.sendKeys(Product_Name);
        driver.findElement(By.xpath("/html/body/div[2]/div/form/div/label[1]/span")).click();
        if (EnterValue.equals(null)){
            return "The Search box is empty after clicking on Exit btn";
        } else {
            return "The Search box is not empty after clicking on Exit btn";
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
    public ArrayList<String> Search(String Input) {
        CountForSearchProducts();

        driver.findElement(By.xpath("/html/body/div[2]/div/form/div/input")).sendKeys(Input);
        String BrandTitle = driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div/h2")).toString();
        String ProductTitle = driver.findElement(By.xpath("/html/body/div[4]/div/div[1]/h2")).toString();
        //-----------Common search----------
        if(BrandTitle != null && ProductTitle != null) {
            for (int i = 1; i <= Count_Of_Products.size(); i++) {
                WebElement ProductElement = driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/ul/li[" + i + "]/div[2]/h2/a"));
                String Product = ProductElement.getText();
                if (Input.contains(Product)) {
                    Find_Common_Item_Brand.add(Input);
                } else {
                    Not_Find_Common_Item_Barnd.add(Input);
                }
            }
            for (int j = 1; j <= Count_Of_Vendors.size(); j++) {
                WebElement VendorElement = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/ul/li[" + j + "]/div[2]/div[1]/h2/a"));
                String Vendor = VendorElement.getText();
                if (Input.contains(Vendor)) {
                    Find_Common_Item_Brand.add(Input);
                } else {
                    Not_Find_Common_Item_Barnd.add(Input);
                }
            }
        }//------------Search For Item-------------
        else if(ProductTitle != null && BrandTitle.equals(null)){
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
        }//----------Search For Vendor-------------
        else if(ProductTitle.equals(null) && BrandTitle != null){
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
        }//---------Search For Not Found Item and Vendor--------------
        else if (ProductTitle.equals(null) && BrandTitle.equals(null)) {
            WebElement MessageElement = driver.findElement(By.xpath("/html/body/div[5]/div/div/p"));
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
    public void CountForSearchVendors() {
        int counter = 1;
        for (int i = 1; i <= counter; i++) {
            //int i = 1;
            //  List<WebElement> VendorNameElement = (List<WebElement>) driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/ul/li[" + i + "]/div[2]/div[1]/h2"));
            try {
                WebElement VendorNameElement = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/ul/li[" + i + "]/div[2]/div[1]/h2"));

                if (VendorNameElement.isDisplayed()) {
                    //  WebElement VendorNameElement2 = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/ul/li[" + i + "]/div[2]/div[1]/h2"));
                    String VendorName = VendorNameElement.getText();
                    Count_Of_Vendors.add(VendorName);
                    counter += 1;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    String[][] Get_All_Products_Sheet;
    public String[][] ReadProductsFromExcel() throws IOException {
        XSSFRow row;
        XSSFCell cell;
        try {
            FileInputStream file = new FileInputStream(new File("C:\\Users\\admin\\Downloads\\FF6C6F00.xlsx"));
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
    public void ClickOnViewBtn() throws InterruptedException {
        String StepName;
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
    }

    public void ClickOnViewItemBtn() {
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
    }

    String[][] Get_All_Vendors_Sheet = null;
    int ArrayLengthOfVendors;

    public String[][] ReadVendorsFromExcel() throws IOException {
        XSSFRow row;
        XSSFCell cell;
        try {
            FileInputStream file = new FileInputStream(new File("C:\\Users\\admin\\Downloads\\BotitWebSite.xlsx"));
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
            ArrayLengthOfVendors = Get_All_Vendors_Sheet.length;
        } catch (Exception e) {
            System.out.println(e);
        }
        return Get_All_Vendors_Sheet;
    }
}
