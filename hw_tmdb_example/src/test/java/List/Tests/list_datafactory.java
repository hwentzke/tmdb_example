package List.Tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static Utils.utils.*;
import static io.restassured.RestAssured.given;

public class list_datafactory {

    public static Response request_list() {
        return given()
                .header(HEADER_AUTH, VALUE_AUTH)
                .header(HEADER_CONTENT_TYPE, ContentType.JSON)
                .body(createListBody())
                .post(BASEURL + LIST_ENDPOINT);
    }

    private static Map<String,String> createListBody() {
        HashMap<String,String> body = new HashMap<>();
        body.put(PARAM_NAME, VALUE_NAME);
        body.put(PARAM_LANG, VALUE_LANG);
        return body;
    }

}
