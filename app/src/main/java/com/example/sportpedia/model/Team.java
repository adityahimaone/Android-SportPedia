package com.example.sportpedia.model;

import java.io.Serializable;


public class Team implements Serializable {
    private int idTeam;
    private String strTeam;
    private String strAlternate;
    private String strTeamLogo;
    private String intFormedYear;
    private String strDescriptionEN;
    private String strStadium;
    private String strStadiumThumb;

    public Team(int idTeam,String strTeam, String strAlternate, String strTeamLogo, String intFormedYear, String strDescriptionEN, String strStadium, String strStadiumThumb) {
        this.idTeam = idTeam;
        this.strTeam = strTeam;
        this.strAlternate = strAlternate;
        this.strTeamLogo = strTeamLogo;
        this.intFormedYear = intFormedYear;
        this.strDescriptionEN = strDescriptionEN;
        this.strStadium = strStadium;
        this.strStadiumThumb = strStadiumThumb;
    }

    public Team() {

    }

    public int getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(int idTeam) {

        this.idTeam = idTeam;
    }

    public String getStrTeam() {
        return strTeam;
    }

    public void setStrTeam(String strTeam) {
        this.strTeam = strTeam;
    }

    public String getStrAlternate() {
        return strAlternate;
    }

    public void setStrAlternate(String strAlternate) {
        this.strAlternate = strAlternate;
    }

    public String getStrTeamLogo() {
        return strTeamLogo;
    }

    public void setStrTeamLogo(String strTeamLogo) {
        this.strTeamLogo = strTeamLogo;
    }

    public String getIntFormedYear() {
        return intFormedYear;
    }

    public void setIntFormedYear(String intFormedYear) {
        this.intFormedYear = intFormedYear;
    }

    public String getStrDescriptionEN() {
        return strDescriptionEN;
    }

    public void setStrDescriptionEN(String strDescriptionEN) {
        this.strDescriptionEN = strDescriptionEN;
    }

    public String getStrStadium() {
        return strStadium;
    }

    public void setStrStadium(String strStadium) {
        this.strStadium = strStadium;
    }

    public String getStrStadiumThumb() {
        return strStadiumThumb;
    }

    public void setStrStadiumThumb(String strStadiumThumb) {
        this.strStadiumThumb = strStadiumThumb;
    }
}
