package com.example.sportpedia.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sportpedia.R;
import com.example.sportpedia.database.SQLiteDatabaseHandler;
import com.example.sportpedia.model.Team;

import java.util.ArrayList;
import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamViewHolder> {
    private ArrayList<Team> listTeam = new ArrayList<>();
    private Activity activity;
    private SQLiteDatabaseHandler dbHelper;

    public TeamAdapter(Activity activity) {
        this.activity = activity;
        dbHelper = new SQLiteDatabaseHandler(activity);
    }

    public ArrayList<Team> getListTeam() {
        return listTeam;
    }

    public void setListTeam(ArrayList<Team> listNotes) {
        if (listNotes.size() > 0) {
            this.listTeam.clear();
        }
        this.listTeam.addAll(listNotes);
        notifyDataSetChanged();
    }

    private OnItemClickCallback onItemClickCallback;
    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback){
        this.onItemClickCallback = onItemClickCallback;
    }

    private List<Team> teams;
    private int rowLayout;
    private Context context;

    public TeamAdapter(List<Team> teams, int rowLayout, Context context) {
        this.teams = teams;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @NonNull
    @Override
    public TeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new TeamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamViewHolder holder, int position) {
        holder.teamName.setText(teams.get(position).getStrTeam());
        holder.teamAlt.setText(teams.get(position).getStrAlternate());

        Glide.with(context)
                .load(teams.get(position).getStrTeamLogo())
                .into(holder.teamImage);

        holder.itemView.setOnClickListener(view -> {
            onItemClickCallback.onItemClicked(teams.get(holder.getAdapterPosition()));
        });
    }

    @Override
    public int getItemCount() {
        return teams.size();
    }

    public class TeamViewHolder extends RecyclerView.ViewHolder {
        CardView teamLayout;
        TextView teamName;
        TextView teamAlt;
        ImageView teamImage;

        public TeamViewHolder(@NonNull View itemView) {
            super(itemView);
            teamLayout = (CardView) itemView.findViewById(R.id.team_layout);
            teamName = (TextView) itemView.findViewById(R.id.tv_item_name);
            teamAlt = (TextView) itemView.findViewById(R.id.tv_item_detail);
            teamImage = (ImageView) itemView.findViewById(R.id.img_item_photo);
        }
    }

    public interface OnItemClickCallback{
        void onItemClicked(Team data);
    }
}
