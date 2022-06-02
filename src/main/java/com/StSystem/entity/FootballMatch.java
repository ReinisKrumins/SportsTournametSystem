package com.StSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "football")
public class FootballMatch {

    @Id
    private int id;

    @Column(name = "Team_A")
    private String teamA;


    @Column(name = "Team_B")
    private String teamB;

    @Column(name = "Match_Date")
    private String date;


    @Column(name = "Match_Time")
    private String time;

    @Column(name= "Groud")
    private String ground;

    @Column(name = "League")
    private String league;

    public FootballMatch() {
    }
    public FootballMatch(String teamA, String teamB, String date, String time, String ground, String league) {
        this.setTeamA(teamA);
        this.setTeamB(teamB);
        this.setDate(date);
        this.setTime(time);
        this.setGround(ground);
        this.setLeague(league);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeamA() {
        return teamA;
    }

    public void setTeamA(String teamA) {
        this.teamA = teamA;
    }

    public String getTeamB() {
        return teamB;
    }

    public void setTeamB(String teamB) {
        this.teamB = teamB;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getGround() { return ground; }

    public void setGround(String ground) { this.ground = ground; }

    public String getLeague() { return league; }

    public void setLeague(String league) { this.league = league; }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", Team A Name='" + getTeamA() + '\'' +
                ", Team A Name='" + getTeamA() + '\'' +
                ", Match Date='" + getDate() + '\'' +
                ", Match Time='" + getTime() + '\'' +
                ", Match League='" + getLeague() + '\'' +
                ", Match Ground='" + getGround() + '\'' +
                '}';
    }

}
