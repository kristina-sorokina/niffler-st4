package guru.qa.niffler.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private final SelenideElement loginLink = $(By.xpath("//a[contains(text(), 'Login')]"));
    private final SelenideElement usernameInput = $(By.xpath("//input[@name='username']"));
    private final SelenideElement passwordInput = $(By.xpath("//input[@name='password']"));
    private final SelenideElement signInButton = $(By.xpath("//button[@type='submit']"));

    public void logInUnder(String username, String password) {
        loginLink.click();
        usernameInput.setValue(username);
        passwordInput.setValue(password);
        signInButton.click();
    }
}
