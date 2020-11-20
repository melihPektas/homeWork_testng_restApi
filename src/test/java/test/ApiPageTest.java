package test;

import org.testng.annotations.Test;
import restApi.ApiPage;

import static org.apache.log4j.PropertyConfigurator.configure;

public class ApiPageTest {


    @Test
    public void test() {
                          configure("./properties/log4j.properties");

        /**
         *                          testUserAll()
         *                          This method take all user information
         *                          This method is call just url
         */

        ApiPage.testUserAll();

        /**
         *                          testUserPost()
         *                          This method creat the new user.
         *                          Requirement are user name , password and user Id
         *                          User Id is create generate random numeric
         */

        ApiPage.testUserPost("melo test ", "melo Test ");

        /**
         *                          testUserGet()
         *                          This method is take user information
         *                          Requirement is  registered user Id
         */

        ApiPage.testUserGet("9");

        /**
         *                          testUserGetError()
         *                          This method is take error message
         *                          Requirement is  unregistered  user Id
         */

        ApiPage.testUserGetError("35");

    }


}
