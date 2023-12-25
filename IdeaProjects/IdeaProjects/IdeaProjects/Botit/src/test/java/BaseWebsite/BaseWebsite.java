package BaseWebsite;
import BotitWebsite.*;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import static com.mongodb.client.model.Filters.eq;

public class BaseWebsite {
    WebDriver driver;
    public Common_Methods Common_Methods;
    public BotitWebsite.Featured_Categories Featured_Categories;
    public BotitWebsite.Popular_Product Popular_Product;
    public BotitWebsite.Product_Details Product_Details;
    public BotitWebsite.Recently_Added_Brands Recently_Added_Brands;
    public BotitWebsite.Vendor_Details Vendor_Details;
    public BotitWebsite.Featured_Brands Featured_Brands;
    public BotitWebsite.Shop_Sub_Category Shop_Sub_Category;
    public BotitWebsite.Offers Offers;
    public BotitWebsite.Offers_Page Offers_Page;
    public BotitWebsite.Search_Bar Search_Bar;
    @BeforeClass
    public void setUpSite() {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://vendorbotit.com/botitwebsite/public/");
        driver.manage().window().maximize();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("window.scrollBy(0,2000)","");
        //Scroll down till the bottom of the page
        js.executeScript("window.scrollBy(1,document.body.scrollHeight)");
        Common_Methods=new Common_Methods(driver);
        Featured_Categories = new Featured_Categories(driver);
        Popular_Product = new Popular_Product(driver);
        Product_Details = new Product_Details(driver);
        Recently_Added_Brands = new Recently_Added_Brands(driver);
        Vendor_Details = new Vendor_Details(driver);
        Featured_Brands = new Featured_Brands(driver);
        Offers = new Offers(driver);
        Offers_Page =new Offers_Page(driver);
        Search_Bar =new Search_Bar(driver);
        Shop_Sub_Category =new Shop_Sub_Category(driver);
        }
}
