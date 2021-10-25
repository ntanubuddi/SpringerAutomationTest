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
 */
public class SortSearchResults extends TestBase {
    private final Logger log = LoggerHelper.getLogger(SortSearchResults.class);

    @Test(description = "Sort Search Results using sort by filters")
    public void sortSearchResults() {

        //Springer application is going to launch
        getApplicationUrl(ObjectReader.reader.getUrl());

        //Page objects are created
        LoginPage loginPage = new LoginPage(driver);
        SearchPage searchPage = new SearchPage(driver);

        //User is going to Log in
        loginPage.loginToApplication(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());

        //Script is going to perform Search and validate number results count in a page
        searchPage.searchOnHomepage("Physics");

        //Sort the results with The Oldest Filter and verify the sorted results
        searchPage.VerifyOldestSort("1832");
        log.info("Results are sorted with oldest date");

        //Sort the results with The Newest filter and verify the sorted results
        searchPage.VerifyNewestSort("2022");
        log.info("Results are sorted with newest date");

        //Sort the results with relevance filter  and verify the sorted results
        searchPage.VerifyRelavanceSort("Physics");
        log.info("Results are sorted with relevance ");

        boolean status = searchPage.verifySuccessResultsPage();
        AssertionHelper.updateTestStatus(status);

    }
}