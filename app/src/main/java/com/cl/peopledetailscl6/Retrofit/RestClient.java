package com.cl.peopledetailscl6.Retrofit;

import android.content.Context;
import android.provider.SyncStateContract;

import com.cl.peopledetailscl6.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {


    public static Retrofit retrofit = null;

    public static ApiInterface getApiInterface(Context context) {
        if (retrofit == null) {
            try {
                retrofit = new Retrofit.Builder()
                        .baseUrl(Constants.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return retrofit.create(ApiInterface.class);
    }
}
