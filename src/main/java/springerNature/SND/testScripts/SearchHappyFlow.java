package springerNature.SND.testScripts;

import org.testng.annotations.Test;
import springerNature.SND.helper.assertion.AssertionHelper;
import springerNature.SND.helper.browserConfig.config.ObjectReader;
import springerNature.SND.helper.logger.LoggerHelper;
import springerNature.SND.pageObject.LoginPage;
import springerNature.SND.pageObject.SearchPage;
import springerNature.SND.testBase.TestBase;

import java.util.logging.Logger;

/**
 * @author narasimha
 * This test is to verify the search functionality
 */
public class SearchHappyFlow extends TestBase {
    private final Logger log = LoggerHelper.getLogger(SearchHappyFlow.class);

    @Test(description="Search on home page with a valid user")
    public void testLoginToApplicationAndSearch(){

        //Springer application is going to launch
        getApplicationUrl(ObjectReader.reader.getUrl());

        //Page objects are created
        LoginPage loginPage = new LoginPage(driver);
        SearchPage searchPage = new SearchPage(driver);

        //User is going to Login
        loginPage.loginToApplication(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());
        log.info("User is looged in");

        //Verify Page title
        searchPage.verifySearchPageTitle("Home - Springer");
        log.info("User is on home page");

        //Script is going to perform Search and open a link from search results and Navigate to IntroductionPage
        searchPage.searchOnHomepage("Law");

        //Verify Search Results page
        searchPage.verifyResultsText("Result(s) for");
        log.info("Search Results are displayed");

        //Script is going to click on ImperialismBook from the above search results and verify introduction page.
        searchPage.verifyIntroductionPage();
        log.info("Introduction is displayed");

        //Update test status
        boolean status = searchPage.verifySuccessLoginMsg();
        AssertionHelper.updateTestStatus(status);
    }
}
