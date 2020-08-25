package List.Tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.*;

import static List.Tests.list_datafactory.request_list;
import static Utils.utils.*;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Test_ListManipulation {

    private static String list_id;
    Response response;

    @Test
    public void a_postList_create_list() {
        //given
        response = request_list();

        //when
        System.out.println(response.statusCode());
        System.out.println(response.body().asString());
        list_id = response.jsonPath().getString("id");
        System.out.println(list_id);

        //then
        assertTrue("Response success was not true", response.jsonPath().getBoolean("success"));
        assertTrue("Request status code was not 201", response.statusCode() == 201);
    }



    @Test
    public void b_putList_update_list() {
        //given
        Response response = given()
                .header(HEADER_AUTH, VALUE_AUTH)
                .header(HEADER_CONTENT_TYPE, ContentType.JSON)
                .body(updateListBody())
                .put(BASEURL + LIST_ENDPOINT + "/" + list_id);

        //when
        System.out.println(response.statusCode());
        System.out.println(response.body().asString());

        //then
        assertTrue("Response success was not true", response.jsonPath().getBoolean("success"));
        assertTrue("Request status code was not 201", response.statusCode() == 201);

    }

    private Map<String,String> updateListBody() {
        HashMap<String,String> body = new HashMap<>();
        body.put(PARAM_NAME, "HW Updated List rest asured");
        body.put(PARAM_DESCRIPTION, VALUE_DESCRIPTION);
        return body;
    }

    @Test
    public void c_postList_add_list_items() {
        //given
        Response response = given()
                .header(HEADER_AUTH, VALUE_AUTH)
                .header(HEADER_CONTENT_TYPE, ContentType.JSON)
                .body(addListItemBody())
                .put(BASEURL + LIST_ENDPOINT + "/" + "7056135" + ITEMS_ENDPOINT);

        //when
        System.out.println(response.statusCode());
        System.out.println(response.body().asString());

        //then
        //assertTrue("Response success was not true", response.jsonPath().getBoolean("success"));
        //assertTrue("Request status code was not 201", response.statusCode() == 201);

    }

    private Map<String,Object> addListItemBody() {
        HashMap<String,Object> body = new HashMap<>();
        body.put(PARAM_ITEMS, addListItems());
        return body;
    }

    private List<Object> addListItems() {
        LinkedList<Object> items = new LinkedList<>();
        items.add(getItem(VALUE_MOVIE_ONE));
        items.add(getItem(VALUE_MOVIE_TWO));
        items.add(getItem(VALUE_MOVIE_THREE));
        return items;
    }

    private Map<String,Object> getItem(int mediaID) {
        HashMap<String,Object> item = new HashMap<>();
        item.put(PARAM_MEDIA_TYPE, VALUE_MEDIA_TYPE);
        item.put(PARAM_MEDIA_ID, mediaID);
        return item;
    }

}
