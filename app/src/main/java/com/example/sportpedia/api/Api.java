package com.example.sportpedia.api;

import com.example.sportpedia.retrofit.TeamList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String BASE_URL = "https://www.thesportsdb.com/api/v1/json/1/";

    @GET("search_all_teams.php?l=English%20Premier%20League")
    Call<TeamList> getTeams();

}
