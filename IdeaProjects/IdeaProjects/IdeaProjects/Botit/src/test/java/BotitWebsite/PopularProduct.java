package BotitWebsite;
import BaseWebsite.BaseWebsite;
import com.google.errorprone.annotations.Var;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import java.io.IOException;

public class PopularProduct extends BaseWebsite {
    WebDriver driver;
    String StepName = "";

    @Test(priority = 1)
    //GC01 || SIT || Check design for popular products section
    public void CheckDesgin() {
        String result = Popular_Product.GetAllPopularProducts();
        Assert.assertEquals(result, null, "the total number of displaying items is not equal 10");
        StepName = "Check the design of Popular Product";
        Featured_Categories.Screenshot(StepName);
    }

    @Test(priority = 2)
    //GC02 || SIT || Check view item button functionality for any popular product
    public void CheckTheNavigation() {
        Product_Details RightNavigate = Popular_Product.ClickOnViewItemButton();
        String RelatedItem= RightNavigate.CheckTitleOfItem();
        if(RelatedItem == Popular_Product.NameOfFirstItem){
            Assert.assertEquals(RelatedItem,Popular_Product.NameOfFirstItem,"The title of item at the product Details page is not matched");
            StepName="The item name is matched at the product details page";
            Featured_Categories.Screenshot(StepName);
        }else {
            StepName="The item name is not matched at the product details page";
            Featured_Categories.Screenshot(StepName);
        }
    }

    @Test(priority = 3)
    //GC03 || SIT || Check if popular product are shown randomly
    public void CheckItemsAfterRefreshing() {
        String TotalNumberOfProducts = Popular_Product.RefreshingPage();
        Assert.assertEquals(TotalNumberOfProducts, "true", "The items isn't change after refreshing page");
        StepName = "Step1 (Items Not change after refreshing page)";
        Featured_Categories.Screenshot(StepName);

        String Items_Not_Found_DB = Popular_Product.VerifyItemsInDB();
        Assert.assertEquals(Items_Not_Found_DB, "true", "Item is not founded in DB");
        StepName="Check Items in DB after refreshing page";
        Featured_Categories.Screenshot(StepName);

        String Unsupported_And_OutOfStock_Items = Popular_Product.InstockItems();
        Assert.assertEquals(Unsupported_And_OutOfStock_Items, "true","items are not available and out of stock");
        StepName="Check in stock and availability items after refreshing page";
        Featured_Categories.Screenshot(StepName);

        Product_Details ProductDetails= Popular_Product.ClickOnViewItemButton();
        String MatchedItem= ProductDetails.CheckTitleOfItem();
        if(MatchedItem == Product_Details.TitleOfItem){
            Assert.assertEquals(MatchedItem,Product_Details.TitleOfItem,"The Items is not matched after refreshing page");
            StepName = "Right navigate after refreshing page";
            Featured_Categories.Screenshot(StepName);
        }else {
            StepName = "Wrong navigate after refreshing page";
            Featured_Categories.Screenshot(StepName);
        }

        String CompareDiscountPrice = Popular_Product.ComperBetweenDiscountPrice();
        Assert.assertEquals(CompareDiscountPrice,"true","The discount price doesn't match with DB");
        StepName="Comparing between discounted items from DB and website";
        Featured_Categories.Screenshot(StepName);
    }

    @Test(priority = 4)
    //GC04 || SIT || Check if the popular product matches its vendor correctly
    public void CheckRelatedVendors() {
        boolean ComparingVendorsName = Popular_Product.CompareVendorsName();
        Assert.assertEquals(ComparingVendorsName, true, "The vendors not related with item");
        StepName = "Step 1 (Check related Vendors)";
        Featured_Categories.Screenshot(StepName);
    }
}