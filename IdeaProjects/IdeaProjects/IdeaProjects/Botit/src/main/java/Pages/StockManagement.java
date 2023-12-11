package Pages;

//import com.sun.org.apache.bcel.internal.generic.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import org.openqa.selenium.support.ui.Select;

public class StockManagement {


    WebDriver driver;

    public StockManagement(WebDriver driver){
        this.driver = driver;
    }
   // By SelectBranch = By.xpath("/html/body/div/div/div/div[2]/div[2]/div[2]/div/div");
   By SelectBranch = By.xpath("//*[@id=\"branches\"]");

    By ItemsTab = By.xpath("/html/body/div/div/div/div[2]/div[2]/div[2]/section/label[2]");


    By SearchTextBox = By.xpath("//*[@id=\"search_word\"]");
    By SearchButton = By.xpath("/html/body/div/div/div/div[2]/div[2]/div[2]/div/div/button[2]");
    By StockMangTab = By.xpath("/html/body/div/div/div/div[1]/div[2]/ul/li[3]/a");

    public  void SelectStockTab(){
        driver.findElement(StockMangTab).click();

    }
    public void selectBranch(){
        driver.findElement(SelectBranch).click();
        Select branches = new Select(driver.findElement(SelectBranch));
        branches.selectByVisibleText("testing");

    }

    public void selectItemsTab(){
        driver.findElement(ItemsTab).click();
    }

    public void searchbyItem(){
        driver.findElement(SearchTextBox).sendKeys("Falafel Labna Plate");
        driver.findElement(SearchButton).click();

    }

    //*[@id="Grid"]/tbody/tr[8]/td[1]
//*[@id="Grid"]/tbody/tr/td[1]

    //*[@id="Grid"]/tbody/tr/td[1]/div[1]
    //*[@id="Grid"]/tbody/tr/td[1]/div[1]/div/h2
}
