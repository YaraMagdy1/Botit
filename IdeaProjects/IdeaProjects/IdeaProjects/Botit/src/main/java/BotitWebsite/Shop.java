package BotitWebsite;

import com.amazonaws.services.dynamodbv2.xspec.B;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Shop {
    WebDriver driver;
    private By CategoryBox = By.tagName("a");
    public Shop(WebDriver driver){
        this.driver=driver;
    }
    public CategoryCaption ClickOnCategory(int index){
        WebElement Category = driver.findElement(CategoryBox);
        Actions action = new Actions(driver);
        action.moveToElement(Category).moveToElement(driver.findElement(By.xpath(""))).click().build().perform();
        return new CategoryCaption(Category.findElement(CategoryBox));
    }
    public class CategoryCaption{
        private WebElement caption;
        private By CategoryName = By.tagName("a");
        public CategoryCaption(WebElement caption){
            this.caption=caption;
        }
        public boolean IsCategoryNamedisplay(){
            return caption.isDisplayed();
        }
        String TitleOfCategory = "";
        public String GetNameOfCategory(){
            for ( int x=0 ; x<=10; x++) {
                WebElement title = driver.findElement(CategoryName);
                TitleOfCategory = title.getText();
                System.out.println(TitleOfCategory);
            }
            return TitleOfCategory;
        }
    }
}
