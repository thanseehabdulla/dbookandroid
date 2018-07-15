package com.example.thanseeh.dbooks.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SalesReg {


    @SerializedName("date")
    @Expose
    public String date;
    @SerializedName("net_total")
    @Expose
    public Float net_total;
    @SerializedName("tax")
    @Expose
    public Float tax;
    @SerializedName("net_sales")
    @Expose
    public Float net_sales;
    @SerializedName("userid")
    @Expose
    public Integer userid;


    public SalesReg(String date, Float net_total, Float tax, Float net_sales,Integer userid) {
        this.date = date;
        this.net_total = net_total;
        this.tax = tax;
        this.net_sales = net_sales;
        this.userid = userid;
        }



}
