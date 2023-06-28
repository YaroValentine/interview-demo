package demo.interview.interviewdemo.app;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import demo.interview.interviewdemo.model.TestData;
import demo.interview.interviewdemo.pages.pages.MainPage;
import demo.interview.interviewdemo.pages.pages.PracticeForm;
import demo.interview.interviewdemo.pages.pages.components.CalendarComponent;
import demo.interview.interviewdemo.pages.pages.components.RegistrationResultsModal;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.$$;

public class AppManager {

    public static ArrayList<String> errorList;
    public static TestData tD;
    private Navigation navigation;
    private MainPage mainPage;
    private PracticeForm practiceForm;
    private CalendarComponent calendarComponent;
    private RegistrationResultsModal registrationResultsModal;

    public AppManager() {

    }

    public AppManager(TestData tD) {
        AppManager.tD = tD;
    }

    @Attachment(value = "Page source", type = "text/html")
    public static byte[] attachPageSource() {
        return WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
    }

    @Attachment(value = "Video", type = "text/html", fileExtension = ".html")
    public static String attachVideo(String sessionId) {
        return "<html><body><video width='100%' height='100%' controls autoplay><source src='"
                + getVideoUrl(sessionId)
                + "' type='video/mp4'></video></body></html>";
    }

    public static String getVideoUrl(String sessionId) {
        return getWebVideoUrl(sessionId);
    }

    public static String getWebVideoUrl(String sessionId) {
        try {
            return new URL(getWebVideoStorage() + sessionId + ".mp4") + "";
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getWebVideoStorage() {
        return System.getProperty("video.storage");
    }

    public void init() {
        errorList = new ArrayList<>();
        mainPage().openMainPage();
    }

    //region Lazy Page Initialization
    public Navigation navigation() {
        return navigation == null ? new Navigation() : navigation;
    }

    public MainPage mainPage() {
        return mainPage == null ? new MainPage() : mainPage;
    }

    public PracticeForm practiceForm() {
        return practiceForm == null ? new PracticeForm() : practiceForm;
    }

    public CalendarComponent calendarComponent() {
        return calendarComponent == null ? new CalendarComponent() : calendarComponent;
    }

    public RegistrationResultsModal registrationResultsModal() {
        return registrationResultsModal == null ? new RegistrationResultsModal() : registrationResultsModal;
    }
    //endregion

    protected void sleep(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean attributeExists(SelenideElement locator, String attrName) {
        try {
            String value = locator.attr(attrName);
            if (value != null) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Attribute " + attrName + " not found");
        }
        return false;
    }

    public String currentDate() {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        return df.format(new Date());
    }

    public void checkElements() {
        List<String> listOfElementsToCheck = new ArrayList<>();
        ElementsCollection listOfQualifiedElements = $$("");
        for (SelenideElement element : listOfQualifiedElements) {
            boolean qualifierExists = Objects.equals(element.getAttribute("AttributeName"), "State Of Attribute");
            if (qualifierExists) {
                //Action. For example if we are looking for all fields that are having error message, we can extract it.
                String errorText = element.$x("subXpath").getText();
                listOfElementsToCheck.add(errorText);
            }
        }
        //Put errors into one list to throw them out upon test completion
        StringBuilder error = new StringBuilder();
        if (!listOfElementsToCheck.isEmpty()) {
            int i = 0;
            for (String elementsText : listOfElementsToCheck) {
                i++;
                error.append(i).append(") ").append(elementsText).append(" is required. ");
            }
            Assertions.fail(error.toString());
        }
    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    public void savePageSource() {
        Allure.addAttachment("Page Source", "text/html", WebDriverRunner.source(), "html");
    }


}
