package springerNature.SND.pageObject;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import springerNature.SND.helper.assertion.VerificationHelper;
import springerNature.SND.helper.browserConfig.config.ObjectReader;
import springerNature.SND.helper.logger.LoggerHelper;
import springerNature.SND.helper.wait.WaitHelper;
import springerNature.SND.testBase.TestBase;

import java.util.List;
import java.util.logging.Logger;

/**
 * @author narasimha
 */
public class SearchPage {
    private WebDriver driver;
    private final Logger log = LoggerHelper.getLogger(SearchPage.class);

    WaitHelper waitHelper;

    @FindBy(xpath="//*[@id='global-search']")
    WebElement SearchForm;
    @FindBy(xpath="//*[@id='search']")
    WebElement SearchButton;
    @FindBy(xpath="//*[@id='query']")
    WebElement SearchField;
    @FindBy(xpath="//a[contains(text(),'Relevance')]")
    WebElement Relevance;
    @FindBy(xpath="//a[contains(text(),'Newest First')]")
    WebElement NewestFirst;
    @FindBy(xpath="//a[contains(text(),'Oldest First')]")
    WebElement OldestFirst;
    @FindBy(xpath="//*[@class='enumeration']/span")
    WebElement resultDate;

    @FindBy(xpath="//li[@class='no-access has-cover'][1]//a[@class='title']")
    WebElement relavanceResultText;

     @FindBy(xpath="//h1[@id='number-of-search-results-and-search-terms']")
    WebElement NumberOfResults;
    @FindBy(xpath="//a[contains(text(),'The Palgrave Encyclopedia of Imperialism and Anti-')]")
    WebElement ImperialismBook;
    @FindBy(xpath="//h2[contains(text(),'Introduction')]")
    WebElement IntroductionPage;
    @FindBy(xpath="//body/div[@id='wrapper']/div[@id='header']/a[@id='logo']/img[1]")
    WebElement SpringerLink;
    @FindBy(xpath="//a[contains(text(),'Home')]")
    WebElement HomeLink;

    @FindBy(xpath="//div[contains(text(),'Date Published')]")
    WebElement DatePublished;
    @FindBy(xpath=" //input[@id='date-facet-submit']")
    WebElement DateSubmit;

    @FindBy(xpath="//input[@id='start-year']")
    WebElement StartYear;
    @FindBy(xpath="//input[@id='end-year']")
    WebElement EndYear;

    @FindBy(xpath="//p[contains(text(),'Volume 1 / 1973 to Volume 142 / 2021')]")
    WebElement DateSearchDateFilterResult;


    public SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitHelper = new WaitHelper(driver);
        waitHelper.WaitForelementVisible(SearchForm, ObjectReader.reader.getExplicitWait(),10);

        new TestBase().logExtentReport("Search Page Object Created");
    }

    public void filterDatePublished(String startDate, String endDate)
    {
        waitHelper.WaitForelementVisible(DatePublished, ObjectReader.reader.getExplicitWait(),10);
        DatePublished.click();
        log.info("Date published filter is displayed");
        waitHelper.WaitForelementVisible(StartYear, ObjectReader.reader.getExplicitWait(),10);

        StartYear.sendKeys(startDate);
        EndYear.sendKeys(endDate);
        DateSubmit.click();
        Assert.assertTrue(DateSearchDateFilterResult.isDisplayed());

    }

    public void VerifyOldestSort(String oldDate)
    {
        waitHelper.WaitForelementVisible(OldestFirst, ObjectReader.reader.getExplicitWait(),10);
        OldestFirst.click();
       Assert.assertTrue(resultDate.getText().contains(oldDate));
       log.info("Results are sorted by oldest First filet");
    }

    public void VerifyRelavanceSort(String SearchText)
    {
        waitHelper.WaitForelementVisible(Relevance, ObjectReader.reader.getExplicitWait(),10);
        Relevance.click();
        Assert.assertTrue(relavanceResultText.getText().contains(SearchText));
        log.info("Results are sorted by oldest First filet");
    }

    public void VerifyNewestSort(String newestDate)
    {
        waitHelper.WaitForelementVisible(NewestFirst, ObjectReader.reader.getExplicitWait(),10);
        NewestFirst.click();
        Assert.assertTrue(resultDate.getText().contains(newestDate));
        log.info("Results are sorted by Newest date ");
    }
    public void enterSearchKeyword(String s1){
        log.info("Enter Search Word...");
        logExtentReport("Enter Search Word...");
        SearchField.sendKeys(s1);

    }
    public int fetchResultLinks()
    {
        waitHelper.WaitForelementVisible(NumberOfResults, ObjectReader.reader.getExplicitWait(),10);
        List<WebElement> resultLinks  = driver.findElements(By.xpath("(//*[@id='results-list']/li)"));
    return resultLinks.size();
    }

    public void verifyNumberOfResults(int count)
    {
        log.info("Count on results page"+fetchResultLinks());
     Assert.assertEquals(fetchResultLinks(),count);
    }
    public void clickSearchButton(){
        log.info("click on search button...");
        logExtentReport("click on search button");
        SearchButton.click();
        waitHelper.WaitForelementVisible(NumberOfResults, ObjectReader.reader.getExplicitWait(),10);

    }

    public void searchOnHomepage(String searchWord)
    {
        enterSearchKeyword(searchWord);
        clickSearchButton();


    }
    public void verifyIntroductionPage()
    {

        ImperialismBook.click();
        IntroductionPage.isDisplayed();
        log.info("introduction page is displayed...");
        logExtentReport("introduction page is displayed...");
    }

    public void verifyResultsText(String expectedResultsText)
    {
        NumberOfResults.isDisplayed();
        NumberOfResults.getText();
        log.info("Number of results.."+NumberOfResults.getText());
        Assert.assertTrue(NumberOfResults.getText().contains(expectedResultsText));
        log.info("results page is displayed...");
        logExtentReport("results page is displayed...");
    }

    public void clickFirstLink(String searchWord)
    {
        enterSearchKeyword(searchWord);
        ImperialismBook.click();
    }
    public boolean verifySuccessLoginMsg(){
        return new VerificationHelper(driver).isDisplayed(IntroductionPage);

    }

    public boolean verifySuccessResultsPage(){
        return new VerificationHelper(driver).isDisplayed(NumberOfResults);

    }
    public String verifyPageTitle(){
        return new VerificationHelper(driver).getTitle();

    }
    public void verifySearchPageTitle(String searchPageTitle)
    {
        log.info("Page title is "+verifyPageTitle());
        logExtentReport("Page title is "+verifyPageTitle());
        Assert.assertEquals(verifyPageTitle(),searchPageTitle);
    }
    public void logExtentReport(String s1){
        TestBase.test.log(Status.INFO, s1);
    }



}
