package pages;

import conf.TrelloCredentials;
import elements.Field;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class LoginPage extends Page {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public Field emailField = new Field(By.cssSelector("input[id='user']"));
    public Field passwordField = new Field(By.cssSelector("input[id='password']"));

    public void enterCredentials(String email, String password) {
        waitForVisibility(emailField);
        emailField.sendKeys(TrelloCredentials.email);
        passwordField.sendKeys(TrelloCredentials.password + Keys.ENTER);
    }

}
