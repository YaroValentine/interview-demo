package demo.interview.interviewdemo.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import demo.interview.interviewdemo.config.Config;
import demo.interview.interviewdemo.model.reqres.*;
import org.junit.jupiter.api.Assertions;
import retrofit2.Response;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ReqresRestClient extends BaseRestClient {

    private final ReqresService reqresService = retrofit.create(ReqresService.class);

    public ReqresRestClient() {
        super(Config.getConfig().getRegresUrl());
    }

    public List<UserData> getUsers(int page) {
        try {
            return reqresService.getUsers(page).execute().body().getData();
        } catch (IOException e) {
            throw new RuntimeException("Can't retrieve List from api/users", e);
        }
    }

    public SuccessfulRegistration register(Register register) {
        try {
            return reqresService.register(register).execute().body();
        } catch (IOException e) {
            throw new RuntimeException("Can't register user", e);
        }
    }

    public UnsuccessfulRegistration registerUnsuccessfully(Register user) {
        try {
            Response<UnsuccessfulRegistration> response = reqresService.registerUnsuccessfully(user).execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                ObjectMapper om = new ObjectMapper();
                return om.readValue(response.errorBody().string(), UnsuccessfulRegistration.class);
            }
        } catch (IOException e) {
            Assertions.fail("Can't register user");
            return null;
        }
    }

    public List<ColorsData> getColorsData() {
        try {
            Response<DataListResponse<ColorsData>> response = reqresService.getColorsData().execute();
            if (response.isSuccessful()) {

                return Objects.requireNonNull(response.body()).getData();
            } else {
                Assertions.fail("Failed to get colors data");
                return Collections.emptyList();
            }
        } catch (IOException e) {
            Assertions.fail("Can't get colors data");
            return Collections.emptyList();
        }
    }

    public boolean deleteUser(int userId) {
        try {
            Response<Void> response = reqresService.deleteUser(userId).execute();
            return response.isSuccessful() && response.code() == 204;
        } catch (IOException e) {
            Assertions.fail("Can't delete user");
            return false;
        }
    }

}
