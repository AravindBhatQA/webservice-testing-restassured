package Auth;

import POJO.Auth.CreateTokenPayload;
import io.restassured.response.Response;
import methods.Auth.AuthMethod;
import org.testng.annotations.Test;
import utils.ValidatorUtils;
import utils.specific.Auth.AuthUtils;

import java.io.IOException;

public class AuthTest {

    @Test
    public void create_Token() throws IOException {
        CreateTokenPayload createTokenPayload = AuthUtils.createAuthPayload();
        Response createTokenResponse = AuthMethod.createToken(createTokenPayload);

        ValidatorUtils.validateStatusCode(createTokenResponse, 200);
        ValidatorUtils.validateResponseSchema(createTokenResponse, "Auth/Post_Create_Token.json");
        AuthUtils.setAuthToken(createTokenResponse);
    }
}
