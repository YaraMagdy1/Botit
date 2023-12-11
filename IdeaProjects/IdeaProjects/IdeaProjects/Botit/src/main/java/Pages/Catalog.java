package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Catalog {

    WebDriver driver;

    public Catalog(WebDriver driver){
        this.driver = driver;
    }

    By CatalogTab = By.xpath("/html/body/div/div/div/div[1]/div[2]/ul/li[4]/a");
    By ItemsTab = By.xpath("//*[@id=\"tab2\"]/label[2]");
    By SearchTextBox = By.xpath("//*[@id=\"search_word\"]");
    By SearchButton = By.xpath("/html/body/div/div/div/div[2]/div[2]/div/div[2]/div/div[1]/button[3]");
    By CatalogTable = By.xpath("//*[@id=\"Grid\"]/tbody/tr");



    public void SelectCatalogTab() throws InterruptedException {
        Thread.sleep(4000);
    driver.findElement(CatalogTab).click();

    }

    public void SelectItemsTab(){
    driver.findElement(ItemsTab).click();
    }

    public void SearchForItem(){
    driver.findElement(SearchTextBox).sendKeys("Baba Ghannoug Plate");
    driver.findElement(SearchButton).click();
    }

    public void GetTableValue(){
        List<WebElement> count = driver.findElements(CatalogTable);
        int rows = count.size();

        for(int i=1; i<rows; i++){
            WebElement sub = driver.findElement(By.xpath("//*[@id=\"Grid\"]/tbody/tr["+i+"]/td[1]"));
            String x = sub.getText();
            String FindText = "Baba Ghannoug Plate";
            if (sub.getText().equalsIgnoreCase("Baba Ghannoug Plate")){
                driver.findElement(By.xpath("//*[@id=\"Grid\"]/tbody/tr["+i+"]/td[6]/div/a")).click();
                break;
            }
            else
            {
                System.out.println(x);
            }
        }
       //     System.out.println("Count is "+rows);
       // }
    }

}
