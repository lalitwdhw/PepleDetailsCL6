package com.cl.peopledetailscl6.Retrofit;

import com.cl.peopledetailscl6.Model.User;
import com.cl.peopledetailscl6.Model.UserPost;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiInterface {

    // users details
    @GET(ApiInventory.GET_USERS)
    Call<List<User>> getUsers();

    //get Posts
    @GET(ApiInventory.GET_USER_POSTS)
    Call<List<UserPost>> getUserPosts(@Query("userId") int id);

    /*//get details of each shift
    @POST(ApiInventory.GET_SHIFT_DETAILS)
    Call<CommonResponse> getShiftDetails(@Header(ACCEPT_LANGUAGE) String lang, @Body Map<String, Object> map);
*/
}


