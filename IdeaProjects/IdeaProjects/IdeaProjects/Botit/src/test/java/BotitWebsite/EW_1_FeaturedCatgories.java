package BotitWebsite;
import BaseWebsite.BaseWebsite;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertAll;

public class EW_1_FeaturedCatgories extends BaseWebsite {
    WebDriver driver;
    String StepName = "";
    String TestDefaultCase = "";
    @Test(priority = 1)

    //GC01 || SIT || Check design for Featured Categories Section
    //GC04 || SIT || Check when a category has no items
    //GC05 || SIT || Check the data returned in the featured categories section
    // GC06 || SIT || Check reflection on home when adding new category
    public void CheckUIDesignAtDefaultCase() throws IOException, InterruptedException {
        Common_Methods.scrolling("/html/body/div[6]/div/div[1]/div[1]/h2");

        SoftAssert SoftAssert= new SoftAssert();

        String titleOfCatge= Featured_Categories.GetTheTitleOfCategorySection();
        SoftAssert.assertEquals(titleOfCatge,"Featured Categories","The title of category section os not correct");
        Common_Methods.Screenshot("EW1.Step 1&2&3 Check the title of categ section");

        Featured_Categories.getallfeaturesCategories();
        Featured_Categories.GetcountOfItem_Categories();
        Featured_Categories.readCategoriesFromExcel();

        ArrayList<String> CategNotFound = Featured_Categories.CheckDisplayingOfCategories();
        SoftAssert.assertNull(CategNotFound,"The count of displaying categories is not correct"+CategNotFound);

       String [][]NotMatched= Featured_Categories.ValidateCategories_Items();
        String[][] Categ1 = new String[Featured_Categories.NotMatched.length][2];
        for (int k=0;k<NotMatched.length;k++){
            if ( NotMatched[k][0] != null && !NotMatched[k][1].trim().isEmpty()) {
                Categ1[k][0] = NotMatched[k][0];
                Categ1[k][1] = NotMatched[k][1];
            }
        }

        String result = Arrays.deepToString(Featured_Categories.NotMatched);
        SoftAssert.assertEquals(result,"[null, null]","Categs not Matched "+result );//Check
        StepName = "EW1.Step3,4 for GC01 & Step1 for GC04 Step2,4 for GC05 (Check the Name of categories and The Count of Items";
        Common_Methods.Screenshot(StepName);

        TestDefaultCase = Featured_Categories.CheckButtonAvailabilityDefault();
        SoftAssert.assertEquals(TestDefaultCase,"true", "Scrollable buttons are not available OR displayed wrong");
        StepName = "EW1.Step5 (check No arrows buttons";
        Common_Methods.Screenshot(StepName);

        SoftAssert.assertAll("The Failed Assertion");

    }
   @Test(priority = 2)
    //GC02 || SIT || Check Scroll buttons of Featured Categories section
    public void checkScrollingFunctionality() throws InterruptedException {
        SoftAssert SoftAssert =new SoftAssert();
        if(TestDefaultCase.contains("Scrollable buttons are not available")) {
            String TestDefaultCase = Featured_Categories.CheckButtonAvailabilityDefault();
            SoftAssert.assertEquals(TestDefaultCase, "true", "2 buttons are exist with Right Arrow is Gray and Left arrow is Red");
            StepName = "EW1.Step1(check default case";
            Common_Methods.Screenshot(StepName);

            String CheckLeftArrowFunctionality = Featured_Categories.ClickOnLeftArrow();
            SoftAssert.assertEquals(CheckLeftArrowFunctionality, "Button is not clickable", "Left arrow is clickable in Red state");
            StepName = "EW1.Step2(check left is not clickable ";
            Common_Methods.Screenshot(StepName);

            String CheckScrollingFunctionality = Featured_Categories.ClickOnRightArrow();
            SoftAssert.assertEquals(CheckScrollingFunctionality, "Scroll working fine");
            StepName = "EW1.Step3( Scroll functionality working fine ";
            Common_Methods.Screenshot(StepName);
        }
        else
        {
            System.out.println(TestDefaultCase);
        }
       SoftAssert.assertAll("The Failed Assertion");
    }
    @Test(priority = 3)
    // GC03 || SIT || Check user redirection when choosing any  category
    public void ClickOnCategory() throws InterruptedException {
        SoftAssert SoftAssert = new SoftAssert();
        Shop_Sub_Category ClickOnCateg = Featured_Categories.ClickOnCategory();
        Thread.sleep(1500);
        String ActualURL= Shop_Sub_Category.GetPageURL();
        Common_Methods.scrolling("/html/body/div[6]/div/div[1]/div[1]/h2");

        if (ActualURL.contains(Featured_Categories.CategName)){
            Thread.sleep(1500);
            Common_Methods.Screenshot("EW1.Step1 Right navigate with right URL");
        }else {
            Common_Methods.Screenshot("EW1.Step1 Wrong navigate with incorrect URL");
        }
        SoftAssert.assertAll("The Failed Assertion");
    }


    // GC06 || SIT || Check reflection on home when adding new category
   /* public void Categories() throws IOException {
        Featured_Categories.getallfeaturesCategories();
        //Exist screenshot
        Featured_Categories.readCategoriesFromExcel();
        String [][] result = Featured_Categories.ValidateCategories_Items();
        Assert.assertEquals(result.toString(), "true");
    }*/
}


