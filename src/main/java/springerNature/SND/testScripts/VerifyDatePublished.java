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
public class VerifyDatePublished extends TestBase {
    private final Logger log = LoggerHelper.getLogger(VerifyDatePublished.class);

    @Test(description = "Verify Date published  filter in search results page ")
    public void filterWithDatePublished() {

        //Springer application is going to launch
        getApplicationUrl(ObjectReader.reader.getUrl());

        //Page objects are created
        LoginPage loginPage = new LoginPage(driver);
        SearchPage searchPage = new SearchPage(driver);

        //User is going to Log in
        loginPage.loginToApplication(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());

        //Script is going to perform Search and validate number results count in a page
        searchPage.searchOnHomepage("Physics");

        //Search results will be filtered based on the date published and filtered results will be verified
        searchPage.filterDatePublished("2021","2022");

        boolean status = searchPage.verifySuccessResultsPage();
        AssertionHelper.updateTestStatus(status);


    }
}
