package demo.interview.interviewdemo.test.db;

import demo.interview.interviewdemo.db.dao.UsersDAOJdbc;
import demo.interview.interviewdemo.db.entity.UserEntity;
import demo.interview.interviewdemo.jupiter.annotation.GenerateUserWithSpringJdbc;
import demo.interview.interviewdemo.jupiter.extensions.GenerateUserJdbcSpringExtension;
import demo.interview.interviewdemo.test.BaseWebTest;
import io.qameta.allure.AllureId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(GenerateUserJdbcSpringExtension.class)
public class SpringJdbcCrudTests extends BaseWebTest {

    @AllureId("201")
    @GenerateUserWithSpringJdbc
    @Test
    void createUserTest(UserEntity user) {
        System.out.println(user.getUsername());
        Assertions.assertNotNull(user.getUsername());
    }

    @AllureId("202")
    @GenerateUserWithSpringJdbc
    @Test
    void readUserTest(UserEntity user) {
        UsersDAOJdbc jdbc = new UsersDAOJdbc();

        Assertions.assertEquals(jdbc.readUser(user.getId()).getAuthorities().get(0).getAuthority().toString(), "read");
        Assertions.assertEquals(jdbc.readUser(user.getId()).getAuthorities().get(1).getAuthority().toString(), "write");

        Assertions.assertTrue(jdbc.readUser(user.getId()).getEnabled());
    }

    @AllureId("203")
    @GenerateUserWithSpringJdbc
    @Test
    void updateUserTest(UserEntity user) {
        Assertions.assertTrue(user.getEnabled());
        UsersDAOJdbc jdbc = new UsersDAOJdbc();
        user.setEnabled(false);
        jdbc.updateUser(user);

        Assertions.assertFalse(user.getEnabled());
    }

    @AllureId("204")
    @GenerateUserWithSpringJdbc
    @Test
    void deleteUserTest(UserEntity user) {
        System.out.println("User to delete: " + user.getUsername());
    }
}
