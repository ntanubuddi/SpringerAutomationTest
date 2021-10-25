package springerNature.SND.helper.assertion;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import springerNature.SND.helper.logger.LoggerHelper;
import springerNature.SND.testBase.TestBase;

import java.util.logging.Logger;

/**
 * @author narasimha
 */
public class VerificationHelper {
    private WebDriver driver;
    private Logger log = LoggerHelper.getLogger(VerificationHelper.class);

    public VerificationHelper(WebDriver driver){
        this.driver = driver;
    }

    public boolean isDisplayed(WebElement element){
        try{
            element.isDisplayed();
            log.info("Element is Displayed.."+element.getText());
            TestBase.logExtentReport("Element is Displayed.."+element.getText());
            return true;
        }
        catch(Exception e){
            log.info("Element is not Displayed.."+ e.getCause());
            TestBase.logExtentReport("Element is not Displayed.."+e.getMessage());
            return false;
        }
    }

    public String getText(WebElement element){
        if(null == element){
            log.info("WebElement is null..");
            return null;
        }
        boolean status = isDisplayed(element);
        if(status){
            log.info("element text is .."+element.getText());
            return element.getText();
        }
        else{
            return null;
        }
    }

    public String getTitle(){
        log.info("page title is: "+driver.getTitle());
        return driver.getTitle();
    }
}
