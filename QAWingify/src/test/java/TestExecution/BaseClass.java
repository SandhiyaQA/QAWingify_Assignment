package TestExecution;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import dataProviders.ConfigFileReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class BaseClass   {

    public static WebDriver driver;

     ExtentSparkReporter spark;
     ExtentReports reporter;
     ExtentTest testcase;



     @BeforeClass
    public  void executionStarts()
    {


        reporter=new ExtentReports();
        spark=new ExtentSparkReporter("Result/Regression.html");
        spark.config().setDocumentTitle("Demo Regression suite");
        reporter.attachReporter(spark);
        testcase=reporter.createTest("Browser Launched Successfully");
        System.setProperty("web driver.chrome.driver", "src/test/resources/chrome.exe");


        switch (ConfigFileReader.getBrowser().toLowerCase())
        {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver= new FirefoxDriver();
                testcase.log(Status.INFO,"Fire fox driver started");
                break;
            case "chrome":
                System.setProperty("web driver.chrome.driver", "src/test/resources/chrome.exe");

                ChromeOptions options=new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                options.addArguments("start-maximized");
                driver = new ChromeDriver(options);
                testcase.log(Status.INFO,"Chrome driver started and window Maximized");

//                if(ConfigFileReader.getMode().equalsIgnoreCase("yes"))
//                {
//                    options.addArguments("--headless");
//                    testcase.log(Status.INFO,"headless mode");
//                }
//
                    break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver=new EdgeDriver();
                testcase.log(Status.INFO,"Edge driver started");
                break;

        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(ConfigFileReader.getImplicitWait())));
        testcase.log(Status.INFO,"Implicitly waited");
        driver.manage().window().maximize();
        testcase.log(Status.INFO,"Window Maximized");


    }


    @AfterClass
    public  void closingBrowser()

    {
        driver.close();
        reporter.flush();
    }



//
//    public static void main(String[] args) throws IOException {
//        ExecutionScript executionScript=new ExecutionScript();
//        executionScript.fetchToWebElements(driver);
//        BaseClass.closingBrowser();
//


    }

