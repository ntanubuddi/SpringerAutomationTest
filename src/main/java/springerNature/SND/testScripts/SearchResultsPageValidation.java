package springerNature.SND.testScripts;

import org.testng.annotations.Test;
import springerNature.SND.helper.browserConfig.config.ObjectReader;
import springerNature.SND.helper.logger.LoggerHelper;
import springerNature.SND.pageObject.LoginPage;
import springerNature.SND.pageObject.SearchPage;
import java.util.logging.Logger;
import springerNature.SND.helper.assertion.AssertionHelper;
import springerNature.SND.testBase.TestBase;
/**
 * @author narasimha
 */
public class SearchResultsPageValidation extends TestBase {
    private final Logger log = LoggerHelper.getLogger(SearchResultsPageValidation.class);

    @Test(description = "Verify Results links and count on search ")
    public void validateResultsPAge() {

        //Springer application is going to launch
        getApplicationUrl(ObjectReader.reader.getUrl());

        //Page objects are created
        LoginPage loginPage = new LoginPage(driver);
        SearchPage searchPage = new SearchPage(driver);

        //User is going to Login

        loginPage.loginToApplication(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());

        //Script is going to perform Search and validate number results count in a page
        searchPage.searchOnHomepage("Physics");
        searchPage.verifyNumberOfResults(20);
        log.info("Expected number of results per page  are displayed ");
        //Update test status
        boolean status = searchPage.verifySuccessResultsPage();
        AssertionHelper.updateTestStatus(status);

    }
}
