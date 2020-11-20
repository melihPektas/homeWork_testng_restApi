package restApi;


import org.apache.http.ParseException;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;


public class BaseHttpClientUtil extends BaseHttpClient {
    protected static final Logger log = Logger.getLogger(BaseHttpClientUtil.class);


    public static JSONParser parser = new JSONParser();

    private static String id;
    private static String password;
    private static String userName;
    public static String idJson;



    public static void responseJsonData(BaseResponse response, String message, String errorMessage, JSONArray jsonArray) {
        if (response.getCode() == 200) {
            reportRequestTime(response, message);
            jsonArray = getJsonArray(response, jsonArray, 200);
            if (jsonArray != null) {
                // System.out.println(jsonArray);
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject jsonObject_0 = (JSONObject) jsonArray.get(i);
                    idJson = jsonObject_0.get("id").toString();
                    password = jsonObject_0.get("password").toString();
                    userName = jsonObject_0.get("userName").toString();
                    log.info(idJson + "-----id-------->" + userName + "------userName------>" + password + "------password------>" + "testUserAll");
                }
            }
        } else {
            reportRequestTimeError(response, errorMessage);
            Assert.fail(errorMessage + "is failed!");
        }
    }

    public static void responseJsonZ(BaseResponse response, String message, String errorMessage) {
        if (response.getCode() == 200) {
            reportRequestTime(response, message);
            JSONObject jsonObject = getJsonObject(response);
            if (jsonObject != null) {
                if (jsonObject.size() > 0) {
                    id = jsonObject.get("id").toString();
                    password = jsonObject.get("password").toString();
                    userName = jsonObject.get("userName").toString();
                    log.info(id + "-------id------->" + userName + "-------userName------->" + password + "------password------>" + "testUserGet");
                }
            }
        } else {
            reportRequestTimeError(response, errorMessage);
            Assert.fail(errorMessage + "is failed!");
        }
    }

    public static void responseError(BaseResponse response, String message, String errorMessage) {
        if (response.getCode() > 200) {
            reportRequestTime(response, message);
            JSONObject jsonObject = getJsonObjectError(response);
            if (jsonObject != null) {
                if (jsonObject.size() > 0) {
                    String title = jsonObject.get("title").toString();
                    String status = jsonObject.get("status").toString();
                    log.info("------title-------->" + title + "-------status------->" + status + "     " + "testUserGet");
                }
            }
        } else {
            reportRequestTimeError(response, errorMessage);
            Assert.fail(errorMessage + "is failed!");
        }
    }

    public static void responsePost(BaseResponse response, String message, String errorMessage) {
        if (response.getCode() == 200) {
            reportRequestTime(response, message);
            JSONObject jsonObject = getJsonObject(response);
            if (jsonObject != null) {
                if (jsonObject.size() > 0) {
                    id = jsonObject.get("id").toString();
                    password = jsonObject.get("password").toString();
                    userName = jsonObject.get("userName").toString();
                    log.info(id + "------id------->" + userName + "-------userName------->" + password + "------password------>" + "responsePost");

                }
            }
        } else {
            reportRequestTimeError(response, errorMessage);
            Assert.fail(errorMessage + "is failed!");
        }
    }


    public static void reportRequestTime(BaseResponse response, String message) {
        log.info(message);
    }


    public static void reportRequestTimeError(BaseResponse response, String message) {
        log.error("ERROR" + "  " + message + "   Code  " + " " + response + " " + response.getBody());
    }


    private static JSONObject getJsonObject(BaseResponse response) throws ParseException {
        JSONObject json = null;
        json = getJsonObject(response, json, 200);
        assert json != null;
        return json;
    }

    private static JSONObject getJsonObjectError(BaseResponse response) throws ParseException {
        JSONObject json = null;
        json = getJsonObject(response, json, 404);
        assert json != null;
        return json;
    }

    private static JSONObject getJsonObject(BaseResponse response, JSONObject jsonObject, int i) throws ParseException {
        String responseBodyString = response.getBody();
        try {
            if (response.getCode() == i && responseBodyString != null) {
                jsonObject = (JSONObject) BaseHttpClientUtil.parser.parse(responseBodyString);
            } else {
                log.error(response.getCode());
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        assert jsonObject != null;
        return jsonObject;
    }



    private static JSONArray getJsonArray(BaseResponse response, JSONArray jsonArray, int i) throws ParseException {
        if (response.getCode() == i && response.getBody() != null) {
            try {
                jsonArray = (JSONArray) BaseHttpClientUtil.parser.parse(response.getBody());
            } catch (org.json.simple.parser.ParseException e) {
                e.printStackTrace();
            }
        }
        assert jsonArray != null;
        return jsonArray;
    }



}
