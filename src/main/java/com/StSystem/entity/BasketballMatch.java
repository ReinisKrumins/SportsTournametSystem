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
    private long gameId;
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

    public BasketballMatch(String startDate){
        setStartDate(startDate);
    }

    public void readBbGameData() throws Exception{
        // Read game start date
        Scanner input = new Scanner(new File("src/main/resources/gameData/bb-game-date.txt"));
        Scanner input2 = new Scanner(new File("src/main/resources/gameData/bb-game-time.txt"));
        Scanner input3 = new Scanner(new File("src/main/resources/gameData/bb-game-visitorTeam.txt"));
        Scanner input4 = new Scanner(new File("src/main/resources/gameData/bb-game-visitorPoints.txt"));
        Scanner input5 = new Scanner(new File("src/main/resources/gameData/bb-game-homeTeam.txt"));
        Scanner input6 = new Scanner(new File("src/main/resources/gameData/bb-game-homePoints.txt"));

        while(input.hasNextLine()
                && input2.hasNextLine()
                && input3.hasNextLine()
                && input4.hasNextLine()
                && input5.hasNextLine()
                && input5.hasNextLine()) {

                String line, line2, line3, line4, line5, line6;

                line = input.nextLine();
                line2 = input2.nextLine();
                line3 = input3.nextLine();
                line4 = input4.nextLine();
                line5 = input5.nextLine();
                line6 = input6.nextLine();

                Scanner data = new Scanner(line);
                Scanner data2 = new Scanner(line2);
                Scanner data3 = new Scanner(line3);
                Scanner data4 = new Scanner(line4);
                Scanner data5 = new Scanner(line5);
                Scanner data6 = new Scanner(line6);

                    while (data.hasNextLine()
                            && data2.hasNextLine()
                            && data3.hasNextLine()
                            && data4.hasNextLine()
                            && data5.hasNextLine()
                            && data6.hasNextLine()) {
                        setStartDate(data.next() + " " + data.next() + " " + data.next());
                        setStartTime(data2.next() + " ");
                        setVisitorTeam(data3.next() + " " + data3.next());
                        setVisitorPts(data4.next() + " ");
                        setHomeTeam(data5.next() + " " + data5.next());
                        setHomePts(data6.next() + " ");
                    }
//                    startDate = startDate.trim(); // .trim() method removes white spaces
//                    startTime = startTime.trim(); // .trim() method removes white spaces
//                    visitorTeam = visitorTeam.trim(); // .trim() method removes white spaces
//                    visitorPts = visitorPts.trim(); // .trim() method removes white spaces
//                    homeTeam = homeTeam.trim(); // .trim() method removes white spaces
//                    homePts = homePts.trim(); // .trim() method removes white spaces

                    saveData();
                }
    }
    // Save data to db
    private void saveData(){
        try (Connection conn = connect()) {
            try (PreparedStatement pstat = conn.prepareCall(
                    "INSERT INTO basketball (game_id, home_pts, home_team, start_date, start_time, visitor_pts, visitor_team)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)"
            )) {

                pstat.setLong(1, getGameId());
                pstat.setString(4, getStartDate());
                pstat.setString(5, getStartTime());
                pstat.setString(7, getVisitorTeam());
                pstat.setString(6, getVisitorPts());
                pstat.setString(3, getHomeTeam());
                pstat.setString(2, getHomePts());

                pstat.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    // Conncetion to database
    private Connection connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection("jdbc:mysql://localhost:3306/stsystemdb?useSSL=false", "root", "root");
        }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }
}

class FDemo{
    public static void main(String[] args) throws Exception{

        BasketballMatch basketballMatch = new BasketballMatch();
        basketballMatch.readBbGameData();

    }
}
