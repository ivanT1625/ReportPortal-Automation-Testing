package reportportal.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class NegativeDashboardAPITest
{

    @Test
    public void testCreateDashboardWithMissingParameters() {
        String dashboardDescription = "****000/////111111";
        Response response = DashboardAPI.createDashboard(null, dashboardDescription);

        response.then()
                .statusCode(400) // Ожидаем ошибку Bad Request
                .extract().response();

        System.out.println("Dashboard dont created !");
        System.out.println("Response Body: " + response.getBody().asString());

        Response dashboardsResponse = DashboardAPI.getAllDashboards();
        dashboardsResponse.then()
                .statusCode(200)
                .body("content.description", not(hasItem(dashboardDescription)))
                .log().all();

    }
}
