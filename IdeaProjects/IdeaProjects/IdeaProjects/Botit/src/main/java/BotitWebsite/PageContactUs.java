package BotitWebsite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class PageContactUs {
WebDriver driver;
    public PageContactUs(WebDriver driver) {
        this.driver = driver;
    }

   public By contactIcon = By.xpath("/html/body/div[4]/div/div[1]/div[2]/ul/li[4]/a/p");
    public By nameField = By.cssSelector("input[placeholder=\"Your name *\"]");
    public By emailField = By.cssSelector("input[placeholder=\"Email *\"]");
    public By phoneField = By.cssSelector("input[placeholder=\"Phone number *\"]");
    public By subjectField = By.cssSelector("input[placeholder=\"Subject *\"]");
    public By messageField = By.cssSelector("textarea[placeholder=\"Message *\"]");
    public By submitBtn = By.xpath("//form[@id=\"form\"]//button[@type=\"submit\"]");

    public By nameValidate = By.xpath("//*[@id=\"form\"]/ul/li[1]/div");
    public By emailValidate = By.xpath("//*[@id=\"form\"]/ul/li[2]/div");
    public By phoneValidate = By.xpath("//*[@id=\"form\"]/ul/li[3]/div");
    public By subjectValidate = By.xpath("//*[@id=\"form\"]/ul/li[4]/div");
    public By messageValidate = By.xpath("//*[@id=\"form\"]/ul/li[5]/div");


public String getUrl(){
    String url = driver.getCurrentUrl();
    return url;
}
    public void iconClick(){
        driver.findElement(contactIcon).click();

    }
    public boolean nameDisplayed(){
       boolean check = driver.findElement(nameValidate).isDisplayed();
       return check;
    }
    public void fillData(String name,String email,String phone,String subject,String message )  {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(phoneField).sendKeys(phone);
        driver.findElement(subjectField).sendKeys(subject);
        driver.findElement(messageField).sendKeys(message);

    }

    public void submitClick(){
        driver.findElement(submitBtn).click();

    }

    public String nameValidateText(){

        String name = driver.findElement((By) nameValidate).getText();
        return name;
    }

    public String phoneValidateText(){

        String phone = driver.findElement((By) phoneValidate).getText();
        return phone;
    }
    public String subjectValidateText(){

        String subject = driver.findElement((By) subjectValidate).getText();
        return subject;
    }
    public String messageValidateText(){

        String message = driver.findElement((By) messageValidate).getText();
        return message;
    }
    public String emailValidateText(){

        String email = driver.findElement((By) emailValidate).getText();
        return email;
    }
    public boolean nameValidation(){
        boolean validate = driver.findElement(nameValidate).isDisplayed();
        return validate;
    }
    public boolean emailValidation(){
        boolean validate = driver.findElement(emailValidate).isDisplayed();
        return validate;
    }

    public boolean phoneValidation(){
        boolean validate = driver.findElement(phoneValidate).isDisplayed();
        return validate;
    }
    public boolean subjectValidation(){
        boolean validate = driver.findElement(subjectValidate).isDisplayed();
        return validate;
    }
    public boolean messageValidation(){
        boolean validate = driver.findElement(messageValidate).isDisplayed();
        return validate;
    }

}
