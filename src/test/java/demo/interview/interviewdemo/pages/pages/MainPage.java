package demo.interview.interviewdemo.pages.pages;

import com.codeborne.selenide.SelenideElement;
import demo.interview.interviewdemo.app.AppManager;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class MainPage extends AppManager {

    static String URL = "https://demoqa.com/";

    private final SelenideElement
            forms = $x("//h5[text()='Forms']/../../..");

    public MainPage openMainPage() {
        open(URL);
        return this;
    }

    public void openForms() {
        forms.click();
    }
}
