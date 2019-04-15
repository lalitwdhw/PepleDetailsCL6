package com.cl.peopledetailscl6.retrofit;

import com.cl.peopledetailscl6.model.User;
import com.cl.peopledetailscl6.model.UserPost;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiInterface {

    // user and their details
    @GET(ApiInventory.GET_USERS)
    Call<List<User>> getUsers();

    //get posts of users by their ids
    @GET(ApiInventory.GET_USER_POSTS)
    Call<List<UserPost>> getUserPosts(@Query("userId") int id);
}


