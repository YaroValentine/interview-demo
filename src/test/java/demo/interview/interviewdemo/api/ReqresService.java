package demo.interview.interviewdemo.api;

import demo.interview.interviewdemo.model.reqres.UsersResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ReqresService {

    @GET("api/users")
    Call<UsersResponse> getUsers(@Query("page") int page);


}
