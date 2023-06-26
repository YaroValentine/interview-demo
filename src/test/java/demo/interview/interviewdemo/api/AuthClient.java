package demo.interview.interviewdemo.api;

import demo.interview.interviewdemo.api.context.CookieContext;
import demo.interview.interviewdemo.api.context.SessionContext;
import demo.interview.interviewdemo.api.interceptor.AddCookiesInterceptor;
import demo.interview.interviewdemo.api.interceptor.ReceivedCodeInterceptor;
import demo.interview.interviewdemo.api.interceptor.RecievedCookiesInterceptor;

import java.io.IOException;

public class AuthClient extends BaseRestClient {

    private final AuthService authService = retrofit.create(AuthService.class);

    public AuthClient() {
        super(
                "http://127.0.0.1:9000",
                true,
                new RecievedCookiesInterceptor(),
                new AddCookiesInterceptor(),
                new ReceivedCodeInterceptor()
        );
    }

    public void authorizedPreRequest() {
        try {
            authService.authorize(
                    null, null, null, null, null, null
            ).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void login(String username, String password) {
        final CookieContext cookieContext = CookieContext.getInstance();

        try {
            authService.login(
                    null, null, null, null, null
            ).execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getToken() {
        final SessionContext sessionContext = SessionContext.getInstance();

        try {
            return authService.token(
                    null, null, null, null, null, null
            ).execute().body().get("id_token").asText();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void signUp(String username, String password) {
        final CookieContext cookieContext = CookieContext.getInstance();

        try {
            authService.register().execute();
            authService.signUp(
                    cookieContext.getFormattedCookie("XSRF-TOKEN"),
                    cookieContext.getCookie("XSRF-TOKEN"),
                    username,
                    password,
                    password
            ).execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
