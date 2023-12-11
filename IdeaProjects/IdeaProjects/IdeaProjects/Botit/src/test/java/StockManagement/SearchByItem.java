package StockManagement;

import Pages.Catalog;
import Pages.StockManagement;
import base.BaseTests;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class SearchByItem extends BaseTests {

    WebDriver driver;


    @Test
    public void SearchByValidItem() throws InterruptedException {
        StockManagement.SelectStockTab();
        StockManagement.selectBranch();
        Thread.sleep(5000);
        StockManagement.selectItemsTab();
        StockManagement.searchbyItem();
    }



}
