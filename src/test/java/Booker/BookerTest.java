package Booker;

import POJO.Booker.CreateBookingPayload;
import io.restassured.response.Response;

import org.jetbrains.annotations.NotNull;
import org.testng.Assert;
import org.testng.annotations.Test;

import methods.Booker.BookerMethods;
import utils.CommonUtils;
import utils.ValidatorUtils;
import utils.specific.booker.BookerUtils;

import static methods.Booker.BookerMethods.*;
import static utils.ValidatorUtils.*;
import static utils.specific.booker.BookerUtils.*;

import java.io.IOException;

public class BookerTest {

    @Test(priority = 1)
    public void validate_BookingCreation_Where_CheckIn_Is_Less_Than_CheckOut() throws IOException {
        CreateBookingPayload payload = BookerUtils.createBookingPayloadWithCheckInLessThanCheckOut();
        Response postResponse = BookerMethods.createBooking(payload);
        ValidatorUtils.validateStatusCode(postResponse, 200);
        ValidatorUtils.validateResponseSchema(postResponse, "Booker/Post_Booking_Creation_Positive.json");

        String bookingId = BookerUtils.getBookingId(postResponse);
        BookerUtils.addBookingIdInConfig(bookingId);
        Response getNewlyAddedBookingResponse = BookerMethods.getBookingDetails(bookingId);
        ValidatorUtils.validateStatusCode(getNewlyAddedBookingResponse, 200);
        ValidatorUtils.validateResponseSchema(getNewlyAddedBookingResponse, "Booker/Get_Single_Booking_Detail_Positive.json");
    }

    @Test
    public void validate_BookingCreation_When_Checking_Is_Greater_Than_CheckOut(){
        CreateBookingPayload payload = createBookingPayloadWithCheckInMoreThanCheckOut();
        Response postResponse = createBooking(payload);
        validateStatusCode(postResponse, 404);
    }

    @Test(priority = 2, dependsOnMethods = "validate_BookingCreation_Where_CheckIn_Is_Less_Than_CheckOut")
    public void validate_Booking_Updation() throws IOException {
        CreateBookingPayload bookingPayload = createBookingPayloadWithCheckInLessThanCheckOut();
        Response updateResponse = updateBooking(bookingPayload, getBookingIdFromConfig(), CommonUtils.getToken());

        validateStatusCode(updateResponse, 200);
        validateResponseSchema(updateResponse, "Booker/Put_Update_Booking_Detail_Positive.json");
    }

    @Test(priority = 3)
    public void updateBookingNegativeScenario() throws IOException {
        @NotNull CreateBookingPayload bookingPayload = createBookingPayloadWithCheckInLessThanCheckOut();
        String incorrectBookingId = generateIncorrectBookingId();

        //Validate that bookingId is indeed incorrect
        Response getBookingDetailResponse = getBookingDetails(incorrectBookingId);
        validateStatusCode(getBookingDetailResponse, 404);

        //Validate user recieves 405 status code when incorrect booking id is used for updation
        Response getUpdatedBookingResponse = updateBooking(bookingPayload, incorrectBookingId, CommonUtils.getToken());
        validateStatusCode(getUpdatedBookingResponse, 405);

        String incorrectToken = CommonUtils.generateIncorrectToken();

        //Validate that "incorrectToken" is indded incorrect
        Assert.assertNotEquals(incorrectToken, CommonUtils.getToken());

        //Validate when incorrect token is sent, than user recieves 403 error
        getUpdatedBookingResponse = updateBooking(bookingPayload, getBookingIdFromConfig(), incorrectToken);
        validateStatusCode(getUpdatedBookingResponse, 403);
    }




}
