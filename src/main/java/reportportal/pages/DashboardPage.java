package reportportal.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class DashboardPage
{
    private WebDriver driver;

    private By dashboardsButton = By.xpath("//a[@class='sidebarButton__nav-link--gZnHQ' and contains(., 'Dashboards')]");
    private By dashboardLink = By.xpath("(//a[@class=\"gridCell__grid-cell--EIqeC gridCell__align-left--DFXWN dashboardTable__name--t2a89\"])[1]"); // Второй элемент
    private By addNewWidgetButton = By.xpath("//button[.//span[text()='Add new widget']]");
    private By widgetTypeOption = By.xpath("(//label[@class='inputRadio__input-radio--EMMTx inputRadio__mode-default--VD2jF'])[2]");
    private By nextStepButton = By.xpath("//button[.//span[text()='Next step']]");
    private By createFilter = By.xpath("//span[@class='ghostButton__text--SjHtK' and text() = 'Add filter']");
    private By filterOption = By.xpath("(//label[@class='inputRadio__input-radio--EMMTx inputRadio__mode-default--VD2jF' and @tabindex='1'])[1]");
    private By addButton = By.xpath("//button[@type='button' and text()='Add']");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    // Метод для перехода на Dashboard
    public void navigateToDashboard() {

        // Явное ожидание для кнопки "Dashboards"
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dashboardsButtonElement = wait.until(ExpectedConditions.elementToBeClickable(dashboardsButton));
        dashboardsButtonElement.click(); // Нажимаем кнопку "Dashboards"

        // Явное ожидание для второго элемента "DEMO DASHBOARD"
        WebElement demoDashboardLinkElement = wait.until(ExpectedConditions.elementToBeClickable(dashboardLink));
        demoDashboardLinkElement.click(); // Выбираем второй "DEMO DASHBOARD"
    }

    // Метод для добавления нового Widget'а
    public void addWidget(String widgetName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Нажимаем "Add new widget"
        WebElement addNewWidgetButtonElement = wait.until(ExpectedConditions.elementToBeClickable(addNewWidgetButton));
        addNewWidgetButtonElement.click();

        // Выбираем тип виджета
        WebElement widgetTypeOptionElement = wait.until(ExpectedConditions.elementToBeClickable(widgetTypeOption));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", widgetTypeOptionElement);
        widgetTypeOptionElement.click();

        // Нажимаем "Next step"
        WebElement nextStepButtonElement = wait.until(ExpectedConditions.elementToBeClickable(nextStepButton));
        nextStepButtonElement.click();

        By widgetLocator = By.xpath("//div[contains(@class, 'filtersItem__filter-item--DHlV9')]");
        List<WebElement> widgets = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(widgetLocator));

        if (widgets.isEmpty()) {
            // Если фильтров нет, создаем новый
            System.out.println("No filters found. Creating a new filter...");
            createFilter();
        }

        // Выбираем фильтр
        WebElement filterOptionElement = wait.until(ExpectedConditions.elementToBeClickable(filterOption));
        filterOptionElement.click();

        // Нажимаем "Next step" снова
        nextStepButtonElement = wait.until(ExpectedConditions.elementToBeClickable(nextStepButton));
        nextStepButtonElement.click();

        // Вводим название виджета
        WebElement widgetNameInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Enter widget name']")));
        widgetNameInput.clear();
        widgetNameInput.sendKeys(widgetName);

        // Нажимаем "Add"
        WebElement addButtonElement = wait.until(ExpectedConditions.elementToBeClickable(addButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addButtonElement);
        
    }

    // Метод для проверки наличия Widget'а на Dashboard
    public boolean isWidgetDisplayed(String widgetName) {
        By widgetLocator = By.xpath(String.format("//div[@class='widgetHeader__widget-name-block--AOAHS' and text()='%s']", widgetName));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(widgetLocator)).size() > 0;
    }

    public void createFilter(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Создание фильтра
        WebElement createFilterElement = wait.until(ExpectedConditions.elementToBeClickable(createFilter));
        createFilterElement.click();

        WebElement filterNameField = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//input[@placeholder='Input filter name']")
        ));
        filterNameField.clear();
        filterNameField.sendKeys("MyFilterName");


        WebElement launchNameField = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//input[@placeholder='Enter name']")
        ));
        launchNameField.clear();
        launchNameField.sendKeys("MyLaunchName");

        //
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(@class, 'bigButton__big-button--BmG4Q') and text()='Submit']")
        ));
        submitButton.click();
    }
}
