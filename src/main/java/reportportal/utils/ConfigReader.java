package reportportal.utils;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    // Статический блок для загрузки свойств при инициализации класса
    static {
        try {
            String rootPath = System.getProperty("user.dir"); // Путь к корневой директории проекта
            String configPath = rootPath + "/src/test/resources/config.properties"; // Путь к файлу конфигурации
            FileInputStream input = new FileInputStream(configPath);
            properties = new Properties();
            properties.load(input);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load configuration file.");
        }
    }

    public static String getBaseUrl() {
        return properties.getProperty("base.url");
    }

    public static String getApiKey() {
        return properties.getProperty("api.key");
    }

    public static String getProjectName() {
        return properties.getProperty("project.name");
    }

    public static String getProperty(String urlApp) {
        return properties.getProperty(urlApp);
    }
}
