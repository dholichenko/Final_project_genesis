package pages;

import elements.Field;
import elements.Link;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ChecklistPage extends Page {
    public ChecklistPage(WebDriver driver) {
        super(driver);
    }

    // addChecklist
    //public By addChecklist = By.cssSelector("a[class='button-link js-add-checklist-menu']");
    public Link addChecklist = new Link(By.cssSelector("a[class='button-link js-add-checklist-menu']"));

    // текстове поле назви Чекліста
    //public By checklistField = By.cssSelector("input[id='id-checklist']");
    public Field checklistField = new Field(By.cssSelector("#id-checklist"));
    //public Field checklistField = new Field(By.cssSelector("input[id='id-checklist']"));

    // перевірка імені першого чекліста зверху
    //public By checklistEx = By.cssSelector("#chrome-container > div > div > div  > div > div > div > div:nth-child(1) > div.window-module-title.window-module-title-no-divider.u-clearfix.ed.grab > div.editable.non-empty.checklist-title > .current.hide-on-edit");
    //public Field checklistEx = new Field(By.cssSelector ("#chrome-container > div > div > div  > div > div > div > div:nth-child(1) > div.window-module-title.window-module-title-no-divider.u-clearfix.ed.grab > div.editable.non-empty.checklist-title > .current.hide-on-edit"));
    public Field checklistEx = new Field(By.cssSelector ("h3.current.hide-on-edit"));
}
