package com.cs60333.jwang28.lab2_jwang28;

import java.io.Serializable;

/**
 * Created by Ivy Wang on 3/1/2017.
 */

public class Team implements Serializable {
    String teamName;
    String teamLogo;
    String gameDate;
    String gamePlace;
    String score;
    String leftteamLogo;
    String rightteamLogo;
    String leftTeam;
    String leftNick;
    String leftScore;
    String rightTeam;
    String rightNick;
    String rightScore;
    // define all the strings that you need to fill all the TextViews  of activity_detail.
    public Team (String[] teaminfo) {
        setTeamName(teaminfo[1]);
       /// setTeamName(right_team_name);
        setTeamLogo(teaminfo[0]);
        setLeftTeamLogo(teaminfo[11]);
        setRightTeamLogo(teaminfo[12]);
        setDate(teaminfo[2]);
        setPlace(teaminfo[3]);
        setLteam(teaminfo[4]);
        setLnick(teaminfo[5]);
        setLscore(teaminfo[6]);
        setRteam(teaminfo[8]);
        setRnick(teaminfo[9]);
        setRscore(teaminfo[10]);
       /// setTeamLogo(right_team_logo);
        setScore(teaminfo[7]);
       // setGameDate(teaminfo[]);

    }

    public void setTeamName(String team_name) {
        this.teamName = team_name;
    }

    public String getTeamName() {
        return this.teamName;
    }

    public void setTeamLogo(String team_logo) {
        this.teamLogo = team_logo;
    }

    public String getTeamLogo() {
        return this.teamLogo;
    }

    public void setLeftTeamLogo(String left_team_logo) { this.leftteamLogo = left_team_logo; }

    public String getLeftTeamLogo() { return this.leftteamLogo; }
    public void setRightTeamLogo(String right_team_logo) { this.rightteamLogo = right_team_logo; }

    public String getRightTeamLogo() { return this.rightteamLogo; }

    public void setScore(String score) {
        this.score = score;
    }

    public String getScore() {
        return this.score;
    }

    public void setDate(String game_date) {
        this.gameDate = game_date;
    }

    public String getDate() {
        return this.gameDate;
    }

    public void setPlace(String game_place) {
        this.gamePlace = game_place;
    }

    public String getPlace() {
        return this.gamePlace;
    }

    public void setLteam(String left_team) {
        this.leftTeam = left_team;
    }

    public String getLteam() {
        return this.leftTeam;
    }

    public void setLnick(String left_nick) {
        this.leftNick = left_nick;
    }

    public String getLnick() {
        return this.leftNick;
    }

    public void setLscore(String left_score) {
        this.leftScore = left_score;
    }

    public String getLscore() {
        return this.leftScore;
    }

    public void setRteam(String right_team) {
        this.rightTeam = right_team;
    }

    public String getRteam() {
        return this.rightTeam;
    }

    public void setRnick(String right_nick) {
        this.rightNick = right_nick;
    }

    public String getRnick() {
        return this.rightNick;
    }

    public void setRscore(String right_score) {
        this.rightScore = right_score;
    }

    public String getRscore() {
        return this.rightScore;
    }
}