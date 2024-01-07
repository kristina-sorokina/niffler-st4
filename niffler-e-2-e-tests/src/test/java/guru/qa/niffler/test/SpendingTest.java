package guru.qa.niffler.test;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import guru.qa.niffler.jupiter.GenerateCategory;
import guru.qa.niffler.jupiter.GenerateSpend;
import guru.qa.niffler.model.CurrencyValues;
import guru.qa.niffler.model.SpendJson;
import guru.qa.niffler.pages.LoginPage;
import guru.qa.niffler.pages.MainPage;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.size;

public class SpendingTest extends BaseWebTest {

  private final LoginPage loginPage = new LoginPage();
  private final MainPage mainPage = new MainPage();

  @GenerateCategory(
          category = "Обучение",
          username = "duck")
  @GenerateSpend(
          username = "duck",
          description = "QA.GURU Advanced 4",
          amount = 72500.00,
          category = "Обучение",
          currency = CurrencyValues.RUB
  )
  @Test
  void spendingShouldBeDeletedByButtonDeleteSpending(SpendJson spend) {
    Selenide.open("http://127.0.0.1:3000/main");
    loginPage.logInUnder("duck", "12345");

    ElementsCollection records = mainPage.selectTableRecordByText(spend.description())
            .clickDeleteButton()
            .getAllRecords();

    records.shouldHave(size(0));
  }
}
