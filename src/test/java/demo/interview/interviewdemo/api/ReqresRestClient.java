package demo.interview.interviewdemo.api;

import demo.interview.interviewdemo.config.Config;
import demo.interview.interviewdemo.model.reqres.UserData;
import demo.interview.interviewdemo.model.reqres.UsersResponse;
import org.junit.jupiter.api.Assertions;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

public class ReqresRestClient extends BaseRestClient {

    private final ReqresService reqresService = retrofit.create(ReqresService.class);

    public ReqresRestClient() {
        super(Config.getConfig().getRegresUrl());
    }

    public List<UserData> getUsers(int page) {
        try {
            Response<UsersResponse> response = reqresService.getUsers(page).execute();
            if (response.isSuccessful()) {
                UsersResponse usersResponse = response.body();
                if (usersResponse != null) {
                    return usersResponse.getData();
                } else {
                    Assertions.fail("Response body is null");
                    return null;
                }
            } else {
                Assertions.fail("Unsuccessful request: " + response.code() + " " + response.message());
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            Assertions.fail("Can't retrieve List from api/users: " + e.getMessage());
            return null;
        }
    }


}
