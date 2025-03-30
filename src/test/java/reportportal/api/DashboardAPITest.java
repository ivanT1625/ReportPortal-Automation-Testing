package reportportal.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class DashboardAPITest
{
    @Test
    public void testCreateDashboard() {
        String dashboardName = "Test4_NameDashboard";
        String dashboardDescription = "Test4_Description";

        Response response = DashboardAPI.createDashboard(dashboardName, dashboardDescription);

        response.then()
                .statusCode(201)
                    .extract().response();


        System.out.println("Dashboard created successfully!");
        System.out.println("Response Body: " + response.getBody().asString());

        Response dashboardsResponse = DashboardAPI.getAllDashboards();
        dashboardsResponse.then()
                .statusCode(200)
                .body("content.name", hasItem(dashboardName))
                .log().all();

        System.out.println("Dashboard is present in the list!");
    }
}
