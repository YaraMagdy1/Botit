package Catalog;

import Pages.Catalog;
import base.BaseTests;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class ChangeItemPrice extends BaseTests {

    WebDriver driver;

    @Test
    public void ChangeItemPrice() throws InterruptedException {
        Catalog.SelectCatalogTab();
        Thread.sleep(5000);
        Catalog.SelectItemsTab();
        Catalog.GetTableValue();
    }
}
