package guru.qa.niffler.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.opentest4j.AssertionFailedError;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {
    private final SelenideElement spendingsTable = $x("//table[@class='table spendings-table']/tbody");
    private final SelenideElement deleteSelectedButton = $x("//button[text()='Delete selected']");

    public MainPage selectTableRecordByText(String text) {
        spendingsTable.$$("tr")
                .find(Condition.text(text))
                .$$("td")
                .first().scrollIntoView(true)
                .click();
        return this;
    }

    public MainPage clickDeleteButton() {
        deleteSelectedButton.click();
        return this;
    }

    public boolean tableShouldHaveSize(int size) {
       try {
           spendingsTable.$$("tr").shouldHave(size(size));
       } catch (AssertionFailedError error) {
           return false;
       }
        return true;
    }
}
