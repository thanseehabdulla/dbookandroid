package com.example.thanseeh.dbooks;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.File;
import java.util.Locale;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * An activity representing a single Item detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link ItemListActivity}.
 */
public class ItemDetailActivity2 extends AppCompatActivity {
    NotificationManagerCompat notificationManager;
    android.content.Context c;
    NotificationCompat.Builder mBuilder;

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


        setContentView(R.layout.activity_item_detail2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        notificationManager = NotificationManagerCompat.from(this);
        mBuilder = new NotificationCompat.Builder(this, "1")
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("Xls file created")
                .setContentText("please open with file manager")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString("id",
                    getIntent().getStringExtra("id"));
            arguments.putString("date", getIntent().getStringExtra("date"));
            arguments.putString("net_sales", getIntent().getStringExtra("net_sales"));
            arguments.putString("tax", getIntent().getStringExtra("tax"));
            arguments.putString("net_total", getIntent().getStringExtra("net_total"));
            ItemDetailFragment2 fragment = new ItemDetailFragment2();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.item_detail_container, fragment)
                    .commit();
        }


//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Creating Xls file", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                File sd = Environment.getExternalStorageDirectory();
//                String csvFile = getIntent().getStringExtra("vendername") + ".xls";
//                File directory = new File(sd.getAbsolutePath() + "/dbook");
//                //create directory if not exist
//                if (!directory.isDirectory()) {
//                    directory.mkdirs();
//                }
//
//                try {
//
//                    //file path
//                    File file = new File(directory, csvFile);
//                    WorkbookSettings wbSettings = new WorkbookSettings();
//                    wbSettings.setLocale(new Locale("en", "EN"));
//                    WritableWorkbook workbook;
//                    workbook = Workbook.createWorkbook(file, wbSettings);
//                    //Excel sheet name. 0 represents first sheet
//                    WritableSheet sheet = workbook.createSheet("userList", 0);
//
////                    sheet.addCell(new Label(0, 0, "vender name")); // column and row
////                    sheet.addCell(new Label(1, 0, "trn no"));
////                    sheet.addCell(new Label(2, 0, "date invoice"));
////                    sheet.addCell(new Label(3, 0, "amount"));
////                    sheet.addCell(new Label(4, 0, "vat"));
////                    sheet.addCell(new Label(5, 0, "total"));
////                    sheet.addCell(new Label(6, 0, "invoice number"));
////
////                    sheet.addCell(new Label(0, 1, getIntent().getStringExtra("vendername")));
////                    sheet.addCell(new Label(1, 1, getIntent().getStringExtra("trn_no")));
////                    sheet.addCell(new Label(2, 1, getIntent().getStringExtra("date_invoice")));
////                    sheet.addCell(new Label(3, 1, getIntent().getStringExtra("amount")));
////                    sheet.addCell(new Label(4, 1, getIntent().getStringExtra("vat")));
////                    sheet.addCell(new Label(5, 1, getIntent().getStringExtra("total")));
////                    sheet.addCell(new Label(6, 1, getIntent().getStringExtra("invoice_number")));
//
//
//                    workbook.write();
//                    workbook.close();
//
//// notificationId is a unique int for each notification that you must define
//                    notificationManager.notify(1, mBuilder.build());
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setDataAndType(Uri.parse(sd.getAbsolutePath() + "/dbook/" + getIntent().getStringExtra("name") + ".xls"), "application/vnd.ms-excel");
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//                try {
//                    startActivity(intent);
//                } catch (ActivityNotFoundException e) {
//                    Toast.makeText(c, "No Application Available to View Excel",
//                            Toast.LENGTH_SHORT).show();
//                }
//            }
//        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            NavUtils.navigateUpTo(this, new Intent(this, ItemListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
