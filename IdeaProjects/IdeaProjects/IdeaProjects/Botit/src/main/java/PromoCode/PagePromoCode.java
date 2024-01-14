package PromoCode;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class PagePromoCode {
    WebDriver driver;
    public PagePromoCode(WebDriver driver){
        this.driver = driver;
    }

    By promoTab = By.xpath("//*[@id=\"root\"]/div/div/div/nav/a[2]/div[2]/span");
    By addBtn = By.cssSelector("svg[data-testid=\"AddIcon\"]");


    By generateCode = By.xpath("/html/body/div[3]/div/div/form/div/div[1]/label/span[1]/input");

    By codeField2 = By.xpath("//*[@id=\"create-random-code-insertion\"]");





    By typeField = By.cssSelector("div[role=\"combobox\"]");


    By fixedType = By.xpath("//*[@id=\":rr:\"]/li[1]");
    //By fixedType = By.cssSelector("li[data-value=\"FIXED\"]");

    By percentageType = By.xpath("//*[@id=\":rr:\"]/li[2]");

    //By valueField = By.xpath("//*[@id=\":rt:\"]");
    By valueField = By.cssSelector("input[id=\"promocode-value\"]");


   // By descriptionField = By.xpath("//*[@id=\":rv:\"]");
    By descriptionField = By.cssSelector("input[id=\"promocode-description\"]");

   // By compatibleIcon = By.xpath("/html/body/div[3]/div/div/form/div/div[5]/label/span[1]/input");
    By compatibleIcon = By.cssSelector("input[id=\"compatible-with-others\"]");


    By campaignList = By.cssSelector("div[id=\"campaign-select\"]");

    By createCampaignBtn = By.cssSelector("button[id=\"go-to-campaigns\"]");
    By newCampaignName = By.cssSelector("input[name=\"name\"]");

    By createNewCampaignBtn = By.cssSelector("button[class=\"MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary MuiButton-sizeMedium MuiButton-containedSizeMedium MuiButton-root MuiButton-contained MuiButton-containedPrimary MuiButton-sizeMedium MuiButton-containedSizeMedium css-1is3n76-MuiButtonBase-root-MuiButton-root\"]");

    By createBtn = By.cssSelector("button[id=\"create-promo-code\"]");




    By deleteCondition = By.xpath("/html/body/div[3]/div/div/form/div/div[7]/div[1]/table/tbody/tr/td[2]/button");

    By conditionList = By.xpath("//*[@id=\"conditions-select-0\"]");


    By createCondition = By.xpath("/html/body/div[3]/div/div/form/div/div[7]/div[2]/div/button");


    By addCondition = By.cssSelector("button[id=\"add-condition\"]");


    By secondConditionList = By.cssSelector("div[id=\"condition-type\"]");
    By newConditionName = By.cssSelector("input[type=\"name\"]");
    By conditionErrorMessag = By.cssSelector("input[type=\"errorMessage\"]");

    By alertMessage = By.xpath("/html/body/div[3]/div/div/div/div/div[2]");

    By codeNameAssert = By.cssSelector("td[class=\"MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium css-1ex1afd-MuiTableCell-root\"]");
    By codeValueAssert = By.xpath("//*[@id=\"root\"]/div/main/div[2]/div/div/div/table/tbody/tr[1]/td[2]");

    By threeDots = By.xpath("//*[@id=\"root\"]/div/main/div[2]/div/div/div/table/tbody/tr[1]/td[5]/button");




    public void promoNav(){
        driver.findElement(promoTab).click();
    }
    public void clickOnSpaceArea() {

        ((JavascriptExecutor) driver).executeScript("document.elementFromPoint(300, 200).click();");
    }
    /*public void form (String codeName,String value,String description){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement form = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ReactModalPortal")));

        //Code Name
        WebElement codeInput = form.findElement(codeField2);
        codeInput.sendKeys(codeName);

        //Select Type
        WebElement typeList = form.findElement(typeField);
        typeList.click();

        //Type of PromoCode
        WebElement typePercentage = form.findElement(percentageType);
        typePercentage.click();

        // Insert PromoCode Value
        WebElement valueInput = form.findElement(valueField);
        valueInput.clear();
        valueInput.sendKeys(value);

        //Insert Description
        WebElement descriptionInput = form.findElement(descriptionField);
        descriptionInput.sendKeys(description);

        //PromoCode to be compatible with others
        WebElement compatibleBox = form.findElement(compatibleIcon);
        compatibleBox.click();

        //Click on Campaign Menu
        WebElement campaignMenu = form.findElement(campaignList);
        campaignMenu.click();

        //Select Campaign
        WebElement campaign = form.findElement(campaignElement);
        campaign.click();

        //Click on Create Button
        WebElement createButton = form.findElement(createBtn);
        createButton.click();

    }*/

    /*public void form2(String value,String description){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement form = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ReactModalPortal")));

        WebElement generateButton = form.findElement(generateCode);
        generateButton.click();

        //Select Type
        WebElement typeList = form.findElement(typeField);
        typeList.click();

        //Type of PromoCode
        WebElement typePercentage = form.findElement(percentageType);
        typePercentage.click();

        // Insert PromoCode Value
        WebElement valueInput = form.findElement(valueField);
        valueInput.clear();
        valueInput.sendKeys(value);

        //Insert Description
        WebElement descriptionInput = form.findElement(descriptionField);
        descriptionInput.sendKeys(description);

        //Leave PromoCode to be compatible with others


        //Click on Campaign Menu
        WebElement campaignMenu = form.findElement(campaignList);
        campaignMenu.click();

        //Select Campaign
        WebElement campaign = form.findElement(campaignElement);
        campaign.click();
        ArrayList<WebElement> campaigns = new ArrayList<>();
        //campaigns = driver.findElements(By.cssSelector())

        //Click on Create Button
        WebElement createButton = form.findElement(createBtn);
        createButton.click();


    }*/
    public void form(boolean codeManual,String codeName,String type,String value,boolean isCompatible,boolean isOldCampaign,String description,String campaignName,boolean isOldCondition,String conditionName,String conditionErrorMessage) throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement form = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ReactModalPortal")));

        // Manual or Automatic
        if (codeManual){
            WebElement codeInput = form.findElement(codeField2);
            codeInput.sendKeys(codeName);
        }else {
            WebElement generateButton = form.findElement(generateCode);
            generateButton.click();
        }


        //Select Type
        WebElement typeList = form.findElements(typeField).get(0);
        typeList.click();
        if (type.contains("fixed")){

            WebElement ul_element = form.findElement(By.xpath("//*[@id=\"menu-\"]/div[3]/ul"));
            List<WebElement> li_All = ul_element.findElements(By.tagName("li"));
            li_All.get(0).click();

        }
        if (type.contains("percentage")) {


            WebElement ul_element = form.findElement(By.xpath("//*[@id=\"menu-\"]/div[3]/ul"));
            List<WebElement> li_All = ul_element.findElements(By.tagName("li"));
            li_All.get(1).click();
        }

        // Insert PromoCode Value
        WebElement valueInput = form.findElement(valueField);
//        valueInput.clear();
        valueInput.sendKeys(value);

        //Insert Description
        WebElement descriptionInput = form.findElement(descriptionField);
        descriptionInput.sendKeys(description);

        //Compatible or not
        if (isCompatible){
            WebElement compatibleBox = form.findElement(compatibleIcon);
            compatibleBox.click();
        }

        // Old Campaign or not
        if (isOldCampaign){
            //Click on Campaign Menu
            WebElement campaignMenu = form.findElement(campaignList);
            campaignMenu.click();

           // generate random number in list range

            List<WebElement> campaignList =  driver.findElements(By.cssSelector("li[class=\"MuiButtonBase-root MuiMenuItem-root MuiMenuItem-gutters MuiMenuItem-root MuiMenuItem-gutters css-kk1bwy-MuiButtonBase-root-MuiMenuItem-root\"]"));
            int size= campaignList.size();
            int min = 0;
            int max = size-1;

            Random random = new Random();
            int number = random.nextInt((max - min) + 1) + min;


            driver.findElements(By.cssSelector("li[class=\"MuiButtonBase-root MuiMenuItem-root MuiMenuItem-gutters MuiMenuItem-root MuiMenuItem-gutters css-kk1bwy-MuiButtonBase-root-MuiMenuItem-root\"]")).get(number).click();



        }else {
            // create new campaign
            createNewCampaign(campaignName);


        }


     /*  if (isOldCondition){
           WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10));
           WebElement form3 = wait3.until(ExpectedConditions.visibilityOfElementLocated(By.className("ReactModalPortal")));
            WebElement add = form3.findElement(addCondition);
           add.click();

           WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
           WebElement form2 = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.className("ReactModalPortal")));
           WebElement list = form2.findElement(conditionList);
           list.click();

           List<WebElement> conditionList =  driver.findElements(By.cssSelector("li[id=\"menu-select\"]"));
           int size= conditionList.size();
           int min = 0;
           int max = 65;

           Random random = new Random();
           int number = random.nextInt((max - min) + 1) + min;

           driver.findElements(By.cssSelector("li[id=\"menu-select\"]")).get(9).click();



        }else {
           createNewCondition(conditionName,conditionErrorMessage);
       }*/





    }
    public void createCondition(boolean isOldCondition){
        if (isOldCondition){


        WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement form3 = wait3.until(ExpectedConditions.visibilityOfElementLocated(By.className("ReactModalPortal")));
        WebElement add = form3.findElement(addCondition);
        add.click();

        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement form2 = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.className("ReactModalPortal")));
        WebElement list = form2.findElement(conditionList);
        list.click();

        List<WebElement> conditionList =  driver.findElements(By.cssSelector("li[id=\"menu-select\"]"));
        int size= conditionList.size();
        int min = 0;
        int max = 65;

        Random random = new Random();
        int number = random.nextInt((max - min) + 1) + min;

        driver.findElements(By.cssSelector("li[id=\"menu-select\"]")).get(9).click();
        }
    }

    public void createPromoCode(){
        WebDriverWait wait4 = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement form4 = wait4.until(ExpectedConditions.visibilityOfElementLocated(By.className("ReactModalPortal")));
        WebElement createButton = form4.findElement(createBtn);
        createButton.click();

    }
    public void createNewCampaign(String campaignName){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement form = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ReactModalPortal")));


        WebElement campaignBtn = form.findElement(createCampaignBtn);
        campaignBtn.click();

        driver.findElement(newCampaignName).sendKeys(campaignName);
        driver.findElement(createNewCampaignBtn).click();

        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement form2 = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.className("ReactModalPortal")));

        WebElement campaignMenu = form2.findElement(campaignList);
        campaignMenu.click();

        List<WebElement> campaignList =  driver.findElements(By.cssSelector("li[class=\"MuiButtonBase-root MuiMenuItem-root MuiMenuItem-gutters MuiMenuItem-root MuiMenuItem-gutters css-kk1bwy-MuiButtonBase-root-MuiMenuItem-root\"]"));
        int size= campaignList.size();

         driver.findElements(By.cssSelector("li[class=\"MuiButtonBase-root MuiMenuItem-root MuiMenuItem-gutters MuiMenuItem-root MuiMenuItem-gutters css-kk1bwy-MuiButtonBase-root-MuiMenuItem-root\"]")).get(size-1).click();
       // WebElement createButton = form2.findElement(By.xpath("/html/body/div[4]/div/div/form/div/div[7]/button"));
        //createButton.click();

    }
    public void createNewCondition(String conditionName,String conditionErrorMessage){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement form = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ReactModalPortal")));


        WebElement conditionAdd = form.findElement(addCondition);
        conditionAdd.click();

        WebElement conditionCreate = form.findElement(createCondition);
        conditionCreate.click();

        driver.findElement(addBtn).click();

        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement form2 = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.className("ReactModalPortal")));

        form2.findElement(secondConditionList).click();
        driver.findElements(By.cssSelector("li[class=\"MuiButtonBase-root MuiMenuItem-root MuiMenuItem-gutters MuiMenuItem-root MuiMenuItem-gutters css-kk1bwy-MuiButtonBase-root-MuiMenuItem-root\"]")).get(0).click();



        form2.findElement(newConditionName).sendKeys(conditionName);
        form2.findElement(conditionErrorMessag).sendKeys(conditionErrorMessage);





    }


    public void addIcon() throws InterruptedException {
        //Thread.sleep(3000);
        driver.findElement(addBtn).click();
       boolean isVisiable = driver.findElement(By.className("ReactModalPortal")).isDisplayed();
       if (isVisiable != true){
           driver.findElement(addBtn).click();
       }
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //WebElement form = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ReactModalPortal")));


    }

    public boolean message(){
        boolean validate = driver.findElement(alertMessage).isDisplayed();
        return validate;
    }



/*
    public void formException(boolean codeManual,String codeName,String type,String value,boolean isCompatible,boolean isOldCampaign,String description,String campaignName) throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement form = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ReactModalPortal")));

        // Manual orAutomatic
        if (codeManual){
            WebElement codeInput = form.findElement(codeField2);
            codeInput.sendKeys(codeName);
        }else {
            WebElement generateButton = form.findElement(generateCode);
            generateButton.click();
        }


        //Select Type
        WebElement typeList = form.findElement(typeField);
        typeList.click();
        if (Objects.equals(type, "fixed")){
            WebElement typeFixed = form.findElement(fixedType);
            typeFixed.click();

        }
        if (Objects.equals(type, "percentage")) {
            WebElement typePercentage = form.findElement(percentageType);
            typePercentage.click();
        }

        // Insert PromoCode Value
        WebElement valueInput = form.findElement(valueField);
//        valueInput.clear();
        valueInput.sendKeys(value);

        //Insert Description
        WebElement descriptionInput = form.findElement(descriptionField);
        descriptionInput.sendKeys(description);

        //Compatible or not
        if (isCompatible){
            WebElement compatibleBox = form.findElement(compatibleIcon);
            compatibleBox.click();
        }

        // Old Campaign or not

        WebElement e = form.findElement(createBtn);
        e.click();

        Thread.sleep(10000);

    }
*/

    public String nameAssert(){

        String code = driver.findElement(codeNameAssert).getText();
        return code;
    }

    public String valueAssert(){
        String value = driver.findElements(codeValueAssert).get(0).getText();
        return value;
    }

    public String campaignAssert(){
        WebDriverWait wait2 = new WebDriverWait(driver,Duration.ofSeconds(15));
        wait2.until(ExpectedConditions.visibilityOfElementLocated(threeDots));



        driver.findElement(threeDots).click();
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //WebElement root = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ReactModalPortal")));
        String campaign = driver.findElement(By.xpath("/html/body/div[4]/div[3]/div[2]/p[6]")).getText();
        return campaign;


    }
    public boolean conditionMandatoryAssert(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement root = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ReactModalPortal")));
        boolean mandatory = root.findElement(createBtn).isEnabled();
        return mandatory;
    }

    public void createNewCampaignException(String campaignName){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement form = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ReactModalPortal")));


        WebElement campaignBtn = form.findElement(createCampaignBtn);
        campaignBtn.click();

        driver.findElement(newCampaignName).sendKeys(campaignName);
        driver.findElement(createNewCampaignBtn).click();

        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement form2 = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.className("ReactModalPortal")));

        WebElement campaignMenu = form2.findElement(campaignList);
        campaignMenu.click();

        List<WebElement> campaignList =  driver.findElements(By.cssSelector("li[class=\"MuiButtonBase-root MuiMenuItem-root MuiMenuItem-gutters MuiMenuItem-root MuiMenuItem-gutters css-kk1bwy-MuiButtonBase-root-MuiMenuItem-root\"]"));
        int size= campaignList.size();

        driver.findElements(By.cssSelector("li[class=\"MuiButtonBase-root MuiMenuItem-root MuiMenuItem-gutters MuiMenuItem-root MuiMenuItem-gutters css-kk1bwy-MuiButtonBase-root-MuiMenuItem-root\"]")).get(size-1).click();
        WebElement createButton = form2.findElement(By.xpath("/html/body/div[4]/div/div/form/div/div[7]/button"));
        createButton.click();

    }
    

    /*By usersTab = By.xpath("//*[@id=\"root\"]/div/div/div/nav/a[3]/div[2]/span");
    By addUsers = By.xpath("//*[@id=\"root\"]/div/button");
    By usersName = By.xpath("//*[@id=\":r41:\"]");*/

}
