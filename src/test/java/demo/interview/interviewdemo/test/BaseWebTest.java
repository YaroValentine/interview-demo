package demo.interview.interviewdemo.test;

import com.codeborne.selenide.Configuration;
import demo.interview.interviewdemo.config.Config;
import demo.interview.interviewdemo.jupiter.annotation.WebTest;

@WebTest
public abstract class BaseWebTest {
    protected static final Config CFG = Config.getConfig();

    static {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = CFG.getFrontUrl();
    }

}