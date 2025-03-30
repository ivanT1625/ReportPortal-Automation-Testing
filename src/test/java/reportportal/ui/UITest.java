package reportportal.ui;
import org.openqa.selenium.chrome.ChromeOptions;
import reportportal.pages.DashboardPage;
import reportportal.pages.LoginPage;
import reportportal.utils.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UITest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        // Настройка WebDriver через WebDriverManager
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testAddWidget() {
        // Arrange
        String widgetName = "DEMO_FILTER_702"; // Имя виджета
        driver.get(ConfigReader.getProperty("app.url"));

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login();

        DashboardPage dashboardPage = new DashboardPage(driver);

        // Act
        dashboardPage.navigateToDashboard(); // Переходим на Dashboard
        dashboardPage.addWidget(widgetName); // Добавляем новый Widget

        // Assert
        assertTrue(dashboardPage.isWidgetDisplayed(widgetName), "Widget was not added successfully.");
    }

    @AfterEach
    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
    }
}
