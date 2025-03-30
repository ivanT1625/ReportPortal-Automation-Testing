package reportportal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage
{
    private WebDriver driver;

//    private By usernameField = By.name("login");
//    private By passwordField = By.name("password");
    private By logginButton = By.xpath("//button[contains(text(), 'Login')]");

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public void login()
    {
        //driver.findElement(usernameField).sendKeys(username);
        //driver.findElement(passwordField).sendKeys(password);
        driver.findElement(logginButton).click();
    }
}
