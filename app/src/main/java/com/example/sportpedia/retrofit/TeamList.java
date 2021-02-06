package com.example.sportpedia.retrofit;

import com.example.sportpedia.model.Team;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TeamList{
    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    @SerializedName("teams")
    @Expose
    private List<Team> teams;
}

