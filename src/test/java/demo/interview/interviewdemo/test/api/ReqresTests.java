package demo.interview.interviewdemo.test.api;

import demo.interview.interviewdemo.api.ReqresRestClient;
import demo.interview.interviewdemo.model.reqres.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class ReqresTests {
    ReqresRestClient client = new ReqresRestClient();

    @Test
    void checkAvatarAndIdTest() {
        List<UserData> users = client.getUsers(2);

        users.forEach(user -> {
            boolean avatarContainsUserId = user.getAvatar().contains(user.getId().toString());
            assertTrue(avatarContainsUserId);
        });

        boolean allUsersEmailsEndsProperDomain = users.stream().allMatch(user -> user.getEmail().endsWith("@reqres.in"));
        assertTrue(allUsersEmailsEndsProperDomain);
    }

    @Test
    void successRegistrationTest() {
        ReqresRestClient client = new ReqresRestClient();

        int id = 4;
        String token = "QpwL5tke4Pnpja7X4";

        Register user = new Register("eve.holt@reqres.in", "pistol");

        SuccessfulRegistration successfulRegistration = client.register(user);

        assertNotNull(successfulRegistration.getId());
        assertNotNull(successfulRegistration.getToken());

        assertEquals(id, successfulRegistration.getId());
        assertEquals(token, successfulRegistration.getToken());
    }

    @Test
    void unsuccessfulRegistrationTest() {
        Register user = new Register("sydney@fife", "");

        UnsuccessfulRegistration unsuccessfulRegistration = client.registerUnsuccessfully(user);

        assertEquals("Missing password", unsuccessfulRegistration.getError());
    }

    @Test
    void sortedYearsTest() {
        List<ColorsData> colorsData = client.getColorsData();

        List<Integer> years = colorsData.stream().map(ColorsData::getYear).collect(Collectors.toList());
        List<Integer> sortedYears = new ArrayList<>(years);
        Collections.sort(sortedYears);

        assertEquals(sortedYears, years);
    }

    @Test
    void deleteUserTest() {
        assertTrue(client.deleteUser(2));
    }

}
