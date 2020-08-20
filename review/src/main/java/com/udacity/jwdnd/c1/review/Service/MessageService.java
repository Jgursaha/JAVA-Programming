package com.udacity.jwdnd.c1.review.Service;

import com.udacity.jwdnd.c1.review.Model.ChatForm;
import com.udacity.jwdnd.c1.review.Model.ChatMessage;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    private List<ChatMessage> messageList;


    @PostConstruct
    public void postConstruct(){
        System.out.println("In MessageService constructor");
        this.messageList = new ArrayList<>();
    }

    public void addChatMessage(ChatForm chatFormMessage){
        ChatMessage newMessage = new ChatMessage();
        newMessage.setUserName(chatFormMessage.getUserName());

        switch(chatFormMessage.getMessageType()){
            case "Say":
                newMessage.setChatMessage(chatFormMessage.getMessageText());
                break;
            case "Shout":
                newMessage.setChatMessage(chatFormMessage.getMessageText().toUpperCase());
                break;
            case "Whisper":
                newMessage.setChatMessage(chatFormMessage.getMessageText().toLowerCase());
                break;
        }

        this.messageList.add(newMessage);
    }

    public List<ChatMessage> getMessageList() {
        return messageList;
    }
}
