package com.example.thanseeh.dbooks.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserPojo {


    @SerializedName("data")
    public List<user> data = null;


    public class user {
        @SerializedName("id")
        @Expose
        public Integer id;
        @SerializedName("vendername")
        @Expose
        public String vendername;
        @SerializedName("trn_no")
        @Expose
        public Float trn_no;
        @SerializedName("date_invoice")
        @Expose
        public String date_invoice;
        @SerializedName("amount")
        @Expose
        public Float amount;
        @SerializedName("vat")
        @Expose
        public Float vat;
        @SerializedName("total")
        @Expose
        public Float total;
        @SerializedName("invoice_number")
        @Expose
        public String invoice_number;
    }


}
