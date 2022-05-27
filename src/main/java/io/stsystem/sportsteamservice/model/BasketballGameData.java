package io.stsystem.sportsteamservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.File;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.sql.*;

@Entity
@Table(name = "basketball")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasketballGameData {

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

    public BasketballGameData(String startDate){
        setStartDate(startDate);
    }

    public void readBbGameDates() {
        // Read game start date
        try(Scanner input = new Scanner(new File("src/main/resources/gameData/bb-game-date.txt"))){
            while(input.hasNextLine()) {
                startDate = "";
                String line;

                line = input.nextLine();
                try (Scanner data = new Scanner(line)) {
                    while (data.hasNextLine()) {
                        startDate += data.next() + "";
                    }
                    startDate = startDate.trim(); // .trim() method removes white spaces
                    saveData();
                }
            }
            } catch(IOException e){
            System.out.println(e);
        }
    }
    // Read game start time
    public void readBbGameTime() throws Exception{
        Scanner input = new Scanner(new File("src/main/resources/gameData/bb-game-time.txt"));
            while(input.hasNextLine()) {
                startTime = "";
                String line;

                line = input.nextLine();
                Scanner data = new Scanner(line);
                    while (data.hasNextLine()) {
                        startTime += data.next() + "";
                    }
                    startTime = startTime.trim(); // .trim() method removes white spaces
                    saveData();
            }
        }
        // Read Visitor team name
        public void readBbVisitorTeam() throws Exception{
            Scanner input = new Scanner(new File("src/main/resources/gameData/bb-game-visitorTeam.txt"));
            while(input.hasNextLine()) {
                visitorTeam = "";
                String line;

                line = input.nextLine();
                Scanner data = new Scanner(line);
                while (data.hasNextLine()) {
                    visitorTeam += data.next() + "";
                }
                visitorTeam = visitorTeam.trim(); // .trim() method removes white spaces
                saveData();
            }
        }
    // Read Visitor team points
    public void readBbVisitorTeamPoints() throws Exception{
        Scanner input = new Scanner(new File("src/main/resources/gameData/bb-game-visitorPoints.txt"));
        while(input.hasNextLine()) {
            visitorPts = "";
            String line;

            line = input.nextLine();
            Scanner data = new Scanner(line);
            while (data.hasNextLine()) {
                visitorPts += data.next() + "";
            }
            visitorPts = visitorPts.trim(); // .trim() method removes white spaces
            saveData();
        }
    }
    // Read Home team
    public void readBbHomeTeam() throws Exception{
        Scanner input = new Scanner(new File("src/main/resources/gameData/bb-game-homeTeam.txt"));
        while(input.hasNextLine()) {
            homeTeam = "";
            String line;

            line = input.nextLine();
            Scanner data = new Scanner(line);
            while (data.hasNextLine()) {
                homeTeam += data.next() + "";
            }
            homeTeam = homeTeam.trim(); // .trim() method removes white spaces
            saveData();
        }
    }
    // Read Visitor team points
    public void readBbHomeTeamPoints() throws Exception{
        Scanner input = new Scanner(new File("src/main/resources/gameData/bb-game-homePoints.txt"));
        while(input.hasNextLine()) {
            homePts = "";
            String line;

            line = input.nextLine();
            Scanner data = new Scanner(line);
            while (data.hasNextLine()) {
                homePts += data.next() + "";
            }
            homePts = homePts.trim(); // .trim() method removes white spaces
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
                pstat.setString(4, startDate);
                pstat.setString(5, startTime);
                pstat.setString(7, visitorTeam);
                pstat.setString(6, visitorPts);
                pstat.setString(3, homeTeam);
                pstat.setString(2, homePts);

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

        BasketballGameData basketballGameData = new BasketballGameData();

        //basketballGameData.readBbGameDates();

        //basketballGameData.readBbGameTime();

        //basketballGameData.readBbVisitorTeam();

        //basketballGameData.readBbVisitorTeamPoints();

        //basketballGameData.readBbHomeTeam();

        //basketballGameData.readBbHomeTeamPoints();

    }
}
