package List.Tests;

import io.restassured.response.Response;
import org.junit.*;
import org.junit.runners.MethodSorters;

import static List.Tests.list_datafactory.request_list;
import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Test_ListManipulation {

    @Before
    public void setup() {
        Response response = request_list("CREATE");
        System.out.println(response.statusCode());
        System.out.println(response.body().asString());
    }

    /*
    @BeforeClass
    public static void setup() {
        Response response = request_list("CREATE");
        System.out.println(response.statusCode());
        System.out.println(response.body().asString());
    }

     */

    @Test
    public void b_putList_update_list() {
        //given
        Response response = request_list("UPDATE");

        //when
        System.out.println(response.statusCode());
        System.out.println(response.body().asString());

        //then
        assertTrue("Response success was not true", response.jsonPath().getBoolean("success"));
        assertTrue("Request status code was not 201", response.statusCode() == 201);

    }

    @Test
    public void c_postList_add_list_items() {
        //given
        Response response = request_list("ADD_ITEMS");

        //when
        System.out.println(response.statusCode());
        System.out.println(response.body().asString());

        //then
        assertTrue("Response success was not true", response.jsonPath().getBoolean("success"));
        assertTrue("Request status code was not 201", response.statusCode() == 200);
    }

    @Test
    public void d_getList_clear_list() {
        //given
        Response response = request_list("CLEAR");

        //when
        System.out.println(response.statusCode());
        System.out.println(response.body().asString());

        //then
        assertTrue("Response success was not true", response.jsonPath().getBoolean("success"));
        assertTrue("Request status code was not 201", response.statusCode() == 200);
    }

    @After
    public void tearDown() {
        request_list("DELETE");
    }

    /*
    @AfterClass
    public static void tearDown() {
        request_list("DELETE");
    }

     */

}
