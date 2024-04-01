package utils.specific.booker;

import POJO.Booker.BookingDatesPayload;
import POJO.Booker.CreateBookingPayload;
import io.restassured.response.Response;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import utils.CommonUtils;

import java.io.IOException;
import java.util.Date;

import java.util.concurrent.TimeUnit;

public class BookerUtils {

    public static @NotNull CreateBookingPayload createBookingPayloadWithCheckInLessThanCheckOut(){

        return createBookingPayload(CommonUtils.faker.date().past(90, TimeUnit.DAYS),
                CommonUtils.faker.date().future(90, TimeUnit.DAYS));
    }

    public static @NotNull CreateBookingPayload createBookingPayloadWithCheckInMoreThanCheckOut(){

        return createBookingPayload(CommonUtils.faker.date().future(90, TimeUnit.DAYS),
                CommonUtils.faker.date().past(90, TimeUnit.DAYS)
                );
    }

    @Contract("_, _ -> new")
    private static @NotNull CreateBookingPayload createBookingPayload(Date checkIn, Date checOut){
        return new CreateBookingPayload(
                CommonUtils.faker.name().firstName(),
                CommonUtils.faker.name().lastName(),
                CommonUtils.faker.number().randomDigitNotZero(),
                CommonUtils.faker.bool().bool(),
                new BookingDatesPayload(
                        CommonUtils.formatDate(checkIn),
                        CommonUtils.formatDate(checOut)
                ),
                CommonUtils.faker.food().dish()
        );
    }

    public static String getBookingId(@NotNull Response resp){
        return resp.body().jsonPath().getInt("bookingid") + "";
    }

    public static void addBookingIdInConfig(String id) throws IOException {
        CommonUtils.writeInConfigProperties("BookingId", id);
    }
    public static String getBookingIdFromConfig() throws IOException {
        return CommonUtils.getConfigProperties().getProperty("BookingId");
    }
    public static String generateIncorrectBookingId(){
        return CommonUtils.faker.name().firstName();
    }

}
