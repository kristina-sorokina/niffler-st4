package guru.qa.niffler.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    private final SelenideElement loginLink = $x("//a[contains(text(), 'Login')]");
    private final SelenideElement usernameInput = $x("//input[@name='username']");
    private final SelenideElement passwordInput = $x("//input[@name='password']");
    private final SelenideElement signInButton = $x("//button[@type='submit']");

    public void logInUnder(String username, String password) {
        loginLink.click();
        usernameInput.setValue(username);
        passwordInput.setValue(password);
        signInButton.click();
    }
}
