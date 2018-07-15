package com.example.thanseeh.dbooks.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SalesPojo {


    @SerializedName("data")
    public List<user> data = null;


    public class user {
        @SerializedName("id")
        @Expose
        public Integer id;
        @SerializedName("date")
        @Expose
        public String date;
        @SerializedName("tax")
        @Expose
        public Float tax;
        @SerializedName("net_sales")
        @Expose
        public String net_sales;
        @SerializedName("net_total")
        @Expose
        public Float net_total;

    }


}
