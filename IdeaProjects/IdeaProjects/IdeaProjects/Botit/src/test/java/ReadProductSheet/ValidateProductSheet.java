package ReadProductSheet;


import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ValidateProductSheet {

    File src = new File("C:\\Users\\MO4\\Downloads\\Diwan Final PS.xlsx");
    FileInputStream file = new FileInputStream(src);
    XSSFWorkbook xsf = new XSSFWorkbook(file);
    String[] forbidden_char = new String[] {"#", "\\" , "*" , "\"","//"};
    int ItemsRowNumber = 0;
    int NumberOfInavlidRows = 0;
    public ValidateProductSheet() throws IOException {
    }

    @Test(priority = 1) //GC01 ("Check Item name validation")
    public void readItemNameData() throws IOException {

        XSSFSheet sheet = xsf.getSheetAt(0);
         NumberOfInavlidRows = 0;
        ItemsRowNumber = 0;
        for (int i = 3; i < 3400; i++) {
            String entry = sheet.getRow(i).getCell(1).getStringCellValue();
            for (int j = 0; j < forbidden_char.length; j++) {
                if (entry.contains(forbidden_char[j])) {
                    NumberOfInavlidRows += 1;
                    ItemsRowNumber = i + 1;
                    System.out.println(entry + " row number:" + ItemsRowNumber + "\n");

                }
            }
        }
            System.out.println("Number of items contains invalid values " + NumberOfInavlidRows);
        }


        @Test (priority = 2) //GC01 ("Check Item Category validation")
        public void readItemCategory() throws IOException {
            NumberOfInavlidRows = 0;
            ItemsRowNumber = 0;
            XSSFSheet sheet = xsf.getSheetAt(0);
          //  int count2 = 1;
            for (int i = 3; i < 3400; i++) {
                String entry = sheet.getRow(i).getCell(12).getStringCellValue();
                    for (int j = 0; j < forbidden_char.length; j++ ){
                        if (entry.contains(forbidden_char[j])){
                            NumberOfInavlidRows += 1;
                            ItemsRowNumber = i+1;
                            System.out.println(entry + " row number:" + ItemsRowNumber + "\n");

                        }
                        else {

                        }

                }
            }
            System.out.println("Number of items incorrect in Item Category " + NumberOfInavlidRows);
        }

                @Test (priority = 3) //GC03 ("Check Item Category validation")
                public void ReadShoppingSubcategory() throws IOException {
                    NumberOfInavlidRows = 0;
                    ItemsRowNumber = 0;
                    XSSFSheet sheet = xsf.getSheetAt(0);

                    for (int i = 3; i < 3400; i++) {
                        String entry = sheet.getRow(i).getCell(13).getStringCellValue();
                        for (int j = 0; j < forbidden_char.length; j++) {
                            if (entry.contains(forbidden_char[j])) {
                                NumberOfInavlidRows += 1;
                                ItemsRowNumber = i + 1;
                                System.out.println(entry + " row number:" + ItemsRowNumber + "\n");

                            } else {

                            }

                        }
                    }
                        System.out.println("Number of items incorrect in shopping sub category" + NumberOfInavlidRows);


                }
    @Test (priority = 4) //GC01 ("Check Logistic size validation")
    public void ReadLogisticSize() throws IOException {


        NumberOfInavlidRows = 0;
        ItemsRowNumber = 0;
        XSSFSheet sheet = xsf.getSheetAt(0);

        for ( int i = 3; i< 3400 ; i++){
            String entry = sheet.getRow(i).getCell(557).getStringCellValue();
            if(entry == "")
            {

                //System.out.println("Number of items empty with Logistic Size" + count4);
                NumberOfInavlidRows +=1 ;
                ItemsRowNumber = i+1;
                System.out.println("Item category "+ entry + " row number:" + ItemsRowNumber + "\n");

            }
            else
            {

            }

        }
        System.out.println("Number of items empty with Logistic Size" + NumberOfInavlidRows);


    }

}
