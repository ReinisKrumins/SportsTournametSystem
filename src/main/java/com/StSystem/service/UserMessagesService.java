package com.StSystem.service;

import com.StSystem.entity.FootballMatch;
import com.StSystem.entity.UserMessages;
import com.StSystem.repository.FootballRepository;
import com.StSystem.repository.UserMessagesRepository;
import org.apache.catalina.User;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserMessagesService {

    @Autowired
    private UserMessagesRepository userMessagesRepository;
    @Transactional
    public String saveMessage(UserMessages userMessage){
        try {
            if (userMessagesRepository.existsByName(userMessage.getName()) && userMessagesRepository.existsByEmail(userMessage.getEmail())
                    && userMessagesRepository.existsBySubject(userMessage.getSubject())&& userMessagesRepository.existsByMessage(userMessage.getMessage())){
                return "Match already exists in the database.";
            }else {
                userMessage.setId(null == userMessagesRepository.findMaxId()? 0 : userMessagesRepository.findMaxId() + 1);
                userMessagesRepository.save(userMessage);
                return "Match record created successfully.";
            }
        }catch (Exception e){
            throw e;
        }
    }

    public List<UserMessages> readMatchs(){
        return userMessagesRepository.findAll();
    }
}