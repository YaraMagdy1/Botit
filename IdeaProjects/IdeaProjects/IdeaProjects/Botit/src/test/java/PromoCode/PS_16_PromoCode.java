package PromoCode;

import BaseWebsite.BasePromoCode;
import BotitWebsite.Common_Methods;
import ReadProductSheet.ReadPromoCode;
import ReadProductSheet.ReadXLS;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class PS_16_PromoCode  {
    WebDriver driver;
    public PageLoginPromo pageLoginPromo;
    public PagePromoCode pagePromoCode;

    @BeforeClass
    public void signIn(){

        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to("http://23.97.197.101:3000/");
        pageLoginPromo = new PageLoginPromo(driver);
        pagePromoCode = new PagePromoCode(driver);
        pageLoginPromo.login();
        pagePromoCode.promoNav();
        pagePromoCode.clickOnSpaceArea();
        System.out.println("");
    }

   @Test(priority = 1,dataProviderClass = ReadPromoCode.class,dataProvider = "testdata")
    public void GC01(String codeName,String type,String value,String description,String campaignName,String conditionName,String errorMessage) throws InterruptedException {

        pagePromoCode.addIcon();
        pagePromoCode.form(true,codeName,type,value,true,true,description,campaignName,true,conditionName,errorMessage);

        Assert.assertEquals(pagePromoCode.nameAssert(),codeName);
    }

   @Test(priority = 2,dataProviderClass = ReadPromoCode.class,dataProvider = "testdata")
    public void GC02(String codeName,String type,String value,String description,String campaignName,String conditionName,String errorMessage) throws InterruptedException {

        pagePromoCode.clickOnSpaceArea();
        pagePromoCode.addIcon();
        pagePromoCode.form(false,codeName,type,value,false,true,description,campaignName,true,conditionName,errorMessage);
        pagePromoCode.createPromoCode();
        Assert.assertEquals(pagePromoCode.valueAssert(),value);


    }
    /* @Test(priority = 3,dataProviderClass = ReadPromoCode.class,dataProvider = "testdata")
    public void GC03(String codeName,String type,String value,String description,String campaignName) throws InterruptedException {

        pagePromoCode.clickOnSpaceArea();
        pagePromoCode.addIcon();
        pagePromoCode.form(false,codeName,type,value,true,true,description,campaignName);
    }*/

   @Test(priority = 3,dataProviderClass = ReadPromoCode.class,dataProvider = "testdata")
    public void GC04(String codeName,String type,String value,String description,String campaignName,String conditionName,String errorMessage) throws InterruptedException {

       pagePromoCode.clickOnSpaceArea();
        pagePromoCode.addIcon();
        pagePromoCode.form(true,codeName,type,value,true,false,description,campaignName,true,conditionName,errorMessage);
        pagePromoCode.createPromoCode();
        Assert.assertEquals(pagePromoCode.campaignAssert(),campaignName);
    }


 /*@Test(priority = 2,dataProviderClass = ReadPromoCode.class,dataProvider = "testdata")
 public void GC05(String codeName,String type,String value,String description,String campaignName) throws InterruptedException {

     pagePromoCode.addIcon();
     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
     WebElement form = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ReactModalPortal")));
     WebElement codeInput = form.findElement(By.xpath("//*[@id=\":rn:\"]"));
     codeInput.sendKeys(codeName);
     pagePromoCode.form(false,codeName,type,value,true,true,description,campaignName);
 }*/






  /*  @Test(priority = 2,dataProviderClass = ReadPromoCode.class,dataProvider = "testdata")
    public void BC01(String codeName,String type,String value,String description,String campaignName) throws InterruptedException {

        pagePromoCode.addIcon();
        pagePromoCode.form(true,codeName,type,value,true,true,description,campaignName);
    }

    @Test(priority = 2,dataProviderClass = ReadPromoCode.class,dataProvider = "testdata")
    public void BC02(String codeName,String type,String value,String description,String campaignName) throws InterruptedException {

        pagePromoCode.addIcon();
        pagePromoCode.form(true,codeName,type,value,true,true,description,campaignName);
    }
    @Test(priority = 2,dataProviderClass = ReadPromoCode.class,dataProvider = "testdata")
    public void BC03(String codeName,String type,String value,String description,String campaignName) throws InterruptedException {

        pagePromoCode.addIcon();
        pagePromoCode.form(false,codeName,type,value,true,true,description,campaignName);
        boolean expectedMessageResult = pagePromoCode.message();
        Assert.assertTrue(expectedMessageResult);
    }
    @Test(priority = 2,dataProviderClass = ReadPromoCode.class,dataProvider = "testdata")
    public void BC04(String codeName,String type,String value,String description,String campaignName) throws InterruptedException {

        pagePromoCode.addIcon();
        pagePromoCode.formException(false,codeName,type,value,true,true,description,campaignName);
        boolean expectedMessageResult = pagePromoCode.message();
        Assert.assertTrue(expectedMessageResult);
    }*/







}
