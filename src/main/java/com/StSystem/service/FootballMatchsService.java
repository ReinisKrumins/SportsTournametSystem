package com.StSystem.service;

import com.StSystem.entity.FootballMatch;
import com.StSystem.repository.FootballRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FootballMatchsService {

    @Autowired
    private FootballRepository footballRepository;
    @Transactional
    public String createFootballMatch(FootballMatch footballMatch){
        try {
            if (footballRepository.existsByTeamA(footballMatch.getTeamA()) && footballRepository.existsByTeamB(footballMatch.getTeamB())
                    && footballRepository.existsByDate(footballMatch.getDate()) && footballRepository.existsByTime(footballMatch.getTime())){
                return "Match already exists in the database.";
            }else {
                footballMatch.setId(null == footballRepository.findMaxId()? 0 : footballRepository.findMaxId() + 1);
                footballRepository.save(footballMatch);
                return "Match record created successfully.";
            }
        }catch (Exception e){
            throw e;
        }
    }

    public List<FootballMatch> readMatchs(){
        return footballRepository.findAll();
    }
    public List<FootballMatch> readtopMatches(){

        List<FootballMatch> matches = new ArrayList<>();
        for(FootballMatch match: footballRepository.findAll()){
            matches.add(match);
        }
        return matches.subList(0,10);
    }
    @Transactional
    public String updateFootballMatch(FootballMatch footballMatch){
        if (footballRepository.existsByDate(footballMatch.getDate())){
            try {
                List<FootballMatch> footballMatches = footballRepository.findByDate(footballMatch.getDate());
                footballMatches.stream().forEach(s -> {
                    FootballMatch footballMatchToBeUpdate = footballRepository.findById(s.getId()).get();
                    footballMatchToBeUpdate.setTeamA(footballMatch.getTeamA());
                    footballMatchToBeUpdate.setTeamB(footballMatch.getTeamB());
                    footballMatchToBeUpdate.setDate(footballMatch.getDate());
                    footballMatchToBeUpdate.setTime(footballMatch.getTime());
                    footballRepository.save(footballMatchToBeUpdate);
                });
                return "Match record updated.";
            }catch (Exception e){
                throw e;
            }
        }else {
            return "Match does not exists in the database.";
        }
    }

    @Transactional
    public String deleteFootballMatch(int id){
        try{
            footballRepository.deleteById(id);
            return "deleted";
        }catch(Exception e){
            throw e;
        }
    }

    @Transactional
    public List<FootballMatch> readUpcomming(){

        List<FootballMatch> upcommingMatchs = new ArrayList<>();
        for(FootballMatch match: footballRepository.findAll())
        {
            upcommingMatchs.add(match);
        }
        upcommingMatchs.remove(0);
        return upcommingMatchs;
    }

    @Transactional
    public void fetctMatchs(){
        final String url =
                "https://www.mykhel.com/football/fixtures/";

        try {
            final Document document = Jsoup.connect(url).get();
            String html = document.outerHtml();
            Document parse = Jsoup.parse(html);
            int count = 1;
            Elements timeAndDate =parse.select("div[class=os-football-time]");
            Elements  teamA=parse.select("div[class=os-football-team team1]");
            Elements teamB = parse.select("div[class=os-football-team team2]");
            Elements league = parse.select("div[class=os-football-league]");
            for(int i = 0; i < teamA.size(); i++){
                String[] data = timeAndDate.get(i).text().split(",");

                this.createFootballMatch(new FootballMatch(teamA.get(i).text(),teamB.get(i).text(), data[1], data[2], data[3]+", "+data[4], league.get(i).text() ));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}