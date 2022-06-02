package com.StSystem.jobs;

import com.StSystem.entity.BasketballMatch;
import com.StSystem.service.BasketballMatchService;
import lombok.AllArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

@Configuration
@EnableScheduling
@AllArgsConstructor
@ConditionalOnProperty(name = "scheduling.enable", matchIfMissing = true)
public class WebScrapeBasketball {

    @Autowired
    private final BasketballMatchService basketballMatchService;
    // use cron to specify exact time when to execute (s, m, h, *, *, MON-SUN)
    // (cron = "* * * * * *")
    @Scheduled(fixedDelayString = "PT10s")
	void scrapeForBasketballGameData() throws Exception{

        basketballMatchService.clearOldData();

        BufferedWriter bw1 = new BufferedWriter(new FileWriter("src/main/resources/basketballGameData/bb-game-date.txt"));
        BufferedWriter bw2 = new BufferedWriter(new FileWriter("src/main/resources/basketballGameData/bb-game-time.txt"));
        BufferedWriter bw3 = new BufferedWriter(new FileWriter("src/main/resources/basketballGameData/bb-game-visitorTeam.txt"));
        BufferedWriter bw4 = new BufferedWriter(new FileWriter("src/main/resources/basketballGameData/bb-game-visitorPoints.txt"));
        BufferedWriter bw5 = new BufferedWriter(new FileWriter("src/main/resources/basketballGameData/bb-game-homeTeam.txt"));
        BufferedWriter bw6 = new BufferedWriter(new FileWriter("src/main/resources/basketballGameData/bb-game-homePoints.txt"));
        BufferedWriter bw7 = new BufferedWriter(new FileWriter("src/main/resources/basketballGameData/bb-game-arena.txt"));
        final String url = "https://www.basketball-reference.com/leagues/NBA_2022_games-may.html";

            final Document document = Jsoup.connect(url).get();
            // Read dates
            Elements dates = document.select("tbody > tr > .left:nth-of-type(1)");
            for (Element el: dates) {
                String sDate = el.text();
                bw1.write(el.text() + "\n");
            }
            bw1.close();

            // Read time
            Elements startTime = document.select("tbody > tr > .right:nth-of-type(1)");
            for (Element el: startTime) {
                String sTime = el.text();
                bw2.write(el.text() + "\n");
            }
            bw2.close();

            // Read visitor team
            Elements visitorTeam = document.select("tbody > tr > .left:nth-of-type(2)");
            for (Element el: visitorTeam)
                bw3.write(el.text() + "\n");
            bw3.close();

            // Read visitor team pts
            Elements pointsMadeVisitors = document.select("tbody > tr > .right:nth-of-type(3)");
            for (Element el: pointsMadeVisitors)
                bw4.write(el.text() + "\n");
            bw4.close();

            // Read home team
            Elements homeTeam = document.select("tbody > tr > .left:nth-of-type(4)");
            for (Element el: homeTeam)
                bw5.write(el.text() + "\n");
            bw5.close();

            // Read home team points
            Elements pointsMadeHome = document.select("tbody > tr > .right:nth-of-type(5)");
            for (Element el: pointsMadeHome)
                bw6.write(el.text() + "\n");
            bw6.close();

            // Arena where match takes place
            Elements arena = document.select("tbody > tr > .left:nth-of-type(9)");
            for (Element el: arena)
                bw7.write(el.text() + "\n");
            bw7.close();

            // Call function to insert all data in database
            insertBasketballData();
	}

    public void insertBasketballData() throws Exception{
        BasketballMatch basketballMatch = new BasketballMatch();
        // Read game start date
        Scanner input = new Scanner(new File("src/main/resources/basketballGameData/bb-game-date.txt"));
        Scanner input2 = new Scanner(new File("src/main/resources/basketballGameData/bb-game-time.txt"));
        Scanner input3 = new Scanner(new File("src/main/resources/basketballGameData/bb-game-visitorTeam.txt"));
        Scanner input4 = new Scanner(new File("src/main/resources/basketballGameData/bb-game-visitorPoints.txt"));
        Scanner input5 = new Scanner(new File("src/main/resources/basketballGameData/bb-game-homeTeam.txt"));
        Scanner input6 = new Scanner(new File("src/main/resources/basketballGameData/bb-game-homePoints.txt"));
        Scanner input7 = new Scanner(new File("src/main/resources/basketballGameData/bb-game-arena.txt"));

        while(input.hasNextLine()
                && input2.hasNextLine()
                && input3.hasNextLine()
                && input4.hasNextLine()
                && input5.hasNextLine()
                && input6.hasNextLine()
                && input7.hasNextLine()) {

            String line, line2, line3, line4, line5, line6, line7;

            line = input.nextLine();
            line2 = input2.nextLine();
            line3 = input3.nextLine();
            line4 = input4.nextLine();
            line5 = input5.nextLine();
            line6 = input6.nextLine();
            line7 = input7.nextLine();

            Scanner data = new Scanner(line);
            Scanner data2 = new Scanner(line2);
            Scanner data3 = new Scanner(line3);
            Scanner data4 = new Scanner(line4);
            Scanner data5 = new Scanner(line5);
            Scanner data6 = new Scanner(line6);
            Scanner data7 = new Scanner(line7);

            while (data.hasNextLine()
                    && data2.hasNextLine()
                    && data3.hasNextLine()
                    && data4.hasNextLine()
                    && data5.hasNextLine()
                    && data6.hasNextLine()
                    && data7.hasNextLine()) {
                basketballMatch.setStartDate(data.next() + " " + data.next() + " " + data.next());
                basketballMatch.setStartTime(data2.next() + "m ");
                basketballMatch.setVisitorTeam(data3.next() + " " + data3.next());
                basketballMatch.setVisitorPts(data4.next() + " ");
                basketballMatch.setHomeTeam(data5.next() + " " + data5.next());
                basketballMatch.setHomePts(data6.next() + " ");
                basketballMatch.setArena(data7.next() + " Arena");
            }
            basketballMatchService.insertWithQuery(basketballMatch);
        }
    }

}
