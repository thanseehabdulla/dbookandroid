package com.example.thanseeh.dbooks;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.thanseeh.dbooks.retrofit.APIClient;
import com.example.thanseeh.dbooks.retrofit.UserPojo;
import com.example.thanseeh.dbooks.retrofit.UserReg;
import com.example.thanseeh.dbooks.retrofit.VenderPojo;
import com.example.thanseeh.dbooks.retrofit.retro;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPurchase extends AppCompatActivity{


    public static EditText  trnno, datainvoice, amount, vat, total,invoicenumber;
    public static  Button submit;
    public static Spinner vendername;
    retro apiInterface;
    Calendar myCalendar;
    private String item_name;
    private List<VenderPojo.user> datumList;
    List<String> categories;

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
        vendername = (Spinner) findViewById(R.id.vendername);
        trnno = (EditText) findViewById(R.id.trnno);
        datainvoice = (EditText) findViewById(R.id.datainvoice);
        amount = (EditText) findViewById(R.id.amount);
        vat = (EditText) findViewById(R.id.vat);
        total = (EditText) findViewById(R.id.total);
        invoicenumber = (EditText) findViewById(R.id.invoicenumber);
        submit = (Button) findViewById(R.id.button);
        apiInterface = APIClient.getClient().create(retro.class);
        total.setText("0.0");
        myCalendar = Calendar.getInstance();

        Call<VenderPojo> call = apiInterface.doGetListResources2();
        call.enqueue(new Callback<VenderPojo>() {
            @Override
            public void onResponse(Call<VenderPojo> call, Response<VenderPojo> response) {


                Log.d("TAG", response.code() + "");
                categories = new ArrayList<String>();
                String displayResponse = "";

                VenderPojo resource = response.body();
                datumList = resource.data;

                for (VenderPojo.user datum : datumList) {
                    categories.add(datum.name+"---"+datum.trn_no);
                }

                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, categories);

                // Drop down layout style - list view with radio button
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                // attaching data adapter to spinner
                vendername.setAdapter(dataAdapter);

            }

            @Override
            public void onFailure(Call<VenderPojo> call, Throwable t) {
                call.cancel();

            }
        });




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


// Spinner Drop down elements


        // Creating adapter for spinner

        amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!((s.toString()).contentEquals("")) && !(vat.getText().toString().contentEquals(""))){
                Float tot = Float.parseFloat(vat.getText().toString()) + Float.parseFloat(s.toString());
                total.setText(String.valueOf(tot));
            }else if(!((s.toString()).contentEquals(""))){
                Float tot = Float.parseFloat(s.toString());
                total.setText(String.valueOf(tot));
            }else if(!(vat.getText().toString().contentEquals(""))){
                    Float tot = Float.parseFloat(vat.getText().toString());
                    total.setText(String.valueOf(tot));
                }else{
                    total.setText("0.0");
                }
                }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        vat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!((s.toString()).contentEquals("")) && !(amount.getText().toString().contentEquals(""))){
                    Float tot = Float.parseFloat(amount.getText().toString()) + Float.parseFloat(s.toString());
                    total.setText(String.valueOf(tot));
                }else if(!((s.toString()).contentEquals(""))){
                    Float tot = Float.parseFloat(s.toString());
                    total.setText(String.valueOf(tot));
                }else if(!(amount.getText().toString().contentEquals(""))){
                    Float tot = Float.parseFloat(amount.getText().toString());
                    total.setText(String.valueOf(tot));
                }else{
                    total.setText("0.0");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

// Spinner click listener
        vendername.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item_name = parent.getItemAtPosition(position).toString();
                trnno.setText(vendername.getSelectedItem().toString().split("---")[1]);
                // Showing selected spinner item
                Toast.makeText(parent.getContext(), "Selected: " + item_name, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        vendername.setSingleLine();
        trnno.setSingleLine();
        datainvoice.setSingleLine();
        amount.setSingleLine();
        vat.setSingleLine();
        total.setSingleLine();
        total.setText("0.0");
        invoicenumber.setSingleLine();

//        vendername.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        trnno.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        datainvoice.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        amount.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        vat.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        total.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        invoicenumber.setImeOptions(EditorInfo.IME_ACTION_NEXT);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if( trnno.getText().toString().length() == 0 )
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
                UserAdd(item_name.split("---")[0], trnno.getText().toString(), datainvoice.getText().toString(), amount.getText().toString(), vat.getText().toString(), total.getText().toString(),invoicenumber.getText().toString());
                }
        });

    }

    private void updateLabel() {
        String myFormat = "MM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        datainvoice.setText(sdf.format(myCalendar.getTime()));
    }

    private void UserAdd(String s, String s1, String s2, String s3, String s4, String s5, String s6) {
        UserReg user = new UserReg(s, s1, s2, Float.parseFloat(s3), Float.parseFloat(s4), Float.parseFloat(s5),s6,getSharedPreferences("Log", MODE_PRIVATE).getInt("userid",0));
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
