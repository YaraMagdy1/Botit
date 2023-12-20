package BotitWebsite;
import BaseWebsite.BaseWebsite;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.io.IOException;

public class EW_1_FeaturedCatgories extends BaseWebsite {
    WebDriver driver;
    String StepName = "";

    @Test(priority = 1)

    //GC01 || SIT || Check design for Featured Categories Section
    //GC04 || SIT || Check when a category has no items
    //GC05 || SIT || Check the data returned in the featured categories section
    public void CheckUIDesignAtDefaultCase() throws IOException {
        String titleOfCatge= Featured_Categories.GetTheTitleOfCategorySection();
        Assert.assertEquals(titleOfCatge,"Featured Categories","The title of category section os not correct");
        Common_Methods.Screenshot("EW1.Step 1&2&3 Check the title of categ section");

        Featured_Categories.getallfeaturesCategories();

        Featured_Categories.GetcountOfItem_Categories();

        String[][] result = Featured_Categories.ValidateCategories_Items();
        Assert.assertNotNull(result, "Some of Categories didn't display"+result.toString());//Check
        StepName = "EW1.Step3,4 for GC01 & Step1 for GC04 Step2,4 for GC05 (Check the Name of categories and The Count of Items";
        Common_Methods.Screenshot(StepName);

        String TestDefaultCase = Featured_Categories.CheckButtonAvailabilityDefault();
        Assert.assertTrue(TestDefaultCase
                .contains(("Scrollable buttons are not available")
                ), "Scrollable buttons are not available");
        StepName = "EW1.Step5 (check No arrows buttons";
        Common_Methods.Screenshot(StepName);
    }
    @Test(priority = 2)
    //GC02 || SIT || Check Scroll buttons of Featured Categories section
    public void checkScrollingFunctionality() throws InterruptedException {
        String TestDefaultCase = Featured_Categories.CheckButtonAvailabilityDefault();
        Assert.assertEquals(TestDefaultCase, "true", "2 buttons are exist with Right Arrow is Gray and Left arrow is Red");
        StepName = "EW1.Step1(check default case";
        Common_Methods.Screenshot(StepName);

        String CheckLeftArrowFunctionality = Featured_Categories.ClickOnLeftArrow();
        Assert.assertEquals(CheckLeftArrowFunctionality, "Button is not clickable", "Left arrow is clickable in Red state");
        StepName = "EW1.Step2(check left is not clickable ";
        Common_Methods.Screenshot(StepName);

        String CheckScrollingFunctionality = Featured_Categories.ClickOnRightArrow();
        Assert.assertEquals(CheckScrollingFunctionality, "Scroll working fine");
        StepName = "EW1.Step3( Scroll functionality working fine ";
        Common_Methods.Screenshot(StepName);
    }
    @Test(priority = 3)
    // GC03 || SIT || Check user redirection when choosing any  category
    public void ClickOnCategory() {
        Shop_Sub_Category ClickOnCateg = Featured_Categories.ClickOnCategory();
        String ActualURL= Shop_Sub_Category.GetPageURL();
        if (ActualURL.contains(Featured_Categories.CategName)){
            Common_Methods.Screenshot("EW1.Step1 Right navigate with right URL");
        }else {
            Common_Methods.Screenshot("EW1.Step1 Wrong navigate with incorrect URL");
        }
    }
    @Test(priority = 4)

    // GC06 || SIT || Check reflection on home when adding new category
    public void Categories() throws IOException {
        Featured_Categories.getallfeaturesCategories();
        //Exist screenshot
        Featured_Categories.readCategoriesFromExcel();
        String [][] result = Featured_Categories.ValidateCategories_Items();
        Assert.assertEquals(result, "true");
    }


}


