package BotitWebsite;
import BaseWebsite.BaseWebsite;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class EW_3_PopularProduct extends BaseWebsite {
    WebDriver driver;
    String StepName = "";

    @Test(priority = 1)
    //GC01 || SIT || Check design for popular products section
    public void CheckDesgin() throws InterruptedException {
        Common_Methods.scrolling("/html/body/div[7]/div/div[1]/h2");
        SoftAssert SoftAssert =new SoftAssert();

        String CheckTilte = Popular_Product.GetTitleOfPopularProductSection();
        SoftAssert.assertEquals(CheckTilte,"Popular Products","the Tilte of popular product is not correct"+CheckTilte);
        Common_Methods.Screenshot("EW3 Step1 to 6 Check the title name");
        SoftAssert.assertAll();
       /* String result = Popular_Product.GetAllPopularProducts();
        Assert.assertEquals(result, null, "the total number of displaying items is not equal 10");
        StepName = "Check the design of Popular Product";
        // Common_Methods.Screenshot(StepName);
    */
    }


    @Test(priority = 2)
    //GC02 || SIT || Check view item button functionality for any popular product
    public void CheckTheNavigation() {
        SoftAssert SoftAssert = new SoftAssert();
        Product_Details RightNavigate = Popular_Product.ClickOnViewItemButton();
        String RelatedItem= RightNavigate.CheckTitleOfItem();
        if(RelatedItem == Popular_Product.NameOfFirstItem){
            SoftAssert.assertEquals(RelatedItem,Popular_Product.NameOfFirstItem,"The title of item at the product Details page is not matched");
            StepName="EW3 step1 the navigate after clicking on view btn is correct";
            Common_Methods.Screenshot(StepName);
        }else {
            StepName="EW3 step1 the navigate after clicking on view btn is not correct";
            Common_Methods.Screenshot(StepName);
        }
        SoftAssert.assertAll();
    }

    @Test(priority = 3)
    //GC03 || SIT || Check if popular product are shown randomly
    public void CheckItemsAfterRefreshing() {
        SoftAssert SoftAssert=new SoftAssert();
        String TotalNumberOfProducts = Popular_Product.RefreshingPage();
        SoftAssert.assertEquals(TotalNumberOfProducts, "true", "The items isn't change after refreshing page");
        StepName = "EW3 Step1 (Items Not change after refreshing page)";
        Common_Methods.Screenshot(StepName);

        String Items_Not_Found_DB = Popular_Product.VerifyItemsInDB();
        SoftAssert.assertEquals(Items_Not_Found_DB, "true", "Item is not founded in DB");
        StepName="Check Items in DB after refreshing page";
        Common_Methods.Screenshot(StepName);

        String Unsupported_And_OutOfStock_Items = Popular_Product.InstockItems();
        SoftAssert.assertEquals(Unsupported_And_OutOfStock_Items, "true","items are not available and out of stock");
        StepName="Check in stock and availability items after refreshing page";
        Common_Methods.Screenshot(StepName);

        Product_Details ProductDetails= Popular_Product.ClickOnViewItemButton();
        String MatchedItem= ProductDetails.CheckTitleOfItem();
        if(MatchedItem == Product_Details.TitleOfItem){
            SoftAssert.assertEquals(MatchedItem,Product_Details.TitleOfItem,"The Items is not matched after refreshing page");
            StepName = "Right navigate after refreshing page";
            Common_Methods.Screenshot(StepName);
        }else {
            StepName = "Wrong navigate after refreshing page";
            Common_Methods.Screenshot(StepName);
        }

        String CompareDiscountPrice = Popular_Product.ComperBetweenDiscountPrice();
        SoftAssert.assertEquals(CompareDiscountPrice,"true","The discount price doesn't match with DB");
        StepName="Comparing between discounted items from DB and website";
        Common_Methods.Screenshot(StepName);
        SoftAssert.assertAll();
    }

    @Test(priority = 4)
    //GC04 || SIT || Check if the popular product matches its vendor correctly
    public void CheckRelatedVendors() {
        SoftAssert SoftAssert=new SoftAssert();
        boolean ComparingVendorsName = Popular_Product.CompareVendorsName();
        SoftAssert.assertEquals(ComparingVendorsName, true, "The vendors not related with item");
        StepName = "Step 1 (Check related Vendors)";
        Common_Methods.Screenshot(StepName);
        SoftAssert.assertAll();
    }

}