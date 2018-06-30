package com.example.thanseeh.dbooks;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thanseeh.dbooks.retrofit.APIClient;
import com.example.thanseeh.dbooks.retrofit.UserReg;
import com.example.thanseeh.dbooks.retrofit.retro;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPurchase extends AppCompatActivity{


    public static EditText vendername, trnno, datainvoice, amount, vat, total,invoicenumber;
    public static  Button submit;
    retro apiInterface;
    Calendar myCalendar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
              Window window = this.getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this,R.color.my_statusbar_color));
        }


        setContentView(R.layout.adduser);
        vendername = (EditText) findViewById(R.id.vendername);
        trnno = (EditText) findViewById(R.id.trnno);
        datainvoice = (EditText) findViewById(R.id.datainvoice);
        amount = (EditText) findViewById(R.id.amount);
        vat = (EditText) findViewById(R.id.vat);
        total = (EditText) findViewById(R.id.total);
        invoicenumber = (EditText) findViewById(R.id.invoicenumber);
        submit = (Button) findViewById(R.id.button);
        apiInterface = APIClient.getClient().create(retro.class);

        myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        datainvoice.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(AddPurchase.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        vendername.setSingleLine();
        trnno.setSingleLine();
        datainvoice.setSingleLine();
        amount.setSingleLine();
        vat.setSingleLine();
        total.setSingleLine();
        invoicenumber.setSingleLine();

        vendername.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        trnno.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        datainvoice.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        amount.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        vat.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        total.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        invoicenumber.setImeOptions(EditorInfo.IME_ACTION_DONE);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( vendername.getText().toString().length() == 0 )
                    vendername.setError( "Field is required!" );
                else if( trnno.getText().toString().length() == 0 )
                    trnno.setError( "Field is required!" );
                else if( datainvoice.getText().toString().length() == 0 )
                    datainvoice.setError( "Field is required!" );
                else if( amount.getText().toString().length() == 0 )
                    amount.setError( "Field is required!" );
                else if( vat.getText().toString().length() == 0 )
                    vat.setError( "Field is required!" );
                else if( total.getText().toString().length() == 0 )
                    total.setError( "Field is required!" );
                else if( invoicenumber.getText().toString().length() == 0 )
                    invoicenumber.setError( "Field is required!" );
                else
                UserAdd(vendername.getText().toString(), trnno.getText().toString(), datainvoice.getText().toString(), amount.getText().toString(), vat.getText().toString(), total.getText().toString(),invoicenumber.getText().toString());


            }
        });

    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        datainvoice.setText(sdf.format(myCalendar.getTime()));
    }

    private void UserAdd(String s, String s1, String s2, String s3, String s4, String s5, String s6) {
        UserReg user = new UserReg(s, Float.parseFloat(s1), s2, Float.parseFloat(s3), Float.parseFloat(s4), Float.parseFloat(s5),s6);
        Call<UserReg> call1 = apiInterface.createUser(user);
        call1.enqueue(new Callback<UserReg>() {
            @Override
            public void onResponse(Call<UserReg> call, Response<UserReg> response) {

                Intent nxtPage = new Intent(getApplicationContext(), MainActivity.class);
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
