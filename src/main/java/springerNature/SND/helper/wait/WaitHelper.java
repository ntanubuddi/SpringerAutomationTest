package springerNature.SND.helper.wait;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import springerNature.SND.helper.logger.LoggerHelper;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * @author narasimha
 */
public class WaitHelper {
    private WebDriver driver;
    private Logger log=LoggerHelper.getLogger(WaitHelper.class);
    public WaitHelper(WebDriver driver)
    {
        this.driver=driver;

    }

    /**
     * This is implicit wait method
     * @param timeoutInSeconds
     * @param seconds
     */
    public void setImplicitWait(long timeoutInSeconds, TimeUnit seconds)
    {
        log.info("implicit Wait has been set to "+timeoutInSeconds);
        new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));;
    }

    /**
     * This is Webdriver wait method it will wait for the given time
     * @param timeoutInSeconds
     * @param pollingEveryMillisecond
     * @return
     */
    private WebDriverWait getWait(long timeoutInSeconds , int pollingEveryMillisecond)
    {
        WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.pollingEvery(Duration.ofMillis(pollingEveryMillisecond));
        wait.ignoring(NoSuchElementException.class);
        return wait;
    }

    /**
     * This is Wait for element Visible method it will wait untill the element is visible in the given time
     * @param element
     * @param timeOutInSeconds
     * @param pollingEveryMilliSec
     */
    public void WaitForelementVisible(WebElement element, int timeOutInSeconds, int pollingEveryMilliSec)
    {
        log.info("waiting for :"+element.toString()+"for :"+timeOutInSeconds+" seconds");
        WebDriverWait wait =getWait(timeOutInSeconds,pollingEveryMilliSec);
        wait.until(ExpectedConditions.visibilityOf(element));
        log.info("element is visible now");
    }


}
