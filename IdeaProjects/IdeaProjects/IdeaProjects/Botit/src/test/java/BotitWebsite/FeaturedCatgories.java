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

public class FeaturedCatgories extends BaseWebsite {
    WebDriver driver;
    String StepName = "";

    @Test(priority = 1)

    //GC01 || SIT || Check design for Featured Categories Section
    //GC04 || SIT || Check when a category has no items
    public void CheckUIDesignAtDefaultCase() throws IOException {
        Featured_Categories.getallfeaturesCategories();

        Featured_Categories.GetAllCategoriesAndCount();

        Featured_Categories.ValidateCategories();
        boolean result = Featured_Categories.ValidateCategories();
        Assert.assertEquals(result, "true", "Some of Categories didn't display");
        StepName = "Step3 and 4 for GC01 & GC04 (Check the Name of categories and The Count of Items";
        Featured_Categories.Screenshot(StepName);

        String TestDefaultCase = Featured_Categories.CheckButtonAvailabilityDefault();
        Assert.assertTrue(TestDefaultCase
                .contains(("Scrollable buttons are not available")
                ), "Scrollable buttons are not available");
        StepName = "Step4 (check No arrows buttons";
        Featured_Categories.Screenshot(StepName);
    }


    @Test(priority = 2)
    //GC02 || SIT || Check Scroll buttons of Featured Categories section
    public void checkScrollingFunctionality() throws InterruptedException {
        String TestDefaultCase = Featured_Categories.CheckButtonAvailabilityDefault();
        Assert.assertEquals(TestDefaultCase, "true", "2 buttons are exist with Left Arrow is Gray and Right arrow is Red");
        StepName = "Step1(check default case";
        Featured_Categories.Screenshot(StepName);

        String CheckLeftArrowFunctionality = Featured_Categories.ClickOnLeftArrow();
        Assert.assertEquals(CheckLeftArrowFunctionality, "Button is not clickable", "Left arrow is not clickable in gray state");
        StepName = "Step2(check left is not clickable ";
        Featured_Categories.Screenshot(StepName);

        String CheckScrollingFunctionality = Featured_Categories.ClickOnRightArrow();
        Assert.assertEquals(CheckScrollingFunctionality, "Scroll working fine");
        StepName = "Step3( Scroll functionality working fine ";
        Featured_Categories.Screenshot(StepName);
    }

    // GC05 || SIT || Check the data returned in the featured categories section
    // GC06 || SIT || Check reflection on home when adding new category
    @Test(priority = 3)
    public void Categories() throws IOException {
        Featured_Categories.getallfeaturesCategories();
        //Exist screenshot
        Featured_Categories.readCategoriesFromExcel();
        boolean result = Featured_Categories.ValidateCategories();
        Assert.assertEquals(result, "true");
    }

}


