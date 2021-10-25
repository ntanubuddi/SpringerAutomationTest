package springerNature.SND.helper.browserConfig.config;

import springerNature.SND.helper.browserConfig.Browser;

/**
 * @author narasimha
 */
public  interface  ConfigReader {

    public int getImpliciteWait();
    public int getExplicitWait();
    public  int  getPageLoadTime ();
    public Browser getBrowserType();
    public  String  getUrl ();
    public  String  getUserName ();
    public String getPassword();


}
