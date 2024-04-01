package utils.specific.Auth;

import POJO.Auth.CreateTokenPayload;
import io.restassured.response.Response;
import utils.CommonUtils;

import java.io.IOException;
import java.util.Properties;

public class AuthUtils {
    public static CreateTokenPayload createAuthPayload(){
        return new CreateTokenPayload(
                "admin",
                "password123"
        );
    }
    public static void setAuthToken(Response resp) throws IOException {
        CommonUtils.writeInConfigProperties("token", resp.body().jsonPath().getString("token"));
    }
}
