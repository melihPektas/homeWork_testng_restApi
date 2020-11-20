package restApi;

import okhttp3.MediaType;
import okhttp3.RequestBody;

import static restApi.BaseHttpClient.HttpClient;
import static restApi.BaseHttpClient.sendPostRequest;
import static util.BasePageUtil.generateRandomNumericString;


public class Api {


    public static BaseResponse userAll() {
        return HttpClient("http://fakerestapi.azurewebsites.net/api/v1/Users");
    }

    public static BaseResponse userGet(String id) {
        return HttpClient("http://fakerestapi.azurewebsites.net/api/v1/Users/" + id);
    }

    public static BaseResponse userPost(String userName, String password) {
        MediaType mediaType = MediaType.parse("application/json; v=1.0");
        RequestBody body = RequestBody.create(mediaType, "{\n    \"id\": \"" + generateRandomNumericString(2) + "\",\n    \"userName\": \"" + userName + "\",\n    \"password\": \"" + password + "\"\n}");
        return sendPostRequest("http://fakerestapi.azurewebsites.net/api/v1/Users", body);
    }


}
