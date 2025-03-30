package reportportal.api;
import reportportal.utils.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class ApiClient {
    private static final String BASE_URL = ConfigReader.getBaseUrl();
    private static final String API_KEY = ConfigReader.getApiKey();

    public static RequestSpecification getRequestSpec() {
        return RestAssured.given()
                .baseUri(BASE_URL)
                .header("Authorization", "Bearer " + API_KEY)
                .contentType("application/json");
    }
}
