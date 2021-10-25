package springerNature.SND.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import springerNature.SND.helper.browserConfig.config.ObjectReader;
import springerNature.SND.helper.logger.LoggerHelper;
import springerNature.SND.helper.wait.WaitHelper;
import springerNature.SND.testBase.TestBase;

import java.util.logging.Logger;

/**
 * @author narasimha
 */
public class NavigationMenu {


    private WebDriver driver;
    private final Logger log = LoggerHelper.getLogger(NavigationMenu.class);
    WaitHelper waitHelper;

    @FindBy(xpath="//body/div[@id='wrapper']/div[@id='header']/a[@id='logo']/img[1]")
    public WebElement SpringerLink;



    public NavigationMenu(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitHelper = new WaitHelper(driver);
        waitHelper.WaitForelementVisible(SpringerLink, ObjectReader.reader.getExplicitWait(),10);
        TestBase.logExtentReport("NavigationMenu object created");

    }
}
