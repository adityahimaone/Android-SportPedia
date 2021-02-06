package com.example.sportpedia.fragment;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportpedia.R;
import com.example.sportpedia.adapter.FavoriteAdapter;
import com.example.sportpedia.database.SQLiteDatabaseHandler;
import com.example.sportpedia.model.Team;

import java.util.ArrayList;

public class FavoriteFragment extends Fragment {
    private RecyclerView recyclerView;
    private FavoriteAdapter adapter;
    private ArrayList<Team> favArrayList;
    private SQLiteDatabaseHandler dbHelper;
    private static final int NOTIFICATION_ID = 2;
    private static final String CHANNEL_ID = "channel_02"; //custom
    private static final CharSequence CHANNEL_NAME = "my channel";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.fav_view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new SQLiteDatabaseHandler(requireContext());
        refresh();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new FavoriteAdapter(favArrayList, R.layout.list_favorite, requireContext());
        adapter.setOnItemClickCallback(new FavoriteAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Team data) {

            }

            @Override
            public void onItemDeleted(Team data) {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());

                builder.setTitle("Konfirmasi hapus");
                builder.setMessage("Apakah yakin akan dihapus?");

                builder.setPositiveButton("YA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbHelper.deleteUser(data.getIdTeam());
                        Toast.makeText(requireContext(), "Hapus berhasil!", Toast.LENGTH_SHORT).show();
                        refresh();
                        adapter.setListTeam(favArrayList);
                        sendNotification2();
                    }
                });

                builder.setNegativeButton("TIDAK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void refresh(){
        favArrayList = dbHelper.getFavorite();
    }

    public void sendNotification2(){
        NotificationManager notificationManager = (NotificationManager) requireContext().getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(requireContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_sports_soccer_24)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_baseline_sports_soccer_24))
                .setContentTitle(getResources().getString(R.string.content_title))
                .setContentText(getResources().getString(R.string.content_text2))
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
