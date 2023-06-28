package demo.interview.interviewdemo.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;

//@Data
//@Accessors(chain = true)
public class TestData {

    String
            firstName,
            lastName,
            email,
            gender,
            dateOfBirth,
            mobile,
            address,
            state,
            city;

    ArrayList<String>
            hobbies,
            subjects;

    public String getFirstName() {
        return firstName;
    }

    public TestData setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public TestData setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public TestData setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public TestData setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public TestData setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public TestData setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public TestData setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getState() {
        return state;
    }

    public TestData setState(String state) {
        this.state = state;
        return this;
    }

    public String getCity() {
        return city;
    }

    public TestData setCity(String city) {
        this.city = city;
        return this;
    }

    public ArrayList<String> getHobbies() {
        return hobbies;
    }

    public TestData setHobbies(ArrayList<String> hobbies) {
        this.hobbies = hobbies;
        return this;
    }

    public ArrayList<String> getSubjects() {
        return subjects;
    }

    public TestData setSubjects(ArrayList<String> subjects) {
        this.subjects = subjects;
        return this;
    }
}
