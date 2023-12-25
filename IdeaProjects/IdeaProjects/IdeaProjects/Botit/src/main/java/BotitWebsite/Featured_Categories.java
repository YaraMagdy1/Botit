package BotitWebsite;

import jdk.internal.icu.impl.CharacterIteratorWrapper;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Featured_Categories {

    private By Icon = By.xpath("//*[@id=\"swiper-wrapper-6050eb48510004729\"]/div[1]/a");
    private By Getname = By.xpath("//*[@id=\"swiper-wrapper-6050eb48510004729\"]/div[1]/a/h2");
    ArrayList<String> Get_Categories = new ArrayList<String>();
    ArrayList<String> Get_Categories_Count = new ArrayList<String>();
    int ArrayLength;
    String[][] GetAllSheet = null;

    String Catgeory = "";
    Boolean result = true;
    static WebDriver driver;
    private CharacterIteratorWrapper cellIterator;

    public Featured_Categories(WebDriver driver) {
        this.driver = driver;
    }

    public String MultipleRightbuttonClick() {
        String result = "";
        String RightButtonStatus = driver.findElement(By.xpath("/html/body/div[6]/div/div[1]/div[2]/div[2]")).getAttribute("aria-disabled");
        try {
            while (RightButtonStatus != "true") {
                Thread.sleep(1000);
                driver.findElement(By.xpath("/html/body/div[6]/div/div[1]/div[2]/div[2]")).click();
                RightButtonStatus = driver.findElement(By.xpath("/html/body/div[6]/div/div[1]/div[2]/div[2]")).getAttribute("aria-disabled");
            }
        } catch (Exception e) {
            String leftarrowEnabled = driver.findElement(By.xpath("/html/body/div[6]/div/div[1]/div[2]/div[2]")).getAttribute("aria-disabled").toString();
            if (leftarrowEnabled == "false") {
                result = "we reached to the last category with gray displayed";
                return result;
            }
        }
        result = "Categories not more than 10";
        return result;
    }

    String Count = "";
    String catgeories = "";

    public String SingleRightbuttonClick() {
        String result = "";
        int i = 11;
        String RightButtonStatus = driver.findElement(By.xpath("/html/body/div[6]/div/div[1]/div[2]/div[2]")).getAttribute("aria-disabled");
        try {
            while (RightButtonStatus != "true") {

                driver.findElement(By.xpath("/html/body/div[6]/div/div[1]/div[2]/div[2]")).click();
                Thread.sleep(1500);
                WebElement category = driver.findElement(By.xpath("/html/body/div[6]/div/div[2]/div/div/div[" + i + "]/a/h2"));
                catgeories = category.getText();
                Get_Categories.add(catgeories);
                Common_Methods.Screenshot("Step 2&3 check name and icons of all catges");
                WebElement CategoryCount = driver.findElement(By.xpath("/html/body/div[6]/div/div[2]/div/div/div[" + i + "]/a/p"));
                Count = CategoryCount.getText();
                Get_Categories_Count.add(Count);
                i++;

                RightButtonStatus = driver.findElement(By.xpath("/html/body/div[6]/div/div[1]/div[2]/div[2]")).getAttribute("aria-disabled");
            }

        } catch (Exception e) {
            String leftarrowEnabled = driver.findElement(By.xpath("/html/body/div[6]/div/div[1]/div[2]/div[2]")).getAttribute("aria-disabled").toString();
            if (leftarrowEnabled == "false") {
                result = "we reached to the last category with gray displayed";
                return result;
            }
        }
        result = "Categories not more than 10";
        return result;
    }


    public String MultipleLeftbuttonClick() {
        String result = "";
        String LeftArrowStatus = "";
        try {
            LeftArrowStatus = driver.findElement(By.xpath("/html/body/div[6]/div/div[1]/div[2]/div[1]")).getAttribute("aria-disabled").toString();
            while (LeftArrowStatus != "true") {
                Thread.sleep(1000);
                driver.findElement(By.xpath("/html/body/div[6]/div/div[1]/div[2]/div[1]")).click();

            }

        } catch (Exception e) {
            //  LeftArrowStatus = driver.findElement(By.xpath("/html/body/div[6]/div/div[1]/div[2]/div[1]")).getAttribute("aria-disabled").toString();
            if (LeftArrowStatus == "true") {
                result = "we reached to the First category with gray displayed";
                //Take screenshot

                return result;
            }
        }
        result = "Categories not more than 10";
        return result;
    }

    public Common_Methods Common_Methods;

    public String getallfeaturesCategories() {

        try {
            int i = 1;
            String Rightarrowstatus = driver.findElement(By.xpath("/html/body/div[6]/div/div[1]/div[2]/div[2]")).getAttribute("aria-disabled");
            while (Rightarrowstatus != "true") {
                if (i == 11) {
                    SingleRightbuttonClick();
                    break;
                } else {
                    // WebElement category = driver.findElement(By.xpath("//*[@id='swiper-wrapper-02c7de3b567cd5f9']/div["+i+"]/a/h2)"));
                    WebElement category = driver.findElement(By.xpath("/html/body/div[6]/div/div[2]/div/div/div[" + i + "]/a/h2"));
                    catgeories = category.getText();
                    WebElement CategoryCount = driver.findElement(By.xpath("/html/body/div[6]/div/div[2]/div/div/div[" + i + "]/a/p"));
                    Count = CategoryCount.getText();
                    Get_Categories.add(catgeories);
                    Get_Categories_Count.add(Count);
                    i++;
                }
            }

        } catch (Exception e) {
            // System.out.println("All categories that are available added in the list");

        }

        return null;
    }

    int ArraySizeOfCateg;
    String[][] GetAllCategsAndCount;

    public int GetcountOfItem_Categories() {
       // getallfeaturesCategories();
        GetAllCategsAndCount = new String[Get_Categories.size()][2];
        for (int r = 0; r < Get_Categories.size(); r++) {//Fot Rows (Name of categs)
            String CountNumber = "";
            String CountName = Get_Categories_Count.get(r);
            String categoryName = Get_Categories.get(r);
            for (int c = 0; c <= 1; c++) { //For cell(Count of items)
                if (c == 1) {
                    GetAllCategsAndCount[r][c] = CountName;
                } else {
                    GetAllCategsAndCount[r][c] = categoryName;
                    continue;
                }
            }
        }
        return GetAllCategsAndCount.length;
    }

    public String GetTheTitleOfCategorySection() {
        WebElement titleCategElement = driver.findElement(By.xpath("/html/body/div[6]/div/div[1]/div[1]/h2"));
        String titleCateg = titleCategElement.getText();
        return titleCateg;
    }

    public void readCategoriesFromExcel() throws IOException {

        XSSFRow row;
        XSSFCell cell;

        try {
            //  FileInputStream file = new FileInputStream(new File("C:\\Users\\MO4\\Desktop\\BotitWebSite.xlsx"));
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
    }
    String[][] Categ_With_No_Items;
    String[][] NotMatched;
    String Category_Website ;
    ArrayList<String> Categ_Website =new ArrayList<>();

    public String[][] ValidateCategories_Items() throws IOException {
        CheckDisplayingOfCategories();

        String[][] IsMatch = new String[GetAllCategsAndCount.length][2];
        NotMatched = new String[GetAllCategsAndCount.length][2];
        int x = 0;
        int y = 0;
        int w = 0;
        int q = 0;
        int f=0;
        try {
            //while (Found_Categories != null) {
                for (int i = 0; i < Found_Categories.length; i++) {
                    //for (int j=0 ;j<2 ;j++){
                    for (int a = 0; a < GetAllSheet.length; a++) {
                        //int j=0;
                        if(Found_Categories[i][0] != null) {
                            if (Found_Categories[i][0].equals(GetAllSheet[a][0])) {
                                if (Found_Categories[i][1].equals(GetAllSheet[a][1])) {
                                    IsMatch[y][0] = Found_Categories[i][0].toString();
                                    IsMatch[y][1] = Found_Categories[i][1].toString();
                                    y++;
                                    break;

                                } else {
                                    NotMatched[x][0] = Found_Categories[i][0];
                                    NotMatched[x][1] = Found_Categories[i][1];
                                    x++;
                                    break;
                                }
                            } else if (f == (GetAllSheet.length) - 1) {
                                Catgeory = GetAllSheet[i][0];
                                Not_Found_Categories.add(Catgeory);
                                continue;
                            }
                        }
                    }
                }
            }
         catch (Exception e) {
            System.out.println(e);
        } return NotMatched;
    }
    ArrayList<String> Missing_Categories =new ArrayList<>();
    ArrayList<String> GetAllCategWebsite = new ArrayList<>();
    ArrayList<String> GetAllCategSheet = new ArrayList<>();
    public String CheckDisplayingOfCatgeories() throws IOException {
        GetcountOfItem_Categories();
        readCategoriesFromExcel();
        try {
            if(GetAllCategsAndCount.length != GetAllSheet.length) {
                for (int i = 0; i < GetAllCategsAndCount.length; i++) {
                    GetAllCategWebsite.add(GetAllCategsAndCount[i][0]);
                }
                for (int j = 0; j < GetAllSheet.length; j++) {
                    GetAllCategSheet.add(GetAllSheet[j][0]);
                }
                for (int x = 0; x < GetAllCategWebsite.size(); x++) {
                    for (int y = 0; y < GetAllCategSheet.size(); y++) {
                        if (GetAllCategWebsite.get(x).contains(GetAllCategSheet.get(y))) {
                        } else {
                            String MissingCateg = GetAllCategWebsite.get(x);
                            Missing_Categories.add(MissingCateg);

                        }
                    }
                }
            }else {
                return "true";
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return String.valueOf(Missing_Categories);
    }

    ArrayList<String> Not_Found_Categories = new ArrayList<String>();
    String[][] Found_Categories;
    ArrayList<String> SizeOfFoundCategory = new ArrayList<String>();

    public ArrayList<String> CheckDisplayingOfCategories(){
        int j=0;
        int X=0;
        Found_Categories=new String[GetAllSheet.length][2];
        for (int i = 0; i < GetAllSheet.length; i++) {
            for (X=0 ; X < Get_Categories.size(); X++) {
                //readCategories.size()-1
                if (Get_Categories.get(X).contains(GetAllSheet[i][0])) {
                   WebElement CountVendorElement =driver.findElement(By.xpath("/html/body/div[6]/div/div[2]/div/div/div["+ (X + 1) +"]/a/p"));
                    String CountVendor=CountVendorElement.getText();
                    Found_Categories[j][0] = GetAllSheet[i][0];
                    Found_Categories[j][1] =CountVendor;
                    j++;

                    break;
                } else if (X == (Get_Categories.size() - 1)) {
                    Catgeory = GetAllSheet[i][0];
                    Not_Found_Categories.add(Catgeory);
                    continue;
                }
            }
        }
        return Not_Found_Categories;
    }
    public String AllFoundCateg(){
        CheckDisplayingOfCategories();
        Found_Categories=new String[SizeOfFoundCategory.size()][2];
        for (int i=0;i<SizeOfFoundCategory.size() ; i++){
            WebElement CountVendorElement =driver.findElement(By.xpath("/html/body/div[6]/div/div[2]/div/div/div["+ (i + 1) +"]/a/p"));
            String CountVendor=CountVendorElement.getText();
            Found_Categories[i][0] = SizeOfFoundCategory.get(i);
            Found_Categories[i][1] =CountVendor;
        }
        return null;
    }

    public String CheckButtonAvailabilityDefault() {
        String RightArrowStatus = "";
        String LeftArrowStatus = "";
        int CategoriesCount = Get_Categories.size();
        if (Get_Categories.size() > 10) {
            MultipleLeftbuttonClick();
            RightArrowStatus = driver.findElement(By.xpath("/html/body/div[6]/div/div[1]/div[2]/div[1]")).getAttribute("aria-disabled");
            LeftArrowStatus = driver.findElement(By.xpath("/html/body/div[6]/div/div[1]/div[2]/div[2]")).getAttribute("aria-disabled");

            if (RightArrowStatus.contains("false") && LeftArrowStatus.contains("true")) {
                return LeftArrowStatus;
            } else {
                return RightArrowStatus;
            }
        } else {
            return "Scrollable buttons are not available ";
        }
    }

    public String ClickOnRightArrow() {
        // getallfeaturesCategories();
        int CategoriesCount = Get_Categories.size();

        if (Get_Categories.size() > 10) {
            // MultipleLeftbuttonClick();
            WebElement lastcategory = driver.findElement(By.xpath("/html/body/div[6]/div/div[2]/div/div/div[10]/a/h2"));
            String lastCategory = lastcategory.getText();
            driver.findElement(By.xpath("/html/body/div[6]/div/div[1]/div[2]/div[2]")).click();
            try {
                WebElement newlastcategory = driver.findElement(By.xpath("/html/body/div[6]/div/div[2]/div/div/div[11]/a/h2"));
                String newlastCategory = newlastcategory.getText();
                String LeftArrowAvailability = driver.findElement(By.xpath("/html/body/div[6]/div/div[1]/div[2]/div[1]")).getAttribute("aria-disabled").toString();
                String RightArrowAvailability = driver.findElement(By.xpath("/html/body/div[6]/div/div[1]/div[2]/div[2]")).getAttribute("aria-disabled").toString();
                if (LeftArrowAvailability.contains("false") && RightArrowAvailability.contains("false")) {
                    if (lastCategory != newlastCategory) {
                        String result = "Scroll working fine";
                        return result;
                    } else {
                        return "Scroll not working";
                    }
                } else {
                    String result = "Scroll not working";
                    return result;
                }

            } catch (Exception e) {
                String result = "Scroll not working";
                return result;
            }
        } else {
            String result = "Categories are less than or equal to 10";
            return result;
        }
    }

    public String ClickOnLeftArrow() {
        // getallfeaturesCategories();
        // int CategoriesCount = Get_Categories.size();
        MultipleLeftbuttonClick();
        try {
            driver.findElement(By.xpath("/html/body/div[6]/div/div[1]/div[2]/div[1]")).click();
            String Result = "Button is clickable";
            return Result;
        } catch (Exception e) {
            String Result = "Button is not clickable";
            return Result;
        }
    }
    // public BotitWebsite.Shop_Sub_Category Shop_Sub_Category;
    String CategName;
    public Shop_Sub_Category ClickOnCategory() throws InterruptedException {
        for (int i = 1; i <= 2; i++) {
            CategName= driver.findElement(By.xpath("/html/body/div[6]/div/div[2]/div/div/div[" + i + "]/a")).toString();

            driver.findElement(By.xpath("/html/body/div[6]/div/div[2]/div/div/div[" + i + "]/a")).click();
            Thread.sleep(1500);
            driver.findElement(By.xpath("/html/body/div[4]/div/div[1]/div[2]/ul/li[1]/a/p")).click();
        }
        return new Shop_Sub_Category(driver);
    }
}



