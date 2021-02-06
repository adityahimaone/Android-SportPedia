package com.example.sportpedia;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.bumptech.glide.Glide;
import com.example.sportpedia.database.SQLiteDatabaseHandler;
import com.example.sportpedia.model.Team;

import java.util.List;

public class ItemDetailActivty extends AppCompatActivity {
    private List<Team> teams;
    public  static final String EXTRA_ID = "extra_id";
    public static final String EXTRA_NAME = "extra_name";
    public static final String EXTRA_IMG = "extra_img";
    public static final String EXTRA_DESKRIPSI = "extra_deskripsi";
    public static final String EXTRA_FORMED = "extra_formed";
    public static final String EXTRA_STADIUM = "extra_stadium";
    private static final int NOTIFICATION_ID = 1;
    private static final String CHANNEL_ID = "channel_01"; //custom
    private static final CharSequence CHANNEL_NAME = "my channel";

    SQLiteDatabaseHandler dbHelper;
    private int temp_id;
    private String temp_name;
    private String temp_img;
    private String temp_deskripsi;
    private String temp_formed;
    private String temp_stadium;
    private TextView tv_name, tv_att, tv_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dbHelper = new SQLiteDatabaseHandler(this);

        tv_id = findViewById(R.id.id_Team);
        tv_name = findViewById(R.id.tv_name);
        tv_att = findViewById(R.id.tv_formed);

        TextView id = findViewById(R.id.id_Team);
        TextView name = findViewById(R.id.tv_name);
        ImageView img = findViewById(R.id.photo_received);
        TextView deskripsi = findViewById(R.id.tv_deskripsi);
        TextView formed = findViewById(R.id.tv_formed);
        TextView stadium = findViewById(R.id.tv_stadium);

        temp_id = getIntent().getIntExtra(EXTRA_ID,0);
        temp_name = getIntent().getStringExtra(EXTRA_NAME);
        temp_img = getIntent().getStringExtra(EXTRA_IMG);
        temp_deskripsi = getIntent().getStringExtra(EXTRA_DESKRIPSI);
        temp_formed = getIntent().getStringExtra(EXTRA_FORMED);
        temp_stadium = getIntent().getStringExtra(EXTRA_STADIUM);

        id.setText(String.valueOf(temp_id));
        name.setText(temp_name);
        Glide.with(ItemDetailActivty.this)
                .load(temp_img)
                .into(img);
        deskripsi.setText(temp_deskripsi);
        formed.setText(temp_formed);
        stadium.setText(temp_stadium);


    }



    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.bottomnavigation_menu, menu);
        return true;
    }*/
    // create an action bar button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // R.menu.mymenu is a reference to an xml file named mymenu.xml which should be inside your res/menu directory.
        // If you don't have res/menu, just create a directory named "menu" inside res
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.bookmark) {
            // do something here
            sendNotification();
            dbHelper.addFavorite(Integer.parseInt(tv_id.getText().toString()),tv_name.getText().toString(), tv_att.getText().toString());
            Toast.makeText(ItemDetailActivty.this, "Data Berhasil di Bookmark!", Toast.LENGTH_SHORT).show();
        }else{
            onBackPressed();
        }

        return true;
    }

    public void sendNotification(){
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_sports_soccer_24)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_baseline_sports_soccer_24))
                .setContentTitle(getResources().getString(R.string.content_title))
                .setContentText(getResources().getString(R.string.content_text1))
                .setSubText(getResources().getString(R.string.subtext))
                .setAutoCancel(true);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription(CHANNEL_NAME.toString());
            builder.setChannelId(CHANNEL_ID);
            if (notificationManager != null){
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }

        Notification notification = builder.build();


        if(notificationManager != null ){
            notificationManager.notify(NOTIFICATION_ID, notification);
        }
    }

}
