package test;

import base.BaseTest;
import org.testng.annotations.Test;
import page.HomePage;

public class HomePageTest extends BaseTest {

    @Test
    public void HomePageTest(){
        new HomePage(driver).search();
    }
}
