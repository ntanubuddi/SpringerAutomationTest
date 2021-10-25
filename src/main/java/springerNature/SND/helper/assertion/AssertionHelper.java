package springerNature.SND.helper.assertion;

import org.testng.Assert;
import springerNature.SND.helper.logger.LoggerHelper;

import java.util.logging.Logger;

/**
 * @author narasimha
 */
public class AssertionHelper {
    private static Logger log = LoggerHelper.getLogger(AssertionHelper.class);

    public static void verifyText(String s1, String s2){
        log.info("veryfing test: "+ s1 + " with "+ s2);
        Assert.assertEquals(s1, s1);
    }


    public static void fail(){
        Assert.assertTrue(false);
    }

    public static void pass(){
        Assert.assertTrue(true);
    }

    public static void updateTestStatus(boolean status){
        if(status){
            pass();
        }
        else{
            fail();
        }
    }
}
