package demo.interview.interviewdemo.pages.pages;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import demo.interview.interviewdemo.app.AppManager;
import io.qameta.allure.Step;

import java.util.ArrayList;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeForm extends AppManager {

    static final String URL = "https://demoqa.com/automation-practice-form";

    //region Locators
    final SelenideElement
            firstName = $("#firstName"),
            lastName = $("#lastName"),
            email = $("#userEmail"),
            genderMale = $(byText("Male")),
            genderFemale = $(byText("Female")),
            genderOther = $(byText("Other")),
            userNumber = $("#userNumber"),
            dateOfBirth = $("#dateOfBirthInput"),
            monthPicker = $(".react-datepicker__month-select"),
            yearPicker = $(".react-datepicker__year-select"),
            subjects = $("#subjectsInput"),
            hobbiesSports = $x("//label[@for='hobbies-checkbox-1']"),
            hobbiesReading = $x("//label[@for='hobbies-checkbox-2']"),
            hobbiesMusic = $x("//label[@for='hobbies-checkbox-3']"),
            currentAddress = $("#currentAddress"),
            state = $("#state"),
            city = $("#city"),
            submit = $("#submit"),
            tableStudentName = $x("//td[text()='Student Name']/.."),
            tableStudentEmail = $x("//td[text()='Student Email']/.."),
            tableGender = $x("//td[text()='Gender']/.."),
            tableMobile = $x("//td[text()='Mobile']/.."),
            tableDateOfBirth = $x("//td[text()='Date of Birth']/.."),
            tableSubjects = $x("//td[text()='Subjects']/.."),
            tableHobbies = $x("//td[text()='Hobbies']/.."),
            tablePictures = $x("//td[text()='Picture']/.."),
            tableAddress = $x("//td[text()='Address']/.."),
            tableStateAndCity = $x("//td[text()='State and City']/..");
    //endregion

    @Step("Open Practice Form")
    public PracticeForm open() {
        Selenide.open(PracticeForm.URL);
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }

    @Step("Fill Practice Form")
    public PracticeForm fillPracticeForm() {
        setFirstName(tD.getFirstName());
        setLastName(tD.getLastName());
        setEmail(tD.getEmail());
        chooseGender(tD.getGender());
        setMobileNumber(tD.getMobile());
        setDateOfBirth(tD.getDateOfBirth());
        setSubjects(tD.getSubjects());
        chooseHobbie(tD.getHobbies());
        setCurrentAddress(tD.getAddress());
        selectState(tD.getState());
        selectCity(tD.getCity());
        takeScreenshot();
        clickSubmit();
        return this;
    }

    @Step("Verify Form")
    public void verifyForm() {
        String[] formattedDate = tD.getDateOfBirth().replace("\"", "").replace(",", "").split(" ");
        tD.setDateOfBirth("Date of Birth " + formattedDate[2] + " " + formattedDate[1] + "," + formattedDate[3]);

        takeScreenshot();
        savePageSource();

        registrationResultsModal().verifyModalAppears()
                .verifyResult("Student Name", tD.getFirstName() + " " + tD.getLastName())
                .verifyResult("Student Email", tD.getEmail())
                .verifyResult("Gender", tD.getGender())
                .verifyResult("Mobile", tD.getMobile())
                .verifyResult("Date of Birth", tD.getDateOfBirth())
                .verifyResult("Address", tD.getAddress())
                .verifyResult("State and City", tD.getState());

        tD.getSubjects().forEach(subject -> tableSubjects.shouldHave(text(subject)));
        tD.getHobbies().forEach(hobby -> tableHobbies.shouldHave(text(hobby)));
    }

    @Step("Set First Name: {value}")
    private void setFirstName(String value) {
        firstName.setValue(value);
    }

    @Step("Set Last Name: {value}")
    private void setLastName(String value) {
        lastName.setValue(value);
    }

    @Step("Set Email: {value}")
    private void setEmail(String value) {
        email.setValue(value);
    }

    @Step("Set Mobile Number: {value}")
    private void setMobileNumber(String value) {
        userNumber.setValue(value);
    }

    @Step("Set Date of Birth: {date}")
    private void setDateOfBirth(String date) {
        String[] formattedDate = date.replace("\"", "").replace(",", "").split(" ");
        String day = formattedDate[2];
        String month = formattedDate[1];
        String year = formattedDate[3];

        dateOfBirth.click();
        calendarComponent().setDate(day, month, year);
    }

    @Step("Set First Subjects: {subjects}")
    private void setSubjects(ArrayList<String> subjects) {
        if (subjects != null) {
            for (String subject : subjects) {
                this.subjects
                        .setValue(subject)
                        .pressEnter();
            }
        }
    }

    @Step("Set Address: {address}")
    private void setCurrentAddress(String address) {
        currentAddress.setValue(address);
    }

    @Step("Set Gender: {gender}")
    private void chooseGender(String gender) {
        switch (gender.toLowerCase()) {
            case "male":
                genderMale.click();
                break;
            case "female":
                genderFemale.click();
                break;
            case "other":
                genderOther.click();
                break;
        }
    }

    @Step("Set Hobbies: {hobbies}")
    private void chooseHobbie(ArrayList<String> hobbies) {
        for (String hobbie : hobbies) {
            switch (hobbie.toLowerCase()) {
                case "sports":
                    hobbiesSports.scrollIntoView(true).click();
                    break;
                case "reading":
                    hobbiesReading.scrollIntoView(true).click();
                    break;
                case "music":
                    hobbiesMusic.scrollIntoView(true).click();
                    break;
            }
        }
    }

    @Step("Select State: {_state}")
    private void selectState(String _state) {
        state.scrollTo().click();
        $(Selectors.byText(_state)).click();
    }

    @Step("Select City: {_city}")
    private void selectCity(String _city) {
        city.scrollTo().click();
        $(Selectors.byText(_city)).click();
    }

    @Step("Click Submit button")
    private void clickSubmit() {
        submit.scrollIntoView(true).click();
    }

}
