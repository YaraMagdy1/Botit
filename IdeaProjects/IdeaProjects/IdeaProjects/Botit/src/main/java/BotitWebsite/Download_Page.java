package BotitWebsite;

import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;

import java.util.Iterator;
import java.util.Set;

public class Download_Page {
    WebDriver driver;

    public Download_Page(WebDriver driver){
        this.driver=driver;
    }



    public String GetURL()  throws InterruptedException {
        String URL = driver.getCurrentUrl();
        Thread.sleep(3000);
        driver.close();
        Set<String> handles = driver.getWindowHandles();
        Iterator it = handles.iterator();
        String ParentID = (String) it.next();
        driver.switchTo().window(ParentID);
        return URL;
    }

}
