package BotitWebsite;

import BaseWebsite.BaseWebsite;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class EW_4_RecentlyAddedBrands extends BaseWebsite {
    String StepName="";
    WebDriver driver;

    @Test(priority = 1)
    //GC01 || SIT || Check design for Recently Added Vendors Section
    public void DesignOfRecentlyAddedVendorsPage() throws InterruptedException {
        Common_Methods.scrolling("/html/body/div[8]/div/div[1]/div[1]/h2");
        SoftAssert  SoftAssert =new SoftAssert();

       // String Tiltle = Recently_Added_Brands.GetTiltleOfRecentlySection();
        //SoftAssert.assertEquals(Tiltle,"Recently added brands","The title of recently added brands is not correct"+Tiltle);
        //Common_Methods.Screenshot("EW4 Step1 Check the title of Recently added brands section");

        String Default= Recently_Added_Brands.DefualtCaseOfArrows();
        Thread.sleep(1000);
        StepName = "Design Of Recent added brands page 1 ";
        Common_Methods.Screenshot(StepName);
        if( Default.contains("false") || Default.contains("true")){
            SoftAssert.assertEquals(Default, "true", "Default is displaying incorrect");

        } else {
            SoftAssert.assertEquals(Default,"vendors are less than or equal 5","Default is displaying correct");
            Recently_Added_Brands.MultipleRightbuttonClick();
            StepName = "Design Of Recent added brands page 2";
            Common_Methods.Screenshot(StepName);
        }
        StepName = "Design Of Recent added brands page 2 ";
        Common_Methods.Screenshot(StepName);
        SoftAssert.assertAll();
    }
    @Test(priority = 2)
    //GC02 || SIT || Check Scroll buttons of Recently Added Vendors section
    public void CheckScrollingButtons () throws InterruptedException {
        SoftAssert  SoftAssert =new SoftAssert();

        Recently_Added_Brands.MultipleLeftbuttonClick();//Back to default case

        String LeftButton =Recently_Added_Brands.StatusOfLeftButton();
        SoftAssert.assertEquals(LeftButton,"","Left Button is clickable at the default case");
        StepName="Click on the Left arrow at the default case";
        Common_Methods.Screenshot(StepName);

        String RightButton = Recently_Added_Brands.CheckFunctionalityOfRightButton();
        Thread.sleep(1000);
        SoftAssert.assertEquals(RightButton,"true","The name of vendor 5 equal the name of vendor 6");
        StepName="Step 2 ( Single Click on the right arrow)";
        Common_Methods.Screenshot(StepName);
        SoftAssert.assertAll();
    }

    @Test(priority = 3)
    //GC03 || SIT || Check user redirection when clicking on "View Products" button
    public void Check_Right_Navigate_After_Click_On_View_Product_Button() throws InterruptedException {
        SoftAssert  SoftAssert =new SoftAssert();

        Recently_Added_Brands.GetTheFirstVendorName();

        Vendor_Details RightNavigate = Recently_Added_Brands.ClickOnViewProductButton();
        String VendorName =RightNavigate.TitleOfVendor();
        if(VendorName == Recently_Added_Brands.FirstVendorName){
            SoftAssert.assertEquals(VendorName,Recently_Added_Brands.FirstVendorName,"The Vendor title at the vendor page not match the Vendor name");
            StepName="Step1 (After click on the view product btn)";
            Common_Methods.Screenshot(StepName);
        }else {
            SoftAssert.assertEquals(VendorName,Recently_Added_Brands.FirstVendorName,"The Vendor title at the vendor page not match the Vendor name");
            StepName="Step1 (After click on the view product btn)";
            Common_Methods.Screenshot(StepName);
        }
        SoftAssert.assertAll();
    }
    //@Test(priority = 4)
    //GC04 || SIT || Check user redirection when clicking on "See more" button
    public void Check_Right_Navigate_After_Click_On_See_More(){
        SoftAssert  SoftAssert =new SoftAssert();

        Featured_Brands RightNavigate = Recently_Added_Brands.ClickOnSeeMoreButton();
        String VendorName = RightNavigate.GetTitle();
        if(VendorName == Recently_Added_Brands.FirstVendorName){
            SoftAssert.assertEquals(VendorName,Recently_Added_Brands.FirstVendorName,"The vendors is not match at the featured brands page ");
            StepName="Step1 (After click on the see more btn)";
            Common_Methods.Screenshot(StepName);
        }else{
            SoftAssert.assertEquals(VendorName,Recently_Added_Brands.FirstVendorName,"The vendors is not match at the featured brands page ");
            StepName="Step1 (After click on the see more btn)";
            Common_Methods.Screenshot(StepName);
        }
        SoftAssert.assertAll();
    }
    @Test(priority = 5)
    //GC06 || SIT || Check the data returned in Recently Added vendors section
    public void CheckTheVendor_DetailsHaseItems() throws InterruptedException {
        SoftAssert  SoftAssert =new SoftAssert();

        Recently_Added_Brands.ClickOnViewProductButton();
        String CheckItems = Vendor_Details.RelatedItemsAtVendorPage();
        if(CheckItems != null) {
            SoftAssert.assertNotNull(CheckItems, "The vendor details page is empty");
            StepName = "Check the vendor details page is not empty";
            Common_Methods.Screenshot(StepName);
        }else {
            StepName = "Check the vendor details page is empty";
            Common_Methods.Screenshot(StepName);
        }

        Recently_Added_Brands.ClickOnSeeMoreButton();
        String CheckVendors = Featured_Brands.RelatedItemsAtFeaturedPage();
        if(CheckVendors != null) {

            SoftAssert.assertNotNull(CheckVendors, "The Featured brands is empty");
            StepName="Check the featured page is not empty";
            Common_Methods.Screenshot(StepName);
        }else{
            StepName="Check the featured page is empty";
            Common_Methods.Screenshot(StepName);
        }
        SoftAssert.assertAll();
    }
}
