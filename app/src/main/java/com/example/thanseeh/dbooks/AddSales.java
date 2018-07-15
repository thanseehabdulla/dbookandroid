package com.example.thanseeh.dbooks;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thanseeh.dbooks.retrofit.APIClient;
import com.example.thanseeh.dbooks.retrofit.SalesReg;
import com.example.thanseeh.dbooks.retrofit.UserReg;
import com.example.thanseeh.dbooks.retrofit.retro;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSales extends AppCompatActivity{


    public static EditText datess, total, tax, netsales;
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


        setContentView(R.layout.addsales);
        datess = (EditText) findViewById(R.id.date);
//        itemsold = (EditText) findViewById(R.id.itemsold);
        total = (EditText) findViewById(R.id.grosssales);
        tax = (EditText) findViewById(R.id.tax);
        netsales = (EditText) findViewById(R.id.netsales);

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

        datess.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(AddSales.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        datess.setSingleLine();
//        itemsold.setSingleLine();
        total.setSingleLine();
        tax.setSingleLine();
        netsales.setSingleLine();


        datess.setImeOptions(EditorInfo.IME_ACTION_NEXT);
//        itemsold.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        total.setImeOptions(EditorInfo.IME_ACTION_DONE);
        tax.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        netsales.setImeOptions(EditorInfo.IME_ACTION_NEXT);

        tax.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!((s.toString()).contentEquals("")) && !(netsales.getText().toString().contentEquals(""))){
                    Float tot = Float.parseFloat(netsales.getText().toString()) + Float.parseFloat(s.toString());
                    total.setText(String.valueOf(tot));
                }else if(!((s.toString()).contentEquals(""))){
                    Float tot = Float.parseFloat(s.toString());
                    total.setText(String.valueOf(tot));
                }else if(!(netsales.getText().toString().contentEquals(""))){
                    Float tot = Float.parseFloat(netsales.getText().toString());
                    total.setText(String.valueOf(tot));
                }else{
                    total.setText("0.0");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        netsales.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!((s.toString()).contentEquals("")) && !(tax.getText().toString().contentEquals(""))){
                    Float tot = Float.parseFloat(tax.getText().toString()) + Float.parseFloat(s.toString());
                    total.setText(String.valueOf(tot));
                }else if(!((s.toString()).contentEquals(""))){
                    Float tot = Float.parseFloat(s.toString());
                    total.setText(String.valueOf(tot));
                }else if(!(tax.getText().toString().contentEquals(""))){
                    Float tot = Float.parseFloat(tax.getText().toString());
                    total.setText(String.valueOf(tot));
                }else{
                    total.setText("0.0");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( datess.getText().toString().length() == 0 )
                    datess.setError( "Field is required!" );
                else if( total.getText().toString().length() == 0 )
                    total.setError( "Field is required!" );
                else if( tax.getText().toString().length() == 0 )
                    tax.setError( "Field is required!" );
                else if( netsales.getText().toString().length() == 0 )
                    netsales.setError( "Field is required!" );
                else
                UserAdd(datess.getText().toString(), total.getText().toString(), tax.getText().toString(), netsales.getText().toString());


            }
        });

    }

    private void updateLabel() {
        String myFormat = "MM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        datess.setText(sdf.format(myCalendar.getTime()));
    }

    private void UserAdd(String s, String s1, String s2, String s3) {
        SalesReg user = new SalesReg(s, Float.parseFloat(s1), Float.parseFloat(s2), Float.parseFloat(s3),getSharedPreferences("Log", MODE_PRIVATE).getInt("userid",0));
        Call<SalesReg> call1 = apiInterface.createSales(user);
        call1.enqueue(new Callback<SalesReg>() {
            @Override
            public void onResponse(Call<SalesReg> call, Response<SalesReg> response) {

                Intent nxtPage = new Intent(getApplicationContext(), MainActivity.class);
                Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
                startActivity(nxtPage);

            }

            @Override
            public void onFailure(Call<SalesReg> call, Throwable t) {
                call.cancel();
                Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();

            }
        });


    }


}
