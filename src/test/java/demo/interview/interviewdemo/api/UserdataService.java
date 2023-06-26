package demo.interview.interviewdemo.api;

import demo.interview.interviewdemo.model.UserJson;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserdataService {

    @POST("/updateUserInfo")
    Call<UserJson> updateUserInfo(@Body UserJson userJson);

    @GET("/currentUser")
    Call<UserJson> currentUser(@Query("username") String username);

}
