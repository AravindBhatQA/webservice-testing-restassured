package methods.Auth;

import POJO.Auth.CreateTokenPayload;
import endPointURIs.EndPoint;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class AuthMethod {
    public static Response createToken(CreateTokenPayload payload){
        return given()
                .spec(EndPoint.specBuilder())
                .contentType("application/json")
                .body(payload)
                .when()
                .post(EndPoint.CREATE_TOKEN_URI);
    }
}
