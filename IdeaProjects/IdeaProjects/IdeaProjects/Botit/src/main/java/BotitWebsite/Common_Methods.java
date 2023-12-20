package BotitWebsite;

import com.google.common.io.Files;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Common_Methods {
    WebDriver driver;

    public Common_Methods() {
        this.driver=driver;
    }
    public void DB_Connection(){
        MongoClient mongoClient = MongoClients.create("mongodb+srv://transmission_dev:K1IPfykYMq6FAUv6@botit-dev.jwtve.mongodb.net/botitdev?retryWrites=true&w=majority");
        MongoDatabase database = mongoClient.getDatabase("botitdev");
    }
    public void Screenshot(String ScreenName) {
        TakesScreenshot camera = (TakesScreenshot) driver;
        File screenshot = camera.getScreenshotAs(OutputType.FILE);
        try {
            Files.move(screenshot,
                    new File("Resources/Screenshots/" + ScreenName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 /*   public void readCategoriesFromExcel() throws IOException {

        XSSFRow row;
        XSSFCell cell;

        try {
            //  FileInputStream file = new FileInputStream(new File("C:\\Users\\MO4\\Desktop\\BotitWebSite.xlsx"));
            FileInputStream file = new FileInputStream(new File("C:\\Users\\admin\\Downloads\\BotitWebSite.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            // get sheet number

            int sheetCn = workbook.getNumberOfSheets();
            for (int cn=0 ; cn < sheetCn; cn++) {
                // get 0th sheet data
                XSSFSheet sheet = workbook.getSheetAt(cn);
                // get number of rows from sheet
                int rows = sheet.getPhysicalNumberOfRows();
                // get number of cell from row
                int cells = sheet.getRow(cn).getPhysicalNumberOfCells();
                // value = new String[rows][cells];
                GetAllSheet = new String[rows][cells];
                for (int r = 0; r < rows; r++) {
                    row = sheet.getRow(r); // bring row
                    if (row != null) {
                        for (int c = 0; c < cells; c++) {
                            cell = row.getCell(c);

                            if (cell != null) {
                                // Process the cell value
                                DataFormatter dataFormatter = new DataFormatter();
                                String cellValue = dataFormatter.formatCellValue(cell);
                                GetAllSheet[r][c] = cell.toString();
                            }

                        }
                    }
                }

            }
            ArrayLength = GetAllSheet.length;
        } catch (Exception e) {
            System.out.println(e);
        }
    }
*/
}
