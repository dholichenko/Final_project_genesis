package pages;

import elements.Link;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends Page{

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String url = "https://trello.com/";

    public Link loginLink = new Link(By.cssSelector("a[href='/login']"));

}








