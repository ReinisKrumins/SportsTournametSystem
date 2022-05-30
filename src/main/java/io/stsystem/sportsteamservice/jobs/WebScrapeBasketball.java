package io.stsystem.sportsteamservice.jobs;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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
@ConditionalOnProperty(name = "scheduling.enable", matchIfMissing = true)
public class WebScrapeBasketball {

    // use cron to specify exact time when to execute (s, m, h, *, *, MON-SUN)
    // (cron = "* * * * * *")
    @Scheduled(fixedDelayString = "PT10M") // will run every 10 min
	void scrapeForBasketballGameData() throws Exception{

        BufferedWriter bw1 = new BufferedWriter(new FileWriter("src/main/resources/gameData/bb-game-date.txt"));
        BufferedWriter bw2 = new BufferedWriter(new FileWriter("src/main/resources/gameData/bb-game-time.txt"));
        BufferedWriter bw3 = new BufferedWriter(new FileWriter("src/main/resources/gameData/bb-game-visitorTeam.txt"));
        BufferedWriter bw4 = new BufferedWriter(new FileWriter("src/main/resources/gameData/bb-game-visitorPoints.txt"));
        BufferedWriter bw5 = new BufferedWriter(new FileWriter("src/main/resources/gameData/bb-game-homeTeam.txt"));
        BufferedWriter bw6 = new BufferedWriter(new FileWriter("src/main/resources/gameData/bb-game-homePoints.txt"));

        final String url = "https://www.basketball-reference.com/leagues/NBA_2022_games-may.html";

            final Document document = Jsoup.connect(url).get();

            // Read dates
            Elements dates = document.select("tbody > tr > .left:nth-of-type(1)");
            for (Element el: dates)
                bw1.write(el.text() + "\n");
            bw1.close();

            // Read time
            Elements startTime = document.select("tbody > tr > .right:nth-of-type(1)");
            for (Element el: startTime)
                bw2.write(el.text() + "\n");
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
	}
}
