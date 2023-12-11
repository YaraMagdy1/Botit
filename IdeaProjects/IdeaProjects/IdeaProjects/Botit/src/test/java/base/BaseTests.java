package base;


import Pages.*;
//import com.sun.org.apache.bcel.internal.generic.PUSH;
import junit.framework.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import Pages.Login;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTests {

    private WebDriver driver;
    public Login login;
    public Orders orders;
    public Account account;
    public Dashboard dashboard;

    public StockManagement StockManagement;
    public Catalog Catalog;
    //public BotitWebsite.Featured_Categories Featured_Categories;

    @BeforeClass
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\MO4\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://transmission-dev.azurewebsites.net/login");
        //driver.get("file:///C:/Users/MO4/Downloads/New-Botit-Website%20(1)/New-Botit-Website/Home.html");
        driver.manage().window().maximize();
        login = new Login(driver);
        orders = new Orders(driver);
        account = new Account(driver);
        dashboard = new Dashboard(driver);
        StockManagement = new StockManagement(driver);
        Catalog = new Catalog(driver);
        //Featured_Categories = new Featured_Categories(driver);
        login.setUsername("testre@test.com");
        login.setPassword("123456");
        Dashboard dashboard = login.clickloginbutton();
        String ActualResult = dashboard.getURL();
        Assert.assertEquals("http://transmission-dev.azurewebsites.net/home",ActualResult);
    }

    @AfterClass
    public void teardown(){
       // driver.quit();
    }


}
