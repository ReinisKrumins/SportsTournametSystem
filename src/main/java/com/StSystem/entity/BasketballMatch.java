package com.StSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.File;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;
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
    private long basketballMatchId;

    private String startDate;

    private String startTime;

    private String visitorTeam;

    private String visitorPts;

    private String homeTeam;

    private String homePts;

    private String arena;

}
