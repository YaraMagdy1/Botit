package BotitWebsite;

import BaseWebsite.BaseWebsite;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class EW_5_Offers extends BaseWebsite {
    WebDriver driver;
    String StepName="";

    @Test(priority = 1)
    //GC01 || SIT || Check offers section design at home page
    //GC04 || SIT || Check design and components of offers page
    public void CheckUIDesignAtDefaultCase() {
        SoftAssert SoftAssert = new SoftAssert();

        String CheckTitle = Offers.TitleOfOfferSection();
        SoftAssert.assertEquals(CheckTitle, "Offers", "The title of offer section doesn't correct" + CheckTitle);
        Common_Methods.Screenshot("EW5 Step1 to 5 for GC01 and Step 2 for GC04 Check desgin Of offer section");
        SoftAssert.assertAll();
    }
    @Test(priority = 2)
    //GC02 || SIT || Check how user can browse more offers
    public void CheckScrollingArrows(){
    SoftAssert SoftAssert = new SoftAssert();

    String DefaultCase= Offers.CheckDefaultCaseOfOffersSection();
    SoftAssert.assertEquals(DefaultCase,"false","The Right btn is not clickable at the default case");
    StepName="EW5 step1 for GC02 Check the design of the first page";
    Common_Methods.Screenshot(StepName);

    String SingleClickOnRightArrow = Offers.SingleClickOnRightArrow();
    SoftAssert.assertEquals(SingleClickOnRightArrow,"true","Items are duplicated or the Arrows status not correct at offer section");
    StepName="EW5 Step 2 for GC02 Check after Single click on right arrow";
    Common_Methods.Screenshot(StepName);

    String MultiClickOnRightArrow = Offers.MultiClickOnRightArrow();
    SoftAssert.assertEquals(SingleClickOnRightArrow,"true","The Right button is not clickable");
    Common_Methods.Screenshot("EW5 Step 2 for GC02 Check Multi clicking on right arrow");

    SoftAssert.assertAll();
    }

    @Test(priority = 3)
    //GC03 || SIT || Check customer journey if chose an offer from home page
    public void CheckReflectionOnProduct_DetailsPage() throws InterruptedException {
        SoftAssert SoftAssert=new SoftAssert();

        Offers.MultiClickOnLeftArrow();
        Product_Details ClickOnViewBtn = Offers.ClickOnViewBtn();
        String TitleOfItem = Product_Details.CheckTitleOfItem();
        String DisocuntPrice =Product_Details.GetDiscountPrice();
        if(TitleOfItem.equals(Offers.ItemName) && DisocuntPrice.equals(Offers.DiscountItme)) {
          Common_Methods.Screenshot("EW5 Step1 for GC03 Check Navigate after clicking on view btn");
        }else {
            SoftAssert.assertEquals(TitleOfItem, Offers.ItemName, "The Item name is not matched after clicking on View btn" + TitleOfItem);
            SoftAssert.assertEquals(DisocuntPrice, Offers.DiscountItme, "The Discount price is not matched after clicking on View btn" + TitleOfItem);
        }
       // String Percentage = Product_Details.CheckThePercentage();
        //SoftAssert.assertEquals(Percentage,Offers.Discount_Price_Website,"The Percentage of Items is not match at the Product details Page ");
        //StepName="EW5 Step 2 For GC03 Check the discount product ";
        //Common_Methods.Screenshot(StepName);
       // int i=1;
        boolean Discount = Offers.CompareDiscountedItems();
        SoftAssert.assertTrue(Discount,"The Comparing of discount price from DB and Website doesn't match");
        Common_Methods.Screenshot("Check the discount price form website and DB");

        /*Offers.GetDiscountItemsFromWebsite();
        if(Percentage.contains(Offers.Discount_Price_Website.get(i))){
            SoftAssert.assertEquals(Percentage,Offers.Discount_Price_Website,"The Percentage of Items is not match at the Product details Page ");
            StepName="Step 2 Check after Single click on right arrow";
            Common_Methods.Screenshot(StepName);
        }*/
        SoftAssert.assertAll();
    }
    //@Test(priority = 4)
    //GC04 || SIT || Check design and components of offers page
    //GC05 || SIT || Check customer journey when clicking on "view item" for a product in offers page
    //GC06 || || SIT || Check customer journey when clicking on "see more" of product description in offers page
    public void CheckClickingOnSeeMoreBtn(){
        SoftAssert SoftAssert=new SoftAssert();
       /* Offers_Page RightNavigate = Offers.ClickOnSeeMoreButton();
        String TitleOfPage=Offers_Page.GetTitleOfPage();
        if(TitleOfPage == "Offers"){
            SoftAssert.assertEquals(TitleOfPage,"Offers","The Navigate is not correct");
            StepName="Step 1 Click on see more btn";
            Common_Methods.Screenshot(StepName);
        }else {
            StepName="Step 1 Wrong navigate after clicking on the see more btn";
            Common_Methods.Screenshot(StepName);
        }
        ArrayList<String> MatchedItems= Offers_Page.ClickOnViewItemButton();
        SoftAssert.assertEquals(MatchedItems,"", String.valueOf(Offers_Page.Not_Valid_Matched_Items));
    */    SoftAssert.assertAll();
    }
}
