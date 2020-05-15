package elements;

import org.openqa.selenium.By;

public class Container extends Element {
    // в конструктор Container передаємо By by. той який буде передаватись. В свою чергу, будемо передавати значення by в суперклас - клас від якого наслідуємось - Element.
    // Element, в свою чергу, просто зберігає його всередині в пропертіс (protected By by;), до якого ми маємо доступ за допомгою наслідування.
    public Container(By by) {
        super(by);
    }
}
