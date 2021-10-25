package springerNature.SND.helper.logger;

import org.apache.log4j.PropertyConfigurator;
import springerNature.SND.helper.resource.ResourceHelper;

import java.util.logging.Logger;

/**
 * @author narasimha
 */
public class LoggerHelper {
    private static boolean root=false;
    public static Logger getLogger(Class cls){
        if(root){
            return Logger.getLogger(String.valueOf(cls));
        }
        PropertyConfigurator.configure(ResourceHelper.getResourcePath("/src/main/resources/configFile/log4j.properties"));

        root=true;
        return Logger.getLogger(String.valueOf(cls));
    }

    public static void main(String[] args) {
        Logger log =LoggerHelper.getLogger(LoggerHelper.class);
        log.info("i am ready");
    }
}
