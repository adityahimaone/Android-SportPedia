package com.example.sportpedia.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportpedia.ItemDetailActivty;
import com.example.sportpedia.R;
import com.example.sportpedia.retrofit.RetrofitClient;
import com.example.sportpedia.model.Team;
import com.example.sportpedia.adapter.TeamAdapter;
import com.example.sportpedia.retrofit.TeamList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamsFragment extends Fragment {
    private RecyclerView recyclerView = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teams, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        getTeams();
    }

    private void getTeams(){
        Call<TeamList> call = RetrofitClient.getInstance().getMyApi().getTeams();
        call.enqueue(new Callback<TeamList>() {
            @Override
            public void onResponse(Call<TeamList> call, Response<TeamList> response) {
                List<Team> teamList = response.body().getTeams();
                TeamAdapter teamAdapter = new TeamAdapter(teamList, R.layout.list_team, requireContext());
                teamAdapter.setOnItemClickCallback(new TeamAdapter.OnItemClickCallback() {
                    @Override
                    public void onItemClicked(Team data) {
                        Log.v("dbdbdb",data.getStrTeam());
                        Intent moveIntentWithData = new Intent(requireContext(), ItemDetailActivty.class);
                        moveIntentWithData.putExtra(ItemDetailActivty.EXTRA_ID, data.getIdTeam());
                        moveIntentWithData.putExtra(ItemDetailActivty.EXTRA_NAME, data.getStrTeam());
                        moveIntentWithData.putExtra(ItemDetailActivty.EXTRA_IMG, data.getStrTeamLogo());
                        moveIntentWithData.putExtra(ItemDetailActivty.EXTRA_DESKRIPSI, data.getStrDescriptionEN());
                        moveIntentWithData.putExtra(ItemDetailActivty.EXTRA_FORMED, data.getIntFormedYear());
                        moveIntentWithData.putExtra(ItemDetailActivty.EXTRA_STADIUM, data.getStrStadium());
                        startActivity(moveIntentWithData);
                    }
                });
                recyclerView.setAdapter(teamAdapter);
                Log.v("teams", teamList.toString());
            }


            @Override
            public void onFailure(Call<TeamList> call, Throwable t) {
                Log.v("error", t.getMessage().toString());
            }
        });
    }


}
