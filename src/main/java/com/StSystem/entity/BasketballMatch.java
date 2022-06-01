package com.StSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.File;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.sql.*;

@Entity
@Table(name = "basketball")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class BasketballMatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long basketballMatchId;
    @Column
    private String startDate;
    @Column
    private String startTime;
    @Column
    private String visitorTeam;
    @Column
    private String visitorPts;
    @Column
    private String homeTeam;
    @Column
    private String homePts;

}
