package springerNature.SND.pageObject;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import springerNature.SND.helper.assertion.VerificationHelper;
import springerNature.SND.helper.browserConfig.config.ObjectReader;
import springerNature.SND.helper.logger.LoggerHelper;
import springerNature.SND.helper.wait.WaitHelper;
import springerNature.SND.testBase.TestBase;
import  springerNature.SND.helper.wait.WaitHelper;
import java.util.logging.Logger;

/**
 * @author narasimha
 */
public class LoginPage {

    private WebDriver driver;
    private final Logger log = LoggerHelper.getLogger(LoginPage.class);

    WaitHelper waitHelper;

    @FindBy(xpath="//*[@id='onetrust-accept-btn-handler']")
    WebElement AccpetCookies;
    @FindBy(xpath="//*[@class='auth flyout']/a")
    WebElement LoginLink;
    @FindBy(xpath="//input[@id='login-box-email']")
    WebElement Email;
    @FindBy(xpath="//input[@id='login-box-pw']")
    WebElement Pass;
    @FindBy(xpath="//button[contains(text(),'Log in')]")
    WebElement LoginButton;

    @FindBy(xpath="//body/div[@id='wrapper']/div[@id='header']/div[1]/div[1]/button[1]/span[1]")
    WebElement successMsgObject;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitHelper = new WaitHelper(driver);
        waitHelper.WaitForelementVisible(LoginLink, ObjectReader.reader.getExplicitWait(),10);

        new TestBase().logExtentReport("Login Page Object Created");
    }

    public void clickOnSignInLink(){
        log.info("clicked on sign in link...");
        logExtentReport("clicked on sign in link...");
        LoginLink.click();
    }
    public void acceptCookies(){
        log.info("Click on accept cookies...");
        waitHelper.WaitForelementVisible(AccpetCookies, ObjectReader.reader.getExplicitWait(),10);
        logExtentReport("Click on accept cookies");
        AccpetCookies.click();
    }

    public void enterEmailAddress(String emailAddress){
        waitHelper.WaitForelementVisible(Email, ObjectReader.reader.getExplicitWait(),10);
        log.info("entering email address...."+emailAddress);
        logExtentReport("entering email address...."+emailAddress);
        this.Email.sendKeys(emailAddress);
    }

    public void enterPassword(String password){
        log.info("entering password...."+password);
        logExtentReport("entering password...."+password);
        this.Pass.sendKeys(password);
    }


    public SearchPage loginToApplication(String emailAddress, String password){
        acceptCookies();
        clickOnSignInLink();
        enterEmailAddress(emailAddress);
        enterPassword(password);
        clickOnSubmitButton();
        return new SearchPage(driver);
    }

    /*public void logout(){
        logout.click();
        log.info("clicked on logout link");
        waitHelper.waitForElement(signin, ObjectReader.reader.getExplicitWait());
    }*/

    public boolean verifySuccessLoginMsg(){
        return new VerificationHelper(driver).isDisplayed(successMsgObject);

    }


    public NavigationMenu clickOnSubmitButton(){
        log.info("clicking on submit button...");
        logExtentReport("clicking on submit button...");

        LoginButton.click();
        return new NavigationMenu(driver);
    }
    public void logExtentReport(String s1){
        TestBase.test.log(Status.INFO, s1);
    }
}
