package springerNature.SND.testBase;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import com.aventstack.extentreports.Status;
import org.testng.ITestResult;
import org.testng.annotations.*;
import springerNature.SND.helper.browserConfig.Browser;
import springerNature.SND.helper.browserConfig.ChromeBrowser;
import springerNature.SND.helper.browserConfig.config.ObjectReader;
import springerNature.SND.helper.browserConfig.config.PropertyReader;
import springerNature.SND.helper.logger.LoggerHelper;

import springerNature.SND.helper.wait.WaitHelper;
import springerNature.SND.utils.ExtentManager;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * @author narasimha
 */
public class TestBase {


    public static ExtentReports extent;
    public static ExtentTest test;
    public WebDriver driver;
    private Logger log = LoggerHelper.getLogger(TestBase.class);
    public static File reportDirectery;

    @BeforeSuite
    public void beforeSuite(){
        extent = ExtentManager.getInstance();
    }


    @BeforeTest
    public void beforeTest() throws Exception{
        ObjectReader.reader = new PropertyReader();
        //reportDirectery = new File(ResourceHelper.getResourcePath("src/main/resources/screenShots"));
        setUpDriver(ObjectReader.reader.getBrowserType());
        test = extent.createTest(getClass().getSimpleName());
    }


    @BeforeMethod
    public void beforeMethod(Method method){
        test.log(Status.INFO, method.getName()+"**************Test Execution started***************");
        log.info("**************"+method.getName()+"Execution Started ***************");
    }

    @AfterMethod
    public void afterMethod(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, result.getThrowable());
           // String imagePath = captureScreen(result.getName(), driver);
          //  test.addScreenCaptureFromPath(imagePath);
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, result.getName() + " is pass");
            //String imagePath = captureScreen(result.getName(),driver);
            //test.addScreenCaptureFromPath(imagePath);
        }
    }
    @AfterTest
    public void afterTest() throws Exception{
        if(driver!=null){
            driver.quit();
        }
    }
    public WebDriver getBrowserObject(Browser btype) throws Exception{

        try{


                    // get object of ChromeBrowser class
                    ChromeBrowser chrome = ChromeBrowser.class.newInstance();
                    ChromeOptions option = chrome.getChromeOptions();
                    return chrome.getChromeDriver(option);

        }
        catch(Exception e){
            log.info(e.getMessage());
            throw e;
        }
    }
    public void setUpDriver(Browser btype) throws Exception{
        driver = getBrowserObject(btype);
        log.info("Initialize Web driver: "+driver.hashCode());
        WaitHelper wait = new WaitHelper(driver);
        wait.setImplicitWait(ObjectReader.reader.getImpliciteWait(), TimeUnit.SECONDS);
        //wait.pageLoadTime(ObjectReader.reader.getPageLoadTime(), TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    public static void logExtentReport(String s1){
        test.log(Status.INFO, s1);
    }

    public void getApplicationUrl(String url){
        driver.get(url);
        logExtentReport("Launching Springer ..."+url);
    }

}
