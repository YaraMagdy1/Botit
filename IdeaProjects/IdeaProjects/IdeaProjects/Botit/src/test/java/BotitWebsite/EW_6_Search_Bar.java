package BotitWebsite;

import BaseWebsite.BaseWebsite;
import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.io.IOException;
import java.util.ArrayList;

import static org.testng.Assert.*;
public class EW_6_Search_Bar extends BaseWebsite {
    WebDriver driver;
    String ExistProductName;
    String StepName;
    SoftAssert softAssert = new SoftAssert();

    @Test(priority = 1)
    //GC01 || SIT || Check position and design for Search Bar
    public void CheckTheDesignOfSearch_Bar() throws InterruptedException {
        //  Common_Methods.scrolling("/html/body/div[3]/div/a");
        // Search_Bar.CountForSearchProducts();
        Search_Bar.Search("Asy");

        StepName = "Design of search bar";
        Common_Methods.Screenshot(StepName);
    }

    @Test(priority = 2)
    //GC02 || SIT || User search existing vendor
    //GC03 || SIT || User search non existing vendor
    //GC04 || SIT || Check if search is case sensitive or not
    //GC07 || SIT || Checking design of search results page
    //GC09 || SIT || Check searching with part of vendor name in home page
    //GC10 || SIT || Check search with common word between different vendors at home page
    //GC16 || SIT || Check design of no result page when searching with invalid values
    public void SearchForVendor() throws IOException {

        String[][] SearchForVendor = Search_Bar.ReadVendorsFromExcel();

        SoftAssert SoftAssert = new SoftAssert();
        for (int i = 0; i < SearchForVendor.length; i++) {
            String VendorName = SearchForVendor[i][0];
            ArrayList<String> Output1 = Search_Bar.Search(VendorName);
            SoftAssert.assertEquals(Output1, "", "The Vendor is not found in search result");
            StepName = "Search for vendor";
            Common_Methods.Screenshot(StepName);
          //  System.out.println("The Count for searching about Item:" +VendorName +"is:" + Count_Vendor);
            Search_Bar.GoBackToHomePage();
        }
        SoftAssert.assertAll();
    }

    @Test(priority = 3)
    //GC04 || SIT || Check if search is case sensitive or not
    //GC11 || SIT || Check searching valid item name in search results page
    //GC16 || SIT || Check design of no result page when searching with invalid values
    public void SearchForItem() throws IOException {

        String[][] ReadItemsFormExcelSheet = Search_Bar.ReadProductsFromExcel();
        //int Count_Product = Search_Bar.CountForSearchProducts();
        for (int i = 0; i < ReadItemsFormExcelSheet.length; i++) {
            String PassValue1 = ReadItemsFormExcelSheet[i][0];
            ArrayList<String> Output2 = Search_Bar.Search(PassValue1);
            SoftAssert SoftAssert = new SoftAssert();
            SoftAssert.assertEquals(Output2, "", "The Vendor or Item is not found in search result" + Output2);
            StepName = "Search for items";
            Common_Methods.Screenshot(StepName);
          //  System.out.println("The Count for searching about Item:" +PassValue1 +"is:" + Count_Product);
            Search_Bar.GoBackToHomePage();
        }
        softAssert.assertAll();
    }
    //@Test(priority =5 )
   /* public void SearchForVendor() throws IOException {

        String[][] ReadVendorsFormExcelSheet = Search_Bar.ReadVendorsFromExcel();
        for (int i = 0; i < ReadVendorsFormExcelSheet.length ; i++) {
            String PassValue2 = ReadVendorsFormExcelSheet[i][0];
            ArrayList<String> Output3 = Search_Bar.Search(PassValue2);
            SoftAssert SoftAssert = new SoftAssert();
            SoftAssert.assertEquals(Output3,"","The Vendor is not found in search result"+PassValue2);
            Common_Methods.Screenshot("Search for Vendor");
            Search_Bar.ClickOnExitButton();
        }
        softAssert.assertAll();
    }*/


    @Test(priority = 4)
    //GC06 || SIT || Check if close button is shown and working when user wants to delete data from search bar
    //GC18 || SIT || Check if close button is shown and working in search result page
    public void ClickOnThe_X_btn() throws IOException, InterruptedException {
        Search_Bar.Search("yara");

        Thread.sleep(2000);
        String ClickOnExitBtn = Search_Bar.ClickOnExitButton();
        SoftAssert SoftAssert = new SoftAssert();
        SoftAssert.assertEquals(ClickOnExitBtn, "true");
        StepName = "EW-6 Check the design after clicking on the exist btn";
        Common_Methods.Screenshot(StepName);
        SoftAssert.assertAll();
    }

    @Test(priority = 5)
    //GC08 || SIT || Check searching with empty in search box at home page
    //GC13 || SIT || Check searching with empty text in search results page
    public void SearchForEmptyValue() {
        SoftAssert SoftAssert = new SoftAssert();
        String AlertMessage = Search_Bar.SearchForEmptyValue();
        SoftAssert.assertEquals(AlertMessage, "No result found", "after search for empty value, the alert message doesn't correct" + AlertMessage);
        Common_Methods.Screenshot("EW-6 Check searching without entering value");
        Search_Bar.GoBackToHomePage();
    }

    @Test(priority = 6)
    //GC14 || SIT || Check "View Products" button in search results page
    public void ClickOnViewItemBtnForProduct() throws InterruptedException {
        SoftAssert SoftAssert = new SoftAssert();
        Search_Bar.Search("Asy");
        Product_Details TitleOfProduct = Search_Bar.ClickOnViewBtnForProduct();
        String ProductTitle = Product_Details.CheckTitleOfItem();
        if (ProductTitle.equals(Search_Bar.Product_Name)) {
            Common_Methods.Screenshot("Right navigate after clicking on view btn for Product");
       Search_Bar.GoBackToHomePage();
        } else {
            SoftAssert.assertEquals(ProductTitle, Search_Bar.Product_Name, "The Product name is not matched throw the navigation" + TitleOfProduct);
            Common_Methods.Screenshot("Wrong navigate after clicking on the view btn for Product");
            Search_Bar.GoBackToHomePage();
        }
    }

    //@Test(priority = 7)
    //GC15 || SIT || Check "View Item" button
    public void ClickOnViewItemBtnForVendor() throws InterruptedException {
        SoftAssert SoftAssert = new SoftAssert();
        Search_Bar.Search("Vendor 1");
        Vendor_Details TitleOfVendor = Search_Bar.ClickOnViewBtnForVendor();
        String VendorTitle = Vendor_Details.TitleOfVendor();
        if (VendorTitle.equals(Search_Bar.Vendor_Name)) {
            Common_Methods.Screenshot("Right navigate after clicking on view btn for Vendor");
        } else {
            SoftAssert.assertEquals(VendorTitle, Search_Bar.Vendor_Name, "The vendor name is not matched throw the navigation" + TitleOfVendor);
            Common_Methods.Screenshot("Wrong navigate after clicking on the view btn for Vendor");
        }
        SoftAssert.assertAll();
    }
}


