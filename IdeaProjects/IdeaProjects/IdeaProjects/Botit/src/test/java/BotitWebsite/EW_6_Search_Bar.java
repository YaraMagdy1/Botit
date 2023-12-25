package BotitWebsite;

import BaseWebsite.BaseWebsite;
import dev.failsafe.internal.util.Assert;
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
    public void CheckTheDesignOfSearch_Bar(){
        Search_Bar.CountForSearchProducts();
        StepName="Design of search bar";
        Common_Methods.Screenshot(StepName);
    }
    @Test
    public void Allure(){
        assertTrue(false);
        System.out.println("This is a fail assertion");
    }

    @Test(priority = 2)
    //GC02 || SIT || User search existing vendor
    //GC03 || SIT || User search non existing vendor
    //GC07 || SIT || Checking design of search results page
    //GC09 || SIT || Check searching with part of vendor name in home page
    //GC10 || SIT || Check search with common word between different vendors at home page
    //GC16 || SIT || Check design of no result page when searching with invalid values
    public void SearchForVendor() throws IOException {
        Search_Bar.CountForSearchVendors();
        String[][] SearchForVendor = Search_Bar.ReadVendorsFromExcel();
        SoftAssert SoftAssert = new SoftAssert();
        for (int i = 0; i < SearchForVendor.length; i++) {
            String VendorName = SearchForVendor[i][0];
            ArrayList<String> Output1 = Search_Bar.Search(VendorName);
            SoftAssert.assertEquals(Output1.get(i), "", "The Vendor is not found in search result");
            StepName = "Search for vendor";
            Common_Methods.Screenshot(StepName);
        }
    }
    @Test(priority = 5)
    //GC11 || SIT || Check searching valid item name in search results page
    //GC16 || SIT || Check design of no result page when searching with invalid values
    public void SearchForItem() throws IOException {

        String[][] ReadItemsFormExcelSheet = Search_Bar.ReadProductsFromExcel();
            for (int i = 0; i < ReadItemsFormExcelSheet.length ; i++) {
                    String PassValue = ReadItemsFormExcelSheet[i][0];
                    ArrayList<String> Output2 = Search_Bar.Search(PassValue);
                    SoftAssert SoftAssert = new SoftAssert();
                    SoftAssert.assertEquals(Output2,"","The Vendor or Item is not found in search result");
                    StepName="Search for items";
                    Common_Methods.Screenshot(StepName);
                }
            softAssert.assertAll();
            }

    //@Test(priority = 3)
    //GC04 || SIT || Check if search is case sensitive or not
    public void CheckSeneitive(){

    }
    @Test(priority = 4)
    //GC06 || SIT || Check if close button is shown and working when user wants to delete data from search bar
    //GC18 || SIT || Check if close button is shown and working in search result page
    public void CheckTheXbtn() throws IOException {

        String ClickOnExitBtn = Search_Bar.ClickOnExitButton();
        SoftAssert SoftAssert=new SoftAssert();
        SoftAssert.assertEquals(ClickOnExitBtn,"The Search box is not empty after clicking on Exit btn");
        StepName="Check the design after clicking on the exist btn";
        Common_Methods.Screenshot(StepName);
        SoftAssert.assertAll();
    }
    @Test(priority = 5)
    //GC08 || SIT || Check searching with empty in search box at home page
    //GC13 || SIT || Check searching with empty text in search results page
    public void SearchForEmptyValue(){
        Search_Bar.SearchForEmptyValue(); //Marina?
    }
    @Test (priority = 6)
    //GC14 || SIT || Check "View Products" button in search results page
    public void ClickOnViewProductBtn() throws InterruptedException {
        Search_Bar.ClickOnViewBtn();
    }
    @Test (priority = 7)
    //GC15 || SIT || Check "View Item" button
    public void ClickOnViewItemBtn(){
        Search_Bar.ClickOnViewItemBtn();
    }

}


