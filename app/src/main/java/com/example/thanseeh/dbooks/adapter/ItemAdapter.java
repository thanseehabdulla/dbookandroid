package com.example.thanseeh.dbooks.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thanseeh.dbooks.AddPurchase;
import com.example.thanseeh.dbooks.AddSales;
import com.example.thanseeh.dbooks.ItemListActivity;
import com.example.thanseeh.dbooks.ItemListActivity2;
import com.example.thanseeh.dbooks.MainActivity;
import com.example.thanseeh.dbooks.R;
import com.example.thanseeh.dbooks.model.HomeItems;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {
    Context c;
    private List<HomeItems> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public RelativeLayout itemClick;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            itemClick = (RelativeLayout) view.findViewById(R.id.itemclick);
            c=view.getContext();

        }
    }


    public ItemAdapter(List<HomeItems> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final HomeItems movie = moviesList.get(position);
        holder.title.setText(movie.getItems());
        holder.itemClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(movie.getItems().contentEquals("Purchase List")){
                    Intent nxtPage = new Intent(c.getApplicationContext(), ItemListActivity.class);
                    c.startActivity(nxtPage);

                }else if(movie.getItems().contentEquals("Sales List")){
                    Intent nxtPage = new Intent(c.getApplicationContext(), ItemListActivity2.class);
                    c.startActivity(nxtPage);

                }else if(movie.getItems().contentEquals("Add Purchase")){
                    Intent nxtPage = new Intent(c.getApplicationContext(), AddPurchase.class);
                    c.startActivity(nxtPage);


                }else if(movie.getItems().contentEquals("Add Sales")){
                    Intent nxtPage = new Intent(c.getApplicationContext(), AddSales.class);
                    c.startActivity(nxtPage);

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
