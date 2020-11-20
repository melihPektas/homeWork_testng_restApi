package restApi;

import org.json.simple.JSONArray;

import static restApi.Api.*;

public class ApiPage {


    private static JSONArray jsonArray;

    public static void testUserAll() {
        BaseHttpClientUtil.responseJsonData(userAll(), "Test userAll", "Test userAll", jsonArray);
    }

    public static void testUserGet(String id) {
        BaseHttpClientUtil.responseJsonZ(userGet(id), "Test userGet", "Test userGet");
    }

    public static void testUserPost(String userName, String password) {
        BaseHttpClientUtil.responsePost(userPost(userName, password), "Test userPost", "Test userPost");
    }

    public static void testUserGetError(String id) {
        BaseHttpClientUtil.responseError(userGet(id), "Test user Get Error", "Test user Get Error");
    }
}
