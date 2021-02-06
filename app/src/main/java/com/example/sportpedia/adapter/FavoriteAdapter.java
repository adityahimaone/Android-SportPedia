package com.example.sportpedia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportpedia.R;
import com.example.sportpedia.model.Team;

import java.util.ArrayList;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavViewHolder> {
    private ArrayList<Team> listFav = new ArrayList<>();
    private int rowLayout;
    private Context context;

    private OnItemClickCallback onItemClickCallback;
    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback){
        this.onItemClickCallback = onItemClickCallback;
    }

    public FavoriteAdapter(ArrayList<Team> listFav, int rowLayout, Context context) {
        this.listFav = listFav;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    public void setListTeam(ArrayList<Team> listNotes) {
        if (listNotes.size() > 0) {
            this.listFav.clear();
        }
        this.listFav.addAll(listNotes);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public FavViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new FavViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.FavViewHolder holder, int position) {
        holder.favName.setText(listFav.get(position).getStrTeam());
        holder.favFormed.setText(listFav.get(position).getIntFormedYear());

        holder.itemView.setOnClickListener(view -> {
            onItemClickCallback.onItemClicked(listFav.get(holder.getAdapterPosition()));
        });

        holder.btnDelete.setOnClickListener(view ->{
            onItemClickCallback.onItemDeleted(listFav.get(holder.getAdapterPosition()));
        });

    }

    public class FavViewHolder extends RecyclerView.ViewHolder {
        CardView favLayout;
        TextView favName;
        TextView favFormed;
        Button btnDelete;
        //ImageView favImg;

        public FavViewHolder(@NonNull View itemView) {
            super(itemView);
            favLayout = (CardView) itemView.findViewById(R.id.card_favorite);
            favName = (TextView) itemView.findViewById(R.id.tv_name_fav);
            favFormed = (TextView) itemView.findViewById(R.id.tv_formed_fav);
            //favImg = (ImageView) itemView.findViewById(R.id.img_item_photo);
            btnDelete = itemView.findViewById(R.id.btn_delete);
        }
    }
    @Override
    public int getItemCount() {
        return listFav.size();
    }

    public interface OnItemClickCallback{
        void onItemClicked(Team data);
        void onItemDeleted(Team data);
    }

}
