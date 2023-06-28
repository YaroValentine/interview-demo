package demo.interview.interviewdemo.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import demo.interview.interviewdemo.app.Attach;
import demo.interview.interviewdemo.config.Config;
import demo.interview.interviewdemo.jupiter.annotation.WebTest;
import org.junit.jupiter.api.AfterEach;

@WebTest
public abstract class BaseWebTest {
    protected static final Config CFG = Config.getConfig();

    static {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = CFG.getFrontUrl();
    }

    @AfterEach
    void addAttachments() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            Attach.screenshotAs("Last Screenshot");
            Attach.addVideo();
            Attach.pageSource();
            Attach.browserConsoleLogs();
        }
    }

}