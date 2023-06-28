package demo.interview.interviewdemo.test.ui;

import com.codeborne.selenide.logevents.SelenideLogger;
import demo.interview.interviewdemo.app.AppManager;
import demo.interview.interviewdemo.model.TestData;
import demo.interview.interviewdemo.test.BaseWebTest;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureId;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;


public class AnnotationsPracticeFormTest extends BaseWebTest {

    @AllureId("501")
    @DisplayName("PracticeForm")
    @ParameterizedTest
    @MethodSource("demo.interview.interviewdemo.app.DataSources#practiceFormTestData")
    public void studentRegistrationForm(TestData tD) {
        Allure.parameter("Parameters", tD);

        new AppManager(tD)
                .practiceForm().open()
                .fillPracticeForm()
                .verifyForm()
        ;
    }
}
