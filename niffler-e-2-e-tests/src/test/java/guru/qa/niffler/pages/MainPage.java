package guru.qa.niffler.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    private final SelenideElement spendingsTable = $(By.xpath("//table[@class='table spendings-table']/tbody"));
    private final SelenideElement deleteSelectedButton = $(By.xpath("//button[text()='Delete selected']"));

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

    public ElementsCollection getAllRecords() {
        return spendingsTable
                .$$("tr");
    }
}
