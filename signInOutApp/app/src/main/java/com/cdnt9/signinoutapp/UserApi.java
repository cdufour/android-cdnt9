package com.cdnt9.signinoutapp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.POST;

public interface UserApi {
    @POST("signup")
    Call<User> signUp(@Body User user);
    //Call<User> signUp(@Body User user);

    @POST("signin")
    Call<User> signIn(@Body User user);
}
