package com.example.thanseeh.dbooks.retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserPojo {


    @SerializedName("data")
    public List<user> data = null;


    public class user {
        @SerializedName("id")
        public Integer id;
        @SerializedName("name")
        public String name;
        @SerializedName("username")
        public String username;
        @SerializedName("email")
        public String email;
        @SerializedName("phone")
        public String phone;
        @SerializedName("address")
        public String address;
    }


}
