package demo.interview.interviewdemo.test.db;

import demo.interview.interviewdemo.db.dao.UsersDAO;
import demo.interview.interviewdemo.db.dao.UsersDAOHibernate;
import demo.interview.interviewdemo.db.entity.AuthorityEntity;
import demo.interview.interviewdemo.db.entity.UserEntity;
import demo.interview.interviewdemo.jupiter.annotation.GenerateUserWithHibernate;
import demo.interview.interviewdemo.test.BaseWebTest;
import io.qameta.allure.AllureId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class HibernateCrudTests extends BaseWebTest {

    @AllureId("201")
    @GenerateUserWithHibernate
    @Test
    void createUserTest(UserEntity user) {
        System.out.println(user.getUsername());
        Assertions.assertNotNull(user.getUsername());
    }

    @AllureId("202")
    @GenerateUserWithHibernate
    @Test
    void readUserTest(UserEntity user) {
        UsersDAO db = new UsersDAOHibernate();
        List<AuthorityEntity> authorities = db.readUser(user.getId()).getAuthorities();

        Assertions.assertEquals(authorities.get(0).getAuthority().toString(), "read");
        Assertions.assertEquals(authorities.get(1).getAuthority().toString(), "write");
        Assertions.assertTrue(db.readUser(user.getId()).getEnabled());
    }

    @AllureId("203")
    @GenerateUserWithHibernate
    @Test
    void updateUserTest(UserEntity user) {
        UsersDAO db = new UsersDAOHibernate();
        Assertions.assertTrue(user.getEnabled());

        user.setEnabled(false);
        db.updateUser(user);

        Assertions.assertFalse(user.getEnabled());
    }

    @AllureId("204")
    @GenerateUserWithHibernate
    @Test
    void deleteUserTest(UserEntity user) {
        System.out.println("User to delete: " + user.getUsername());
    }
}
