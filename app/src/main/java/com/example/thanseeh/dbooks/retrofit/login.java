package com.example.thanseeh.dbooks.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class login {

    @SerializedName("username")
    @Expose
    public String username;
    @SerializedName("password")
    @Expose
    public String password;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("userid")
    @Expose
    public Integer userid;

    public login(String name, String password) {
        this.username = name;
        this.password = password;

    }


}
