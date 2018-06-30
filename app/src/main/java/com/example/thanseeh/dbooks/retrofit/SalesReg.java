package com.example.thanseeh.dbooks.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SalesReg {


    @SerializedName("date")
    @Expose
    public String date;
    @SerializedName("item_sold")
    @Expose
    public Float item_sold;
    @SerializedName("gross_sales")
    @Expose
    public Float gross_sales;
    @SerializedName("tax")
    @Expose
    public Float tax;
    @SerializedName("net_sales")
    @Expose
    public Float net_sales;


    public SalesReg(String date, Float item_sold, Float gross_sales, Float tax, Float net_sales) {
        this.date = date;
        this.item_sold = item_sold;
        this.gross_sales = gross_sales;
        this.tax = tax;
        this.net_sales = net_sales;
        }



}
