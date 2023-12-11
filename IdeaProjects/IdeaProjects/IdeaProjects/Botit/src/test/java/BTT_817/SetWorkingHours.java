package BTT_817;

import Pages.Account;
import Pages.Dashboard;
import base.BaseTests;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SetWorkingHours extends BaseTests{

    WebDriver driver;
   // public Dashboard dashboard;




    @Test
    public void ChooseRegularSchedule(){
    //new Dashboard(driver).NavigatetoAccount();
    //new Account(driver).SelectRegularSchedule();
    dashboard.NavigatetoAccount();
    account.ClickOnAccountWorkingHours();
    boolean result = account.CheckdefaultSelection();
    //if(result == true) {
       // Boolean ActualResult = Boolean.valueOf(account.SelectRegularSchedule().toString());
        // Assert.assertEquals(ActualResult, "false");
      String Isenable = String.valueOf(account.CheckDoneButton());
        Assert.assertEquals(Isenable, "false");
   // }
   // else
   // {
      //  System.out.println("Regular is not the default selection");
   // }

    }

    @Test
    public void SetRegularWorkingHours(){
        account.SetRegularTime();
        boolean BtnIsEnable =  account.CheckDoneButton();
        if (BtnIsEnable == true)
        {
            account.ClickonDone();
        }
    }

}
