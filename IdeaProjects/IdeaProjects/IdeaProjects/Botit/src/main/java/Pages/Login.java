package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login {

    //Locators
    private  By username = By.name("email");
    private  By password = By.name("password");
    private  By Login_btn = By.className("button-input");

    //Constructor
    WebDriver driver;

    public Login(WebDriver driver){
        this.driver = driver;
    }



    public void setUsername(String Usernamefield){

        driver.findElement(username).sendKeys(Usernamefield);
    }

    public void setPassword(String Passwordfield){
        driver.findElement(password).sendKeys(Passwordfield);
    }

    public Dashboard clickloginbutton(){
        driver.findElement(Login_btn).click();
        return new Dashboard(driver);
    }
}
