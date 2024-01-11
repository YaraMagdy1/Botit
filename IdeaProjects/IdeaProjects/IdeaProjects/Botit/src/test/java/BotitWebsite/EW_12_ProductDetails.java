package BotitWebsite;

import BaseWebsite.BaseWebsite;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class EW_12_ProductDetails extends BaseWebsite {
    WebDriver driver;
    SoftAssert SoftAssert = new SoftAssert();

    @Test(priority = 1)
    //GC01 || SIT || Check app download from Product details page
    //GC02 || SIT || Check Product details page design and information
    //GC03 || SIT || Check navigation to Product details page

    //----------------From The Home Page----------------
    public void CheckRightNavigate() throws InterruptedException, IOException {
        //-------------------From Featured category section----------------
        Common_Methods.scrolling("/html/body/div[6]/div/div[1]/div[1]/h2");
        Shop_Sub_Category ClickOnCateg = Featured_Categories.ClickOnCategory();
        Thread.sleep(3500);
        String CategNameFromPath = Shop_Sub_Category.CategNameFromPath();
        SoftAssert.assertEquals(CategNameFromPath, Featured_Categories.CategName, "The Path at the sub categ page is not matched" + CategNameFromPath);
        Common_Methods.Screenshot("EW12 Step1 for GC01 & Step1 for GC03");

        Common_Methods.scrolling("/html/body/div[5]/div/div/div[2]/div[3]/ul/li[1]");
        Product_Details ClickOnViewItemBtn = Shop_Sub_Category.ClickOnViewItemBtn();
        Thread.sleep(3000);
        String ItemName = Product_Details.CheckTitleOfItem();
        SoftAssert.assertEquals(ItemName, Product_Details.TitleOfItem, "The name of item at the product details doesn't match from (Featured Category Section)");
        Common_Methods.Screenshot("EW12 Step 1 to 8 For GC02 & step 2 for GC03");

        Product_Details.CheckTheFooter();
        Common_Methods.Screenshot("EW12 Step 9 for GC02");

        Download_Page GetURL = Product_Details.ClickOnDownloadAppBtn();
        Thread.sleep(3500);
        String ActualURL = Download_Page.GetURL();
        SoftAssert.assertEquals(ActualURL, "https://apps.apple.com/us/app/botit/id1490081820", "The Actual URL to Download App is not Correct from featured category section" + ActualURL);
        Common_Methods.Screenshot("EW 12 GC01 Step2");

        //-----------------From Popular product section--------------------
        Product_Details.GoBackToHomePage();
        Common_Methods.scrolling("/html/body/div[7]/div/div[1]/h2");
        Product_Details ClickOnBtn = Popular_Product.ClickOnViewItemButton();
        Thread.sleep(1800);
        String Title2 = Product_Details.CheckTitleOfItem();
        SoftAssert.assertEquals(Title2, Product_Details.TitleOfItem, "The title of item doesn't match from the (Popular Product section)" + Title2);
        Common_Methods.Screenshot("EW12 step 3 for GC03");

        Download_Page Click2 = Product_Details.ClickOnDownloadAppBtn();
        String URL2 = Download_Page.GetURL();
        SoftAssert.assertEquals(URL2, "https://apps.apple.com/us/app/botit/id1490081820", "The Actual URL to Download App is not Correct from popular product section" + URL2);

        //-----------------From recently added brands section---------------------
        Product_Details.GoBackToHomePage();
        Common_Methods.scrolling("/html/body/div[8]/div/div[1]/div[1]/h2");
        Vendor_Details Click = Recently_Added_Brands.ClickOnViewProductButton();

        String VendorTitle = Vendor_Details.TitleOfVendor();
        SoftAssert.assertEquals(VendorTitle, Vendor_Details.VendorTitle, "The Vendor title doesn't matched");
        Common_Methods.Screenshot("EW12 step4 for GC03");
        Thread.sleep(3000);

        Common_Methods.scrolling("/html/body/div[5]/div/div/div[2]/div[3]/ul/li[1]");
        Product_Details ClickBtn = Vendor_Details.ClickOnViewItemBtn();
        Thread.sleep(3000);
        Common_Methods.Screenshot("EW12 Step 5 for GC03");

        Download_Page DownloadBtn = Product_Details.ClickOnDownloadAppBtn();
        String URL3 = Download_Page.GetURL();
        SoftAssert.assertEquals(URL3, "https://apps.apple.com/us/app/botit/id1490081820", "The Actual URL to Download App is not Correct from Recently added brands section" + URL3);

        //---------------From Offers section-------------------
        Product_Details.GoBackToHomePage();
        Common_Methods.scrolling("/html/body/div[9]/div/div[1]/div[1]/h2");
        Product_Details Title = Offers.ClickOnViewBtn();
        String Title3 = Product_Details.CheckTitleOfItem();
        SoftAssert.assertEquals(Title3, Product_Details.TitleOfItem, "The title doesn't match from (the Offer section)" + Title3);

        Product_Details.ClickOnDownloadAppBtn();
        String URL4 = Download_Page.GetURL();
        SoftAssert.assertEquals(URL4, "https://apps.apple.com/us/app/botit/id1490081820", "The Actual URL to Download App is not Correct from (Offer section)" + URL4);

    }
}
