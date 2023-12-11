package Orders;

import Pages.Login;
import Pages.Orders;
import base.BaseTests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class OrdersTestCount extends BaseTests {

    WebDriver driver;


    @Test
    public void GetOrdersCount_Accepting_Successfully(){
        orders.ClickOnAcceptingOrders();
      String Expected = orders.GetTableRowsCount().toString();
      String Actual =  orders.GetAcceptingOrdersCount();

        Assert.assertEquals(Actual,Expected);

    }

    @Test
    public void GetOrdersCount_Pending_Successfully(){
        orders.ClickOnPendingOrders();
        String Expected = orders.GetTableRowsCount().toString();
        String Actual =  orders.GetPendingOrdersCount();
        Assert.assertEquals(Actual,Expected);

    }

}
