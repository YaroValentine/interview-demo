package demo.interview.interviewdemo.test.api;

import demo.interview.interviewdemo.api.ReqresRestClient;
import demo.interview.interviewdemo.model.reqres.UserData;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

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



}
