package demo.interview.interviewdemo.jupiter.extensions;

import com.github.javafaker.Faker;
import demo.interview.interviewdemo.db.dao.UsersDAO;
import demo.interview.interviewdemo.db.dao.UsersDAOHibernate;
import demo.interview.interviewdemo.db.entity.Authority;
import demo.interview.interviewdemo.db.entity.AuthorityEntity;
import demo.interview.interviewdemo.db.entity.UserEntity;
import demo.interview.interviewdemo.jupiter.annotation.GenerateUserWithHibernate;
import io.qameta.allure.AllureId;
import org.junit.jupiter.api.extension.*;

import java.util.Arrays;
import java.util.Objects;

public class GenerateUserHibernateExtension implements ParameterResolver, BeforeEachCallback, AfterTestExecutionCallback {

    public static ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace
            .create(GenerateUserHibernateExtension.class);
    private Faker faker = new Faker();

    @Override
    public void beforeEach(ExtensionContext context) {
        final String testId = getTestId(context);
        GenerateUserWithHibernate annotation = context.getRequiredTestMethod().getAnnotation(GenerateUserWithHibernate.class);
        if (annotation != null) {
            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(faker.name().username());
            userEntity.setPassword("secret");
            userEntity.setEnabled(true);
            userEntity.setAccountNonExpired(true);
            userEntity.setAccountNonLocked(true);
            userEntity.setCredentialsNonExpired(true);

            userEntity.setAuthorities(Arrays.stream(Authority.values())
                    .map(authority -> {
                                AuthorityEntity authorityEntity = new AuthorityEntity();
                                authorityEntity.setAuthority(authority);
                                authorityEntity.setUser(userEntity);
                                return authorityEntity;
                            }
                    ).toList());
            UsersDAO db = new UsersDAOHibernate();
            db.createUser(userEntity);
            context.getStore(NAMESPACE).put(testId, userEntity);
        }
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext,
                                     ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().isAssignableFrom(UserEntity.class)
                && extensionContext.getRequiredTestMethod().getAnnotation(GenerateUserWithHibernate.class) != null;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext,
                                   ExtensionContext extensionContext) throws ParameterResolutionException {
        GenerateUserWithHibernate annotation = extensionContext.getRequiredTestMethod().getAnnotation(GenerateUserWithHibernate.class);
        if (annotation != null) {
            final String testId = getTestId(extensionContext);
            return extensionContext.getStore(NAMESPACE).get(testId, UserEntity.class);
        }
        return null;
    }

    @Override
    public void afterTestExecution(ExtensionContext context) {
        GenerateUserWithHibernate annotation = context.getRequiredTestMethod().getAnnotation(GenerateUserWithHibernate.class);
        if (annotation != null) {
            final String testId = getTestId(context);
            UserEntity user = (UserEntity) context.getStore(NAMESPACE).get(testId);
            UsersDAO db = new UsersDAOHibernate();
            db.removeUser(user);
        }
    }

    private String getTestId(ExtensionContext context) {
        return Objects
                .requireNonNull(context.getRequiredTestMethod().getAnnotation(AllureId.class))
                .value();
    }
}