package springerNature.SND.helper.browserConfig;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import springerNature.SND.helper.resource.ResourceHelper;

/**
 * @author narasimha
 */


    public class ChromeBrowser {

        public ChromeOptions getChromeOptions() {

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--test-type");
            options.addArguments("--disable-popup-blocking");


            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);
            options.merge(capabilities);

            return options;
        }

        public WebDriver getChromeDriver(ChromeOptions cap) {


                System.setProperty("webdriver.chrome.driver", ResourceHelper.getResourcePath("/src/main/resources/drivers/chromedriver"));
                return new ChromeDriver(cap);



        }

        public static void main(String[] args) {
            ChromeBrowser obj = new ChromeBrowser();
            WebDriver driver = obj.getChromeDriver(obj.getChromeOptions());
            driver.get("https://link.springer.com/");
        }

    }


