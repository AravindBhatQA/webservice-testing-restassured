package methods.Booker;

import POJO.Booker.CreateBookingPayload;
import endPointURIs.EndPoint;
import io.restassured.response.Response;

import java.io.IOException;

import static io.restassured.RestAssured.*;

public class BookerMethods {

    public static Response createBooking(CreateBookingPayload bookingDetail){
        return given()
                .spec(EndPoint.specBuilder())
                .contentType("application/json")
                .accept("application/json")
                .body(bookingDetail)
                .when()
                .post(EndPoint.CREATE_BOOKING_URL);
    }

    public static Response getBookingDetails(String id){
        return given()
                .spec(EndPoint.specBuilder())
                .accept("application/json")
                .pathParam("id", id)
                .when()
                .get(EndPoint.GET_BOOKING_URL);
    }

    public static Response updateBooking(CreateBookingPayload payload, String id, String token) throws IOException {
        return given()
                .spec(EndPoint.specBuilder())
                .contentType("application/json")
                .accept("application/json")
                .cookie("token", token)
                .pathParam("id", id)
                .body(payload)
                .when()
                .put(EndPoint.UPDATE_BOOKING_ID_URL);
    }


    public static Response deleteUser(int id){
        return  given()
                .spec(EndPoint.specBuilder())
                .contentType("application/json")
                .header("Authorization", "Basic") //Need to add token
                .pathParam("id", id)
                .when()
                .delete(EndPoint.DELETE_BOOKING_ID_URL);
    }

}
