package com.example.thanseeh.dbooks.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VenderPojo {


    @SerializedName("data")
    public List<user> data = null;


    public class user {
        @SerializedName("id")
        @Expose
        public Integer id;
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("trn_no")
        @Expose
        public String trn_no;
        }


}
