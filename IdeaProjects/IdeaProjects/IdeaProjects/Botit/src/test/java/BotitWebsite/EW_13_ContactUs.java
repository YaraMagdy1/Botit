package BotitWebsite;

import BaseWebsite.BaseWebsite;
import ReadProductSheet.ReadXLS;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class EW_13_ContactUs extends BaseWebsite {
    WebDriver driver;
   @Test(priority = 1)
    public void GC01()  {

     contactUs.iconClick();

       String actualUrl = contactUs.getUrl();
       String expectedUrl = "https://vendorbotit.com/botitwebsite/public/contact-us";

       Assert.assertEquals(actualUrl,expectedUrl);
       Common_Methods.Screenshot("contactUS");


    }
    @Test
    public void navToContact(){
       contactUs.iconClick();
    }

    @Test(dataProviderClass = ReadXLS.class,dataProvider = "testdata")
    public void GC02(String name,String email,String phone,String subject,String message) throws IOException {

        //user insert valid data and check the fields get empty
        contactUs.iconClick();
        contactUs.fillData(name,email,phone,subject,message);
        contactUs.submitClick();
        String expectedNameText = "";
        String actualNameText = contactUs.nameValidateText();
        Assert.assertEquals(expectedNameText,actualNameText);

    }
    @Test(priority = 2,dataProviderClass = ReadXLS.class,dataProvider = "testdata")
    public void BC01 (String name,String email,String phone,String subject,String message) throws IOException{
        contactUs.iconClick();
        contactUs.fillData(name,email,phone,subject,message);
        contactUs.submitClick();

        String expectedNameText = contactUs.nameValidateText();
        String expectedEmailText = contactUs.emailValidateText();
        String expectedPhoneText = contactUs.phoneValidateText();
        String expectedSubjectText = contactUs.subjectValidateText();
        String expectedMessageText = contactUs.messageValidateText();

        boolean expectedNameResult = contactUs.nameValidation();
        boolean expectedEmailResult = contactUs.emailValidation();
        boolean expectedPhoneResult = contactUs.phoneValidation();
        boolean expectedSubjectResult = contactUs.subjectValidation();
        boolean expectedMessageResult = contactUs.messageValidation();



        switch(name) {
            case "":
                Assert.assertTrue(expectedNameResult);
                Assert.assertEquals("Please provide your first name.",expectedNameText);

                break;
            case "mohamed":
                Assert.assertTrue(expectedEmailResult);
                Assert.assertEquals("Please provide your email address.",expectedEmailText);
                break;
            case "mohamed2":
                Assert.assertTrue(expectedPhoneResult);
                Assert.assertEquals("Please provide your phone number.",expectedPhoneText);
                break;
            case "mohamed3":
                Assert.assertTrue(expectedSubjectResult);
                Assert.assertEquals("Please provide your Subject.",expectedSubjectText);
                break;
            case "mohamed4" :
                Assert.assertTrue(expectedMessageResult);
                Assert.assertEquals("Please enter your message.",expectedMessageText);
                break;


        }
    }

   @Test(priority = 2,dataProviderClass = ReadXLS.class,dataProvider = "testdata")
    public void BC02 (String name,String email,String phone,String subject,String message){
        contactUs.iconClick();
        contactUs.fillData(name,email,phone,subject,message);
        contactUs.submitClick();

       boolean expectedNameResult = contactUs.nameValidation();
       Assert.assertTrue(expectedNameResult);


    }
    @Test(priority = 2,dataProviderClass = ReadXLS.class,dataProvider = "testdata")
    public void BC03 (String name,String email,String phone,String subject,String message){
        contactUs.iconClick();
        contactUs.fillData(name,email,phone,subject,message);
        contactUs.submitClick();

        boolean expectedNameResult = contactUs.nameValidation();
        Assert.assertTrue(expectedNameResult);

        boolean expectedSubjectResult = contactUs.subjectValidation();
        Assert.assertTrue(expectedSubjectResult);


    }
   @Test(dataProviderClass = ReadXLS.class,dataProvider = "testdata")
    public void BC04 (String name,String email,String phone,String subject,String message){

        contactUs.iconClick();
        contactUs.fillData(name,email,phone,subject,message);
        contactUs.submitClick();

       boolean expectedEmailResult = contactUs.emailValidation();
       Assert.assertTrue(expectedEmailResult);

    }
   @Test(priority = 2,dataProviderClass = ReadXLS.class,dataProvider = "testdata")
   public void BC05 (String name,String email,String phone,String subject,String message){
       contactUs.iconClick();
       contactUs.fillData(name,email,phone,subject,message);
       contactUs.submitClick();


       boolean expectedPhoneResult = contactUs.phoneValidation();
       Assert.assertTrue(expectedPhoneResult);

   }

}
