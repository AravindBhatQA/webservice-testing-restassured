package utils;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class ValidatorUtils {
    public static void validateStatusCode(@NotNull Response resp, int statusCode){
        resp.then().statusCode(statusCode);
    }

    public static void validateResponseSchema(@NotNull Response resp, String fileName){
//        src/main/java/Schemas/User/Post_Booking_Creation_Positive.json
        resp.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File(System.getProperty("user.dir") + "/src/main/java/Schemas/" + fileName)));
    }
}
