package reportportal.api;
import reportportal.utils.ConfigReader;
import io.restassured.response.Response;

import static reportportal.api.ApiClient.getRequestSpec;

public class DashboardAPI {

    private static final String PROJECT_NAME = ConfigReader.getProjectName();

    /**
     * Создает новый Dashboard.
     *
     * @param name        Имя Dashboard
     * @param description Описание Dashboard
     * @return Ответ сервера
     */
    public static Response createDashboard(String name, String description) {
        String requestBody;

        if (name != null){
            requestBody = "{\n" +
                    "  \"name\": \"" + name + "\",\n" +
                    "  \"description\": \"" + description + "\"\n" +
                    "}";
        } else {
            requestBody = "{\n" +
                    "  \"description\": \"" + description + "\"\n" +
                    "}";
        }


        return getRequestSpec()
                .body(requestBody)
                .when()
                .post("/api/v1/" + PROJECT_NAME + "/dashboard");
    }

    /**
     * Получает список всех Dashboard'ов.
     *
     * @return Ответ сервера
     */
    public static Response getAllDashboards() {
        return getRequestSpec()
                .get("/api/v1/" + PROJECT_NAME + "/dashboard");
    }
}
