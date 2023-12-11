package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Account {

    WebDriver driver;

    public Account(WebDriver driver){
        this.driver = driver;
    }

    //locators


    By Account_WorkingHours = By.xpath("/html/body/div/div/div/div[2]/div[2]/div/div[2]/ul/li[4]/span[2]");
    By RegularSchedule = By.xpath("/html/body/div/div/div/div[2]/div[2]/div/div[2]/div[2]/div/div[2]/div[1]/label[1]");
    By duringSelectedHours = By.xpath("/html/body/div/div/div/div[2]/div[2]/div/div[2]/div[2]/div/div[2]/div[1]/label[2]");
    By RegularFrom = By.xpath("/html/body/div/div/div/div[2]/div[2]/div/div[2]/div[2]/div/div[2]/div[2]/div/div[1]/div[2]/div");
    By DoneButton = By.xpath("/html/body/div/div/div/div[2]/div[2]/div/div[2]/div[2]/div/div[2]/input");
   // By DuringHours = By.xpath("/html/body/div/div/div/div[2]/div[2]/div/div[2]/div[2]/div/div[2]/div[1]/label[2]");
    By RegularFromTime = By.xpath("/html/body/div/div/div/div[2]/div[2]/div/div[2]/div[2]/div/div[2]/div[2]/div/div[1]/div[2]/div");
    By RegularToTime = By.xpath("/html/body/div/div/div/div[2]/div[2]/div/div[2]/div[2]/div/div[2]/div[2]/div/div[2]/div[2]/div");


    public void ClickOnAccountWorkingHours(){
        driver.findElement(Account_WorkingHours).click();
    }
    public Boolean SelectRegularSchedule(){
        //driver.findElement(AccountLink).click();


        driver.findElement(RegularSchedule).click();
       Boolean result1 = driver.findElement(DoneButton).isEnabled();

       return result1;
    }

    public void SelectHoursSchedule(){
        //driver.findElement(AccountLink).click();

        driver.findElement(Account_WorkingHours).click();
        //driver.findElement(RegularSchedule).click();
        driver.findElement(duringSelectedHours).click();

    }

    public boolean CheckdefaultSelection(){
        boolean GetRegularSelection = driver.findElement(RegularSchedule).isSelected();
        boolean GetWHSelection = driver.findElement(duringSelectedHours).isSelected()  ;
        return GetRegularSelection;
    }

    public void SetRegularTime(){

        driver.findElement(RegularFromTime).sendKeys("01:00 pm");
        driver.findElement(RegularToTime).sendKeys("05:00 pm");
    }

    public boolean CheckDoneButton(){
        boolean IsEnable = driver.findElement(DoneButton).isEnabled();
        return IsEnable;
    }

    public void ClickonDone()
    {
        driver.findElement(DoneButton).click();
    }
}

