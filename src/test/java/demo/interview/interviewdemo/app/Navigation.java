package demo.interview.interviewdemo.app;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class Navigation extends AppManager{

    private final SelenideElement
            forms = $x("//h5[text()='Forms']/../../..");

}
