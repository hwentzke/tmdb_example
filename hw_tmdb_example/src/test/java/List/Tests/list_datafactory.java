package List.Tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static Utils.utils.*;
import static io.restassured.RestAssured.given;

public class list_datafactory {

    public static String list_id;

    public static Response request_list(String action, int ...id) {
        String url = "";
        Map<String,Object> body = null;
        switch (action) {
            case "CREATE":
                url = BASEURL;
                body = createListBody();
                break;
            case "UPDATE":
                url = BASEURL + list_id;
                body = updateListBody();
                break;
            case "ADD_ITEMS":
                url = BASEURL + list_id + ITEMS_ENDPOINT;
                body = addListItemBody();
                break;
            case "CHECK_ITEMS":
                url = BASEURL + list_id + ITEM_STATUS_ENDPOINT + ITEM_STATUS_URL_ENDING + id;
                break;
            case "CLEAR":
                url = BASEURL + list_id + CLEAR_LIST_ENDPOINT;
                break;
            case "DELETE":
                url = BASEURL + list_id;
        }

        RequestSpecification specs = given()
                .header(HEADER_AUTH, VALUE_AUTH)
                .header(HEADER_CONTENT_TYPE, ContentType.JSON);


        switch (action) {
            case "CREATE":
                Response response = specs.body(body).post(url);
                list_id = "/" + response.jsonPath().getString("id");
                return response;
            case "UPDATE":
                return specs.body(body).put(url);
            case "ADD_ITEMS":
                return specs.body(body).post(url);
            case "CHECK_ITEMS":
                return specs.get(url);
            case "CLEAR":
                //TODO investigate why only one eval is executed
                if (isItemNotOnList(VALUE_MOVIE_ONE) && isItemNotOnList(VALUE_MOVIE_TWO) && isItemNotOnList(VALUE_MOVIE_THREE)) {
                    request_list("ADD_ITEMS");
                    System.out.println("Items have been added");
                }
                return specs.get(url);
            default:
                return specs.delete(url);
        }

    }



    private static Map<String,Object> createListBody() {
        HashMap<String,Object> body = new HashMap<>();
        body.put(PARAM_NAME, VALUE_NAME);
        body.put(PARAM_LANG, VALUE_LANG);
        return body;
    }

    private static Map<String,Object> updateListBody() {
        HashMap<String,Object> body = new HashMap<>();
        body.put(PARAM_NAME, "HW Updated List rest asured");
        body.put(PARAM_DESCRIPTION, VALUE_DESCRIPTION);
        return body;
    }

    private static HashMap<String,Object> addListItemBody() {
        HashMap<String,Object> body = new HashMap<>();
        body.put(PARAM_ITEMS, addListItems());
        return body;
    }

    private static List<Object> addListItems() {
        LinkedList<Object> items = new LinkedList<>();
        items.add(getItem(VALUE_MOVIE_ONE));
        items.add(getItem(VALUE_MOVIE_TWO));
        items.add(getItem(VALUE_MOVIE_THREE));
        return items;
    }

    private static Map<String,Object> getItem(int mediaID) {
        HashMap<String,Object> item = new HashMap<>();
        item.put(PARAM_MEDIA_TYPE, VALUE_MEDIA_TYPE);
        item.put(PARAM_MEDIA_ID, mediaID);
        return item;
    }

    private static boolean isItemNotOnList(int id) {
        Response response = request_list("CHECK_ITEMS", id);
        boolean test = response.jsonPath().getInt("status_code") == 1;
        System.out.println("is item on list: " + test);
        return test;
    }


}
