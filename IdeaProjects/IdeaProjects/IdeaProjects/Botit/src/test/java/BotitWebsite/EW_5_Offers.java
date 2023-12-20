package BotitWebsite;

import BaseWebsite.BaseWebsite;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.util.ArrayList;

import org.testng.Assert;

public class EW_5_Offers extends BaseWebsite {
    WebDriver driver;
    String StepName="";

    @Test(priority = 1)
    //GC01 || SIT || Check offers section design at home page
    //GC02 || SIT || Check how user can browse more offers
    public void CheckUIDesignAtDefaultCase() {
        String DefaultCase= Offers.CheckDefaultCaseOfOffersSection();
        if(DefaultCase.contains("false")){
            Assert.assertEquals(DefaultCase,"false","The Right btn is not clickable at the default case");
            StepName="Check the design of the first page";
            Common_Methods.Screenshot(StepName);

            String SingleClickOnRightArrow = Offers.SingleClickOnRightArrow();
            Assert.assertEquals(SingleClickOnRightArrow,"true","Items are duplicated");
            StepName="Step 2 Check after Single click on right arrow";
            Common_Methods.Screenshot(StepName);

            String MultiClickOnRightArrow = Offers.MultiClickOnRightArrow();
            Assert.assertEquals(SingleClickOnRightArrow,"true","The Right button is not clickable");
            StepName="Step 3 Check After multi click on right button";
            Common_Methods.Screenshot(StepName);

        }else{
            Assert.assertEquals(DefaultCase,"Scrollable buttons are not available OR Items is Less than 3","The Right btn is not clickable at the default case");
            StepName="";
            Common_Methods.Screenshot(StepName);
        }
    }
    @Test(priority = 1)
    public void test(){
       // Offers.CheckDiscountStatusFromDB();
        Offers.CalculateDiscount();
    }
    @Test(priority = 2)
    //GC03 || SIT || Check customer journey if chose an offer from home page
    public void CheckReflectionOnProduct_DetailsPage(){
        Offers.GetAllNameOfItems();
        ArrayList<String> NotVaildItems = Offers.ClickOnViewItemButton();
        Assert.assertNull(NotVaildItems,String.valueOf(Offers.NotValid_Matched_Items));

        String Percentage = Product_Details.CheckThePercentage();
        Assert.assertEquals(Percentage,Offers.Discount_Price_Website,"The Percentage of Items is not match at the Product details Page ");
        StepName="Step 2 Check after Single click on right arrow";
        Common_Methods.Screenshot(StepName);
        int i=1;
        Offers.GetDiscountItemsFromWebsite();
        if(Percentage.contains(Offers.Discount_Price_Website.get(i))){
            Assert.assertEquals(Percentage,Offers.Discount_Price_Website,"The Percentage of Items is not match at the Product details Page ");
            StepName="Step 2 Check after Single click on right arrow";
            Common_Methods.Screenshot(StepName);
        }
    }
    //@Test(priority = 3)
    //GC04 || SIT || Check design and components of offers page
    //GC05 || SIT || Check customer journey when clicking on "view item" for a product in offers page
    public void CheckClickingOnSeeMoreBtn(){
        Offers_Page RightNavigate = Offers.ClickOnSeeMoreButton();
        String TitleOfPage=Offers_Page.GetTitleOfPage();
        if(TitleOfPage == "Offers"){
            Assert.assertEquals(TitleOfPage,"Offers","The Navigate is not correct");
            StepName="Step 1 Click on see more btn";
            Common_Methods.Screenshot(StepName);
        }else {
            StepName="Step 1 Wrong navigate after clicking on the see more btn";
            Common_Methods.Screenshot(StepName);
        }
        ArrayList<String> MatchedItems= Offers_Page.ClickOnViewItemButton();
        Assert.assertEquals(MatchedItems,"", String.valueOf(Offers_Page.Not_Valid_Matched_Items));
    }
}
