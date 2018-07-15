package com.example.thanseeh.dbooks;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thanseeh.dbooks.retrofit.APIClient;
import com.example.thanseeh.dbooks.retrofit.SalesPojo;
import com.example.thanseeh.dbooks.retrofit.retro;

import java.io.File;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ItemListActivity2 extends AppCompatActivity {

    retro apiInterface;
    NotificationManagerCompat notificationManager;
    Context c;
    NotificationCompat.Builder mBuilder;
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private String newList;
    private List<SalesPojo.user> datumList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.my_statusbar_color));
        }


        setContentView(R.layout.activity_item_list2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
        apiInterface = APIClient.getClient().create(retro.class);
        notificationManager = NotificationManagerCompat.from(this);
        mBuilder = new NotificationCompat.Builder(this, "1")
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("Xls file created")
                .setContentText("please open with file manager")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Creating Xls file", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                File sd = Environment.getExternalStorageDirectory();
                String csvFile = "purchase.xls";
                File directory = new File(sd.getAbsolutePath() + "/dbook");
                //create directory if not exist
                if (!directory.isDirectory()) {
                    directory.mkdirs();
                }

                try {

                    //file path
//                    File file = new File(directory, csvFile);
//                    WorkbookSettings wbSettings = new WorkbookSettings();
//                    wbSettings.setLocale(new Locale("en", "EN"));
//                    WritableWorkbook workbook;
//                    workbook = Workbook.createWorkbook(file, wbSettings);
//                    //Excel sheet name. 0 represents first sheet
//                    WritableSheet sheet = workbook.createSheet("userList", 0);
//
//                    sheet.addCell(new Label(0, 0, "Vender Name")); // column and row
//                    sheet.addCell(new Label(1, 0, "trn no"));
//                    sheet.addCell(new Label(2, 0, "date invoice")); // column and row
//                    sheet.addCell(new Label(3, 0, "amount"));
//                    sheet.addCell(new Label(4, 0, "vat")); // column and row
//                    sheet.addCell(new Label(5, 0, "total")); // column and row
//                    sheet.addCell(new Label(6, 0, "invoice number")); // column and row
//                    int i = 1;
//                    for (SalesPojo.user datum : datumList) {
//
//                        sheet.addCell(new Label(0, i, datum.vendername));
//                        sheet.addCell(new Label(1, i, String.valueOf(datum.trn_no)));
//                        sheet.addCell(new Label(2, i, datum.date_invoice));
//                        sheet.addCell(new Label(3, i, String.valueOf(datum.amount)));
//                        sheet.addCell(new Label(4, i, String.valueOf(datum.vat)));
//                        sheet.addCell(new Label(5, i, String.valueOf(datum.amount)));
//                        sheet.addCell(new Label(6, i, datum.invoice_number));
//                        i++;
//
//                    }
//
//
//                    workbook.write();
//                    workbook.close();

// notificationId is a unique int for each notification that you must define
                    notificationManager.notify(1, mBuilder.build());

                } catch (Exception e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse(sd.getAbsolutePath() + "/dbook/purchase.xls"), "application/vnd.ms-excel");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(c, "No Application Available to View Excel",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        final View recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;
        Call<SalesPojo> call = apiInterface.doGetListResources3(getSharedPreferences("Log", MODE_PRIVATE).getInt("userid", 0));
        call.enqueue(new Callback<SalesPojo>() {
            @Override
            public void onResponse(Call<SalesPojo> call, Response<SalesPojo> response) {


                Log.d("TAG", response.code() + "");

                String displayResponse = "";

                SalesPojo resource = response.body();
                datumList = resource.data;

//                for (UserPojo.user datum : datumList) {
//                    newList +=  datum.name;
//                }


                setupRecyclerView((RecyclerView) recyclerView, datumList);
            }

            @Override
            public void onFailure(Call<SalesPojo> call, Throwable t) {
                call.cancel();
            }
        });

    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView, List<SalesPojo.user> datumList) {

        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, datumList, mTwoPane));
    }

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final ItemListActivity2 mParentActivity;
        private final List<SalesPojo.user> mValues;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SalesPojo.user item = (SalesPojo.user) view.getTag();
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString("id", String.valueOf(item.id));
                    arguments.putString("date", item.date);
                    arguments.putString("net_sales", String.valueOf(item.net_sales));
                    arguments.putString("tax", String.valueOf(item.tax));
                    arguments.putString("net_total", String.valueOf(item.net_total));
                    ItemDetailFragment2 fragment = new ItemDetailFragment2();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.item_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ItemDetailActivity2.class);
                    intent.putExtra("id", String.valueOf(item.id));
                    intent.putExtra("date", item.date);
                    intent.putExtra("net_sales", String.valueOf(item.net_sales));
                    intent.putExtra("tax", String.valueOf(item.tax));
                    intent.putExtra("net_total", String.valueOf(item.net_total));
                    context.startActivity(intent);
                }
            }
        };

        SimpleItemRecyclerViewAdapter(ItemListActivity2 parent,
                                      List<SalesPojo.user> items,
                                      boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
//            holder.mIdView.setText(String.valueOf(mValues.get(position).id));
            try {
                holder.mContentView.setText(mValues.get(position).date + " -AED " +mValues.get(position).net_total);

                holder.itemView.setTag(mValues.get(position));
                holder.itemView.setOnClickListener(mOnClickListener);
            } catch (Exception e) {

            }
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView mIdView;
            final TextView mContentView;

            ViewHolder(View view) {
                super(view);
                mIdView = (TextView) view.findViewById(R.id.id_text);
                mContentView = (TextView) view.findViewById(R.id.content);
            }
        }
    }
}
