package com.StSystem.jobs;

import com.StSystem.entity.VolleyballMatch;
import com.StSystem.service.VolleyballMatchesService;
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
@ConditionalOnProperty(name = "scheduling.enable", matchIfMissing = true)
public class WebScrapeVolleyball {

    @Autowired
    VolleyballMatchesService volleyballMatchService;
    // use cron to specify exact time when to execute (s, m, h, *, *, MON-SUN)
    // (cron = "* * * * * *")
    //@Scheduled(fixedDelayString = "PT10S")
    void scrapeForBasketballGameData() throws Exception{

     //   volleyballMatchService.clearOldData();

        final String url = "https://www.hockey-reference.com/leagues/NHL_2022_games.html";
        try {

            final Document document = Jsoup.connect(url).get();
            // Read dates
            Elements dates = document.select("tbody > tr > .left:nth-of-type(1)");

            // Read time
            Elements startTime = document.select("tbody > tr > .right:nth-of-type(8)");

            // Read team B
            Elements visitorTeam = document.select("tbody > tr > .left:nth-of-type(2)");
            for (Element el : visitorTeam) {
            }

            // Read team A
            Elements homeTeam = document.select("tbody > tr > .left:nth-of-type(4)");


                for (int i = 0; i < dates.size(); i++) {
                    VolleyballMatch volleyballMatch = new VolleyballMatch();
                    volleyballMatch.setDate(dates.get(i).toString());
                    volleyballMatch.setTime(startTime.get(i).toString());
                    volleyballMatch.setTeamA(homeTeam.get(i).toString());
                    volleyballMatch.setTeamB(visitorTeam.get(i).toString());

                    volleyballMatchService.createVolleyballMatch(volleyballMatch);
                }
        }catch (Exception e){
            throw e;
        }
    }
}
