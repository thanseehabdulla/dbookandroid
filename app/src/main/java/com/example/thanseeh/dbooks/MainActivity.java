package com.example.thanseeh.dbooks;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.thanseeh.dbooks.adapter.ItemAdapter;
import com.example.thanseeh.dbooks.model.HomeItems;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<HomeItems> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ItemAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextPage = new Intent(MainActivity.this,LoginActivity.class);
                getSharedPreferences("Log",MODE_PRIVATE).edit().putString("logged","null").apply();
                startActivity(nextPage);
                finish();
            }
        });

        mAdapter = new ItemAdapter(movieList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareMovieData();
    }


    private void prepareMovieData() {
        HomeItems movie = new HomeItems("Purchase List");
        movieList.add(movie);

        HomeItems movie2 = new HomeItems("Sales List");
        movieList.add(movie2);

        HomeItems movie3 = new HomeItems("Add Purchase");
        movieList.add(movie3);

        HomeItems movie4 = new HomeItems("Add Sales");
        movieList.add(movie4);



        mAdapter.notifyDataSetChanged();
    }

}
