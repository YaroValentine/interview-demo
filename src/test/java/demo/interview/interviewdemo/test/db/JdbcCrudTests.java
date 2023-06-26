package demo.interview.interviewdemo.test.db;

import demo.interview.interviewdemo.db.dao.UsersDAOJdbc;
import demo.interview.interviewdemo.db.entity.UserEntity;
import demo.interview.interviewdemo.jupiter.annotation.GenerateUserWithJdbc;
import demo.interview.interviewdemo.test.BaseWebTest;
import io.qameta.allure.AllureId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JdbcCrudTests extends BaseWebTest {

    @AllureId("201")
    @GenerateUserWithJdbc
    @Test
    void createUserTest(UserEntity user) {
        System.out.println(user.getUsername());
        Assertions.assertNotNull(user.getUsername());
    }

    @AllureId("202")
    @GenerateUserWithJdbc
    @Test
    void readUserTest(UserEntity user) {
        UsersDAOJdbc jdbc = new UsersDAOJdbc();
        Assertions.assertTrue(jdbc.readUser(user.getId()).getEnabled());
    }

    @AllureId("203")
    @GenerateUserWithJdbc
    @Test
    void updateUserTest(UserEntity user) {
        Assertions.assertTrue(user.getEnabled());
        UsersDAOJdbc jdbc = new UsersDAOJdbc();
        user.setEnabled(false);
        jdbc.updateUser(user);
        Assertions.assertFalse(user.getEnabled());
    }

    @AllureId("204")
    @GenerateUserWithJdbc
    @Test
    void deleteUserTest(UserEntity user) {
        System.out.println("User to delete: " + user.getUsername());
    }
}
