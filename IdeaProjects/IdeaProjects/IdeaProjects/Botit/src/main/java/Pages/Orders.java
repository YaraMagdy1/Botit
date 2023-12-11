package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;



public class Orders {

    private WebDriver driver;

    By AcceptedTab = By.xpath("//*[@id=\"tab2\"]/label[2]");
    By PendingTab = By.xpath("//*[@id=\"tab2\"]/label[1]");
    By ReadyForPickupTab = By.xpath("//*[@id=\"tab2\"]/label[3]");
    By PickedUpTab = By.xpath("//*[@id=\"tab2\"]/label[4]");
    By DeliveredTab = By.xpath("//*[@id=\"tab2\"]/label[5]");
    By CancelledTab = By.xpath("//*[@id=\"tab2\"]/label[6]");

    //By TableRow = By.xpath("//*[@id=\"Grid\"]/thead/tr");
    By AcceptingNumber = By.xpath("//*[@id=\"tab2\"]/label[2]/span");
    By PendingNumber = By.xpath("//*[@id=\"tab2\"]/label[1]/span");
    By TableRows = By.xpath("//table/tbody/tr");
    public Orders(WebDriver driver)
    {
        this.driver=driver;
    }

    public void ClickOnAcceptingOrders(){
        driver.findElement(AcceptedTab).click();

    }

    public void ClickOnPendingOrders(){
        driver.findElement(PendingTab).click();

    }

    public void ClickOnReadyForPickupOrders(){
        driver.findElement(ReadyForPickupTab).click();
    }

    public void ClickOnPickedUpOrders(){
        driver.findElement(PickedUpTab).click();
    }

    public void ClickOnDeliveredOrders(){
        driver.findElement(DeliveredTab).click();
    }
    public void ClickOnCancelledOrders(){
        driver.findElement(CancelledTab).click();
    }

    public Integer GetTableRowsCount(){
       // WebElement table =  driver.findElement(TableRow);
       // WebElement table = len (driver.findElement(TableRow));
      //  rows = driver.find_elements_by_xpath("//table/tbody/tr")
        List<WebElement> row = driver.findElements(TableRows);
      //  //*[@id="Grid"]
        System.out.println("Total Number of Rows = " + row.size());
        Integer rowNumber = row.size();

        return row.size();
    }


    public String GetAcceptingOrdersCount(){

        String Number = driver.findElement(AcceptingNumber).getText();
        return Number;

    }

    public String GetPendingOrdersCount(){

        String Pending_Number = driver.findElement(PendingNumber).getText();
        return Pending_Number;

    }




}
