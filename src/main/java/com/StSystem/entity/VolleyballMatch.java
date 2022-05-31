package com.StSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table (name = "volleyballmatch")


public class VolleyballMatch {

    @Id
    private int id;

    @Column(name = "TeamA")
    private String teamA;

    @Column(name = "TeamB")
    private String teamB;

    @Column(name = "match_date")
    private String date;

    @Column(name = "match_time")
    private String time;

    public VolleyballMatch(){
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

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", Team A Name='" + getTeamA() + '\'' +
                ", Team A Name='" + getTeamA() + '\'' +
                ", Match Date='" + getDate() + '\'' +
                ", Match Time='" + getTime() + '\'' +
                '}';
    }

}


