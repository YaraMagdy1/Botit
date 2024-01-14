package BaseWebsite;

import BotitWebsite.Common_Methods;
import PromoCode.PageLoginPromo;
import PromoCode.PagePromoCode;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class BasePromoCode {

    WebDriver driver;
    public PageLoginPromo pageLoginPromo;
    public PagePromoCode pagePromoCode;
    public BotitWebsite.Common_Methods common_Methods;

    @BeforeClass
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to("http://23.97.197.101:3000/");

        common_Methods = new Common_Methods(driver);
        pageLoginPromo = new PageLoginPromo(driver);
        pagePromoCode = new PagePromoCode(driver);


    }

 /*   @AfterClass
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }*/
}
