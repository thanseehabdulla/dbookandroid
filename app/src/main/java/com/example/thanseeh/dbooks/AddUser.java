package com.example.thanseeh.dbooks;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thanseeh.dbooks.retrofit.UserReg;
import com.example.thanseeh.dbooks.retrofit.retro;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddUser extends AppCompatActivity{


    public static EditText username, name, email, phone, address, password;
    private static  Button submit;
    private retro apiInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adduser);
        username = (EditText) findViewById(R.id.username);
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        phone = (EditText) findViewById(R.id.phone);
        address = (EditText) findViewById(R.id.address);
        submit = (Button) findViewById(R.id.button);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                UserAdd(name.getText().toString(), username.getText().toString(), email.getText().toString(), password.getText().toString(), phone.getText().toString(), address.getText().toString());


            }
        });

    }

    private void UserAdd(String s, String s1, String s2, String s3, String s4, String s5) {
        UserReg user = new UserReg(s, s1, s2, s3, s4, s5);
        Call<UserReg> call1 = apiInterface.createUser(user);
        call1.enqueue(new Callback<UserReg>() {
            @Override
            public void onResponse(Call<UserReg> call, Response<UserReg> response) {

                Intent nxtPage = new Intent(getApplicationContext(), MainActivityAdmin.class);
                Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
                startActivity(nxtPage);

            }

            @Override
            public void onFailure(Call<UserReg> call, Throwable t) {
                call.cancel();
                Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();

            }
        });


    }


}
