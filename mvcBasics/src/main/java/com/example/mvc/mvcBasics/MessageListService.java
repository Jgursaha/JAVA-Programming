package com.example.mvc.mvcBasics;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageListService {

    private List<String> messages;

    //Constructor
    @PostConstruct
    public void postConstruct(){
        this.messages = new ArrayList<String>();
    }

    //addMessage
    public void addMessage(String message){
        messages.add(message);
    }

    //getMessages
    public List<String> getMessages(){
        return new ArrayList<>(this.messages);
    }
}
