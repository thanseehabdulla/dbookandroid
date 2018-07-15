package com.example.thanseeh.dbooks.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserReg {


    @SerializedName("vendername")
    @Expose
    public String vendername;
    @SerializedName("trn_no")
    @Expose
    public String trn_no;
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
    @SerializedName("userid")
    @Expose
    public Integer userid;

    public UserReg(String vendername, String trn_no, String date_invoice, Float amount, Float vat, Float total, String invoice_number, int userid) {
        this.vendername = vendername;
        this.trn_no = trn_no;
        this.date_invoice = date_invoice;
        this.amount = amount;
        this.vat = vat;
        this.total = total;
        this.invoice_number = invoice_number;
        this.userid = userid;
    }



}
