package demo.interview.interviewdemo.api;

import demo.interview.interviewdemo.model.reqres.*;
import retrofit2.Call;
import retrofit2.http.*;

public interface ReqresService {

    @GET("api/users")
    Call<UsersResponse> getUsers(@Query("page") int page);

    @POST("api/register")
    Call<SuccessfulRegistration> register(@Body Register register);

    @POST("api/register")
    Call<UnsuccessfulRegistration> registerUnsuccessfully(@Body Register user);

    @GET("api/unknown")
    Call<DataListResponse<ColorsData>> getColorsData();

    @DELETE("api/users/{id}")
    Call<Void> deleteUser(@Path("id") int userId);

}
