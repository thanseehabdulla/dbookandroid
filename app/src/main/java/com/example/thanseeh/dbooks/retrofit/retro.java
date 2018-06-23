package com.example.thanseeh.dbooks.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface retro {
    @GET("/api/user")
    Call<UserPojo> doGetListResources();

    @POST("/api/register")
    Call<UserReg> createUser(@Body UserReg user);

//    @GET("/api/users?")
//    Call<UserList> doGetUserList(@Query("page") String page);
//
//    @FormUrlEncoded
//    @POST("/api/register")
//    Call<UserReg> createUser(@Field("name") String name, @Field("username") String username,@Field("password") String password,@Field("email") String email,@Field("phone") String phone,@Field("address") String address);
}
