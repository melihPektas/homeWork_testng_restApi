package page;

import org.openqa.selenium.WebDriver;
import util.BasePageUtil;

import static constants.constant.NAV_SEARCH_FIELD;
import static constants.constant.NAV_SEARCH_SUBMIT;

public class HomePage extends BasePageUtil {

    public HomePage(WebDriver driver) {
        super(driver);
    }
    protected static String Value ="iPhone ";


    /**
     * This method is firstly check search bar.
     * Value is send search bar.
     * search icon is check
     * click the search icon
     * List of products
     * check the list of product
     *
     */
    public HomePage search() {
        waitAllRequests();
        assertTrue(isElementPresent(NAV_SEARCH_FIELD), "NAV_SEARCH_FIELD");
        sendKeysBy(NAV_SEARCH_FIELD, Value);
        assertTrue(isElementPresent(NAV_SEARCH_SUBMIT), "NAV_SEARCH_SUBMIT");
        clickOBJECT(NAV_SEARCH_SUBMIT, "NAV_SEARCH_SUBMIT");
        constantControl(Value);
       return new HomePage(driver);
    }


}
