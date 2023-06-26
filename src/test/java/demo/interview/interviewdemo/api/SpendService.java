package demo.interview.interviewdemo.api;

import demo.interview.interviewdemo.model.CategoryJson;
import demo.interview.interviewdemo.model.SpendJson;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SpendService {

    @POST("/addSpend")
    Call<SpendJson> addSpend(@Body SpendJson spend);

    @POST("/category")
    Call<CategoryJson> addCategory(@Body CategoryJson category);

}
