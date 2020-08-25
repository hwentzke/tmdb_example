package Utils;

public class utils {

    public static final String API_TOKEN = "5f003d24d316f94e86ae43cac0e7927f";
    public static final String ACCESS_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYmYiOjE1OTgyODEwOTgsInN1YiI6IjVmNDNjYjRjNDE0MjkxMDAzODRmZGMwMCIsImp0aSI6IjIzMzAzNDEiLCJhdWQiOiI1ZjAwM2QyNGQzMTZmOTRlODZhZTQzY2FjMGU3OTI3ZiIsInNjb3BlcyI6WyJhcGlfcmVhZCIsImFwaV93cml0ZSJdLCJ2ZXJzaW9uIjoxfQ.pFk_b-pOU4yLgeOroSbk1se0oYxVU5-5dUAgTZJbOj4";

    public static final String BASEURL = "https://api.themoviedb.org/4";
    public static final String LIST_ENDPOINT = "/list";
    public static final String ITEMS_ENDPOINT = "/items";

    public static final String HEADER_AUTH = "Authorization";
    public static final String VALUE_AUTH = "Bearer " + ACCESS_TOKEN;
    public static final String HEADER_CONTENT_TYPE = "Content-Type";

    public static final String PARAM_NAME = "name";
    public static final String VALUE_NAME = "HW Test List rest assured";

    public static final String PARAM_LANG = "iso_639_1";
    public static final String VALUE_LANG = "en";

    public static final String PARAM_DESCRIPTION = "description";
    public static final String VALUE_DESCRIPTION = "This list was made with rest assured!";

    public static final String PARAM_ITEMS = "items";
    public static final String PARAM_MEDIA_TYPE = "media_type";
    public static final String VALUE_MEDIA_TYPE = "movie";
    public static final String PARAM_MEDIA_ID = "media_id";
    public static final Integer VALUE_MOVIE_ONE = 106;      //Predator
    public static final Integer VALUE_MOVIE_TWO = 348;      //Alien
    public static final Integer VALUE_MOVIE_THREE = 679;    //Aliens

}
