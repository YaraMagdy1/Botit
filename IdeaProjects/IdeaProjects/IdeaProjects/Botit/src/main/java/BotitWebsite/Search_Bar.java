/*package BotitWebsite;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.commons.math3.analysis.solvers.BrentSolver;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.bson.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class Search_Bar {
    WebDriver driver;
    MongoClient mongoClient = MongoClients.create("mongodb+srv://transmission_dev:K1IPfykYMq6FAUv6@botit-dev.jwtve.mongodb.net/botitdev?retryWrites=true&w=majority");
    MongoDatabase database = mongoClient.getDatabase("botitdev");
    ArrayList<String> Find_Searched_Vendor = new ArrayList<>();
    ArrayList<String> Not_Find_Searched_Product = new ArrayList<>();
    ArrayList<String> Find_Searched_Product = new ArrayList<>();
    ArrayList<String> Not_Find_Searched_Vendor = new ArrayList<>();
    ArrayList<String> Count_Of_Products=new ArrayList<>();
    ArrayList<String> Count_Of_Vendors=new ArrayList<>();
    public Search_Bar(WebDriver driver) {
        this.driver = driver;
    }
    public void  CountForSearchProducts() {
        int counter = 1;
        for (int i = 1; i <= counter; i++) {
            try {
                WebElement ProductNameElement = driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/ul/li["+ i +"]/div[2]/h2/a"));
                if (ProductNameElement.isDisplayed()) {
                    String ProductName = ProductNameElement.getText();
                    Count_Of_Products.add(ProductName);
                    counter += 1;
                }
            } catch (Exception e) {
                System.out.println("Finished looping for all Products");
            }
        }
    }
    public String SearchForExistProduct(String Product_Name) {
        CountForSearchProducts();
        driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[1]/form/input")).sendKeys(Product_Name);
        MongoCollection<Document> collection1 = database.getCollection("Items");
        Document doc1 = collection1.find(eq("name.en", Product_Name)).first();
        if (doc1 != null) {
            //driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[1]/form/button")).click();
            WebElement CheckTitleOfProductElement = driver.findElement(By.xpath("/html/body/div[4]/div/div[1]/h2"));
            String CheckTitleOfProduct = CheckTitleOfProductElement.getText();
            if (CheckTitleOfProduct == "Products") {
                for (int i = 1; i < Count_Of_Products.size(); i++) {
                    WebElement ProductNameElement = driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/ul/li[" + i + "]/div[2]/h2"));
                    String ProductName = ProductNameElement.getText();
                    if (Product_Name.equals(ProductName)) {
                        Find_Searched_Product.add(ProductName);
                    } else {
                        Not_Find_Searched_Product.add(ProductName);
                    }
                }
            } else {
                return "No result appear";
            }
        } else {
            return "Item not found in DB";
        }
        return String.valueOf(Not_Find_Searched_Product);
    }
    int i=1;
    public void CountForSearchVendors() {
       int counter = 1;
        for (int i = 1; i <=counter; i++) {
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
            }
            catch (Exception e){
                System.out.println("Finished looping for all vendors");
            }
        }
    }
    /*public void readVendorsFromExcel() throws IOException {
        XSSFRow row;
        XSSFCell cell;
        try {
            //  FileInputStream file = new FileInputStream(new File("C:\\Users\\MO4\\Desktop\\BotitWebSite.xlsx"));
            FileInputStream file = new FileInputStream(new File("C:\\Users\\admin\\Downloads\\BotitWebSite.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            // get sheet number

            int sheetCn = workbook.getNumberOfSheets();
            for (int cn=0 ; cn < sheetCn; cn++) {
                // get 0th sheet data
                XSSFSheet sheet = workbook.getSheetAt(cn);
                // get number of rows from sheet
                int rows = sheet.getPhysicalNumberOfRows();
                // get number of cell from row
                int cells = sheet.getRow(cn).getPhysicalNumberOfCells();
                // value = new String[rows][cells];
                GetAllSheet = new String[rows][cells];
                for (int r = 0; r < rows; r++) {
                    row = sheet.getRow(r); // bring row
                    if (row != null) {
                        for (int c = 0; c < cells; c++) {
                            cell = row.getCell(c);

                            if (cell != null) {
                                // Process the cell value
                                DataFormatter dataFormatter = new DataFormatter();
                                String cellValue = dataFormatter.formatCellValue(cell);
                                GetAllSheet[r][c] = cell.toString();
                            }

                        }
                    }
                }

            }
            ArrayLength = GetAllSheet.length;
        } catch (Exception e) {
            System.out.println(e);
        }
    }*/

    public String SearchForExistVendor (String Vendor_Name) {
        CountForSearchVendors();
        MongoCollection<Document> collection2 = database.getCollection("Vendors");
        Document doc2 = collection2.find(eq("name.en", Vendor_Name)).first();
        if (doc2 != null) {
            //driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[1]/form/button")).click();
            WebElement CheckTitleElement = driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div/h2"));
            String CheckTitle = CheckTitleElement.getText();
            if (CheckTitle.equals("Brands")) {
                for (int i = 1; i <= Count_Of_Vendors.size(); i++) {
                    WebElement VendorNameElement = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/ul/li[" + i + "]/div[2]/div[1]/h2"));
                    String VendorName = VendorNameElement.getText();
                    if (Vendor_Name.equals(VendorName)) {
                        Find_Searched_Vendor.add(VendorName);
                    } else {
                        Not_Find_Searched_Vendor.add(VendorName);
                    }
                }
            } else {
                return "No Vendors returned in search result but the vendors is exsited";
            }
            return "Exist Vendor Not found in DB";
        }
        return String.valueOf(Not_Find_Searched_Vendor);
    }
    ArrayList<String> Matched_Common_Product = new ArrayList<>();
    ArrayList<String> Matched_Common_Vendor = new ArrayList<>();
    ArrayList<String> Not_Matched_Common_Product = new ArrayList<>();
    ArrayList<String> Not_Matched_Common_Vendor = new ArrayList<>();

    public String SearchForCommonName(String Common_Name) {
        CountForSearchVendors();
        CountForSearchProducts();
        driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[1]/form/input")).sendKeys(Common_Name);
        MongoCollection<Document> collection1 = database.getCollection("Items");
        Document doc1 = collection1.find(eq("name.en", Common_Name)).first();
        if (doc1 != null) {
            driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[1]/form/button")).click();
            WebElement CheckTitleOfProductElement = driver.findElement(By.xpath("/html/body/div[4]/div/div[1]/h2"));
            String CheckTitleOfProduct = CheckTitleOfProductElement.getText();
            if (CheckTitleOfProduct == "Products") {
                for (int i = 1; i <= Count_Of_Products.size(); i++) {
                    WebElement ProductNameElement = driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/ul/li[" + i + "]/div[2]/h2"));
                    String ProductName = ProductNameElement.getText();
                    if (Common_Name.contains(ProductName)) {
                        Matched_Common_Product.add(ProductName);
                    } else {
                        Not_Matched_Common_Product.add(ProductName);
                    }
                }
            }else {
                return "there is no products appeared";
            }
        }else {
            return "Product Not found in DB";
        }
        MongoCollection<Document> collection2 = database.getCollection("Vendors");
        Document doc2 = collection1.find(eq("name.en", Common_Name)).first();
        if (doc2 != null) {
            for (int j = 1; i<=Count_Of_Vendors.size(); i++) {
                WebElement CheckTitleOfProductElement = driver.findElement(By.xpath("/html/body/div[4]/div/div[1]/h2"));
                String CheckTitleOfVendor = CheckTitleOfProductElement.getText();
                if (CheckTitleOfVendor.equals("Brands")) {
                    WebElement VendorNameElement = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/ul/li[" + i + "]/div[2]/div[1]/h2"));
                    String VendorName = VendorNameElement.getText();
                    if (Common_Name.contains(VendorName)) {
                        Matched_Common_Vendor.add(VendorName);
                    } else {
                        Not_Matched_Common_Vendor.add(VendorName);
                    }
                } else {
                    return "No vendors appears";
                }
            }
        }else {
            return "Vendor not found in DB";
        }
        return Common_Name;
    }
    ArrayList<String> Not_Exsit_Products = new ArrayList<>();
    ArrayList<String> Find_Exsit_Products = new ArrayList<>();
    ArrayList<String> Not_Exsit_Vendors = new ArrayList<>();
    ArrayList<String> Find_Exsit_Vendors = new ArrayList<>();

    public String SearchForNotExsitProduct(String NotExsitProductName){
        CountForSearchProducts();
        CountForSearchVendors();
        driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[1]/form/input")).sendKeys(NotExsitProductName);
        MongoCollection<Document> collection1 = database.getCollection("Items");
        Document doc1 = collection1.find(eq("name.en", NotExsitProductName)).first();
        if (doc1.equals(null)){
            for (int i=1 ; i<Count_Of_Products.size();i++){
            WebElement ValidationMessageElement = driver.findElement(By.xpath("/html/body/div[5]/div/div/p"));
            String ValidationMessage = ValidationMessageElement.getText();
            if(ValidationMessage.equals("No result found")){
                Not_Exsit_Products.add(NotExsitProductName);
            }else{
                Find_Exsit_Products.add(NotExsitProductName);
            }
            }
        }else {
            return "Not exist product is found in DB";
        }
            return String.valueOf(Find_Exsit_Products);
    }
    public String SearchForNotExsitVendors(String NotExsitVendorName){
        CountForSearchVendors();
        driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[1]/form/input")).sendKeys(NotExsitVendorName);
        MongoCollection<Document> collection1 = database.getCollection("Items");
        Document doc1 = collection1.find(eq("name.en", NotExsitVendorName)).first();
        if (doc1.equals(null)){
            for (int i=1 ; i<Count_Of_Vendors.size();i++){
                WebElement ValidationMessageElement = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/ul/li[11]/div[2]/div[1]/h2/a"));
                String ValidationMessage = ValidationMessageElement.getText();
                if(ValidationMessage.equals("No result found")){
                    Not_Exsit_Vendors.add(NotExsitVendorName);
                }else{
                    Find_Exsit_Vendors.add(NotExsitVendorName);
                }
            }
        }else {
            return "Not exist product is found in DB";
        }
        return String.valueOf(Find_Exsit_Vendors);
    }

}*/