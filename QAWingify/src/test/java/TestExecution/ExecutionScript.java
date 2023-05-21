package TestExecution;

import com.aventstack.extentreports.Status;
import dataProviders.ConfigFileReader;
import objectManager.ObjectManager;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.Listener;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Listeners(Listener.class)
public class ExecutionScript extends BaseClass {



    public FileInputStream fileInputStream;
    public HSSFWorkbook workbook;
    public HSSFSheet sheet;
    public HSSFRow row;
    public String column;
    public String username, Password,expected;
    public int no_of_cells, no_of_rows;
    public static List<String> userName = new ArrayList<>();
    public static List<String> pass = new ArrayList<>();

    public static List<String> Expected=new ArrayList<>();
    public SoftAssert softAssert;




@Test(description="verify the title,URL and PageHeading  ",priority = 0)
public void verifyBreadCrums()
{
    try {
        testcase = reporter.createTest("verify the title,URL and PageHeading");
        driver.get(ConfigFileReader.getUrl());
        testcase.log(Status.INFO, " Navigated to URL");
        ObjectManager objectManager = new ObjectManager(driver);
        softAssert = new SoftAssert();
        softAssert.assertEquals(driver.getCurrentUrl(), ConfigFileReader.getUrl(), "URL is Mismatched with Actual URL");
        testcase.log(Status.PASS, " URL is matched with Actual URL");
        softAssert.assertEquals(driver.getTitle(), "Demo App", "Title mismatched with Actual Title");
        testcase.log(Status.PASS, " Title is matched with Actual Title");
        softAssert.assertEquals(objectManager.getLoginPageObjects().getPageHeading(), "Login Form", "PageHeading mismatched with Actual page heading");
        testcase.log(Status.PASS, "Page Heading matched with Actual Page Heading");
        softAssert.assertAll();
    }
    catch(Exception e)
    {
        System.out.println(e.getMessage());
        testcase.log(Status.FAIL,"verify the title,URL and PageHeading");
    }

}




@Test(description = "verifying the login functionality accepting all possible values or not" ,priority = 1)
    public void fetchToWebElements() throws IOException {

try{
    testcase=reporter.createTest("verifying the login functionality accepting all possible values or not");
    fileInputStream = new FileInputStream(ConfigFileReader.getTestDataFile());
    workbook = new HSSFWorkbook(fileInputStream);
    sheet = workbook.getSheet("Sheet1");
    no_of_rows = sheet.getLastRowNum();
    no_of_cells = sheet.getRow(0).getLastCellNum();

    int n = 1;
    for (int i = 1; i < no_of_rows; i++) {

        driver.get(ConfigFileReader.getUrl());
        testcase.log(Status.INFO,"Navigating to the URL");
        //implicit wait to load web elements and to maintain synchronization
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(400));

        readFromExcel(i, 3);
        for (int j = 0; j < userName.size(); j++) {
            username = userName.get(0);
        }
        for (int l = 0; l < pass.size(); l++) {
            Password = pass.get(0);
        }
        for (int m = 0; m < Expected.size(); m++) {
            expected = Expected.get(0);
        }


        ObjectManager objectManager = new ObjectManager(driver);
        objectManager.getLoginPageObjects().enterUsername(username);
        testcase.log(Status.INFO,"Entering UserName");
        System.out.println(username);
        objectManager.getLoginPageObjects().enterPassword(Password);
        testcase.log(Status.INFO,"Entering Password");
        objectManager.getLoginPageObjects().clickLogin();
        testcase.log(Status.INFO,"Clicking Login");
        System.out.println(Password);
        softAssert = new SoftAssert();
        if (driver.getCurrentUrl() != ConfigFileReader.getUrl()) {
            softAssert.assertEquals(driver.getCurrentUrl(), expected, "testcase for login successful is failed");
            System.out.println(expected);
            testcase.log(Status.PASS,"login successful!!! with valid and invalid credentials");
        } else if (driver.getCurrentUrl() == ConfigFileReader.getUrl()) {
            softAssert.assertTrue(expected.contains(objectManager.getLoginPageObjects().getWarmMessage()), " not verified");
            testcase.log(Status.PASS,"login unsuccessful with blank and any of one blank field in the username or password");
            System.out.println(expected);
            //continue;
        }
        //softAssert.assertAll();
        n++;
        userName.remove(0);
        pass.remove(0);
        Expected.remove(0);
    }}
    catch(Exception e)
    {
        System.out.println(e.getMessage());
        testcase.log(Status.FAIL,"verifying the login functionality accepting all possible values or not");

    }
    }

@Test(description = "testcase for verifying whether the amounts in the Amount column are sorted are not", priority = 2)
    public void gettableElements() {

    testcase=reporter.createTest("testcase for verifying whether the amounts in the Amount column are sorted are not");
    try {
        driver.get(ConfigFileReader.getUrl());
        testcase.log(Status.INFO,"Navigating to URL");
        //implicit wait to load web elements and to maintain synchronization
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(400));
        testcase.log(Status.INFO,"implicitly waiting for the elements");
        ObjectManager objectManager = new ObjectManager(driver);
        objectManager.getLoginPageObjects().enterUsername(ConfigFileReader.getUserName());
        testcase.log(Status.INFO,"Entering UserName");
        objectManager.getLoginPageObjects().enterPassword(ConfigFileReader.getPassword());
        testcase.log(Status.INFO,"Entering password");
        objectManager.getLoginPageObjects().clickLogin();
        testcase.log(Status.INFO,"Clicking Login Button");
        objectManager.getHomePageObjects().clickamount();
        testcase.log(Status.INFO,"Clicking the Amount header");
        List<String> returnedElements = new ArrayList<>();
        testcase.log(Status.INFO,"Getting the values from Amount Column");
        List<Float> exactamountListasFloat = new ArrayList<>();
        for (String element : objectManager.getHomePageObjects().gettotalElementsInAmount()) {
            returnedElements.add(element);
        }


        for (String element : returnedElements) {
            String elementsInString = element.replace("USD", "").replace(",", "");
            String afterValidation = "";
            if (elementsInString.contains("+ ")) {
                afterValidation += elementsInString.replace("+ ", "").trim();
                exactamountListasFloat.add(Float.parseFloat(afterValidation));
            } else if (elementsInString.contains("- ")) {
                afterValidation += elementsInString.replace("- ", "").trim();
                exactamountListasFloat.add(-(Float.parseFloat(afterValidation)));

            }

        }
        testcase.log(Status.INFO,"Converting to Float for verifying whether elements are sorted");
        System.out.println(exactamountListasFloat);
        testcase.log(Status.INFO,"Converted to Float for verifying whether elements are sorted");
        for (int i = 0; i < exactamountListasFloat.size(); i++) {
            if (exactamountListasFloat.get(i) < exactamountListasFloat.get(i + 1)) {
                System.out.println("The values in Amount columns are in sorted order");
                testcase.log(Status.PASS,"The values in Amount columns are in sorted order");
                break;
            } else {
                System.out.println("The values in the Amount column is not in sorted order");
                testcase.log(Status.FAIL,"The values in Amount columns are not in sorted order");
                break;
            }
        }
    }
    catch (Exception e)
    {
        System.out.println(e.getMessage());
        testcase.log(Status.FAIL,"testcase for verifying whether the amounts in the Amount column are sorted are not");
    }
    }
    public  void readFromExcel(int n, int no_of_cells) throws IOException {

        row = sheet.getRow(n);

        for(int j=0;j<=2;j++) {

            HSSFCell col = row.getCell(j);
            column = col.getStringCellValue();


            switch (j) {
                case 0:
                    userName.add(column);
                    break;

                case 1:
                    pass.add(column);

                    break;
                case 2:
                    Expected.add(column);
                    break;
            }

        }

    }}



