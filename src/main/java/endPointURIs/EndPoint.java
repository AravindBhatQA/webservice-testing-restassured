package endPointURIs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseLogSpecification;

public class EndPoint {

    /*
    * Base URL
    * */
    private static final String BASE_URL = "https://restful-booker.herokuapp.com/";

    /*
    * Auth Endpoint URI
    * */
    public static final String CREATE_TOKEN_URI = "auth";
    /*
    * Booking Endpoint URIs
    * */
    public static final String CREATE_BOOKING_URL = "booking";
    public static final String GET_BOOKING_URL = "booking/{id}";
    public static final String GET_ALL_BOOKING_ID_URL = "booking";
    public static final String UPDATE_BOOKING_ID_URL = "booking/{id}";
    public static final String PARTIAL_UPDATE_BOOKING_ID_URL = "booking/{id}";
    public static final String DELETE_BOOKING_ID_URL = "booking/{id}";
    /**/
    public static RequestSpecification specBuilder(){
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .setBaseUri(BASE_URL);
        return builder.build();
    }

}
