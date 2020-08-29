package com.udacity.jwdnd.c1.review.Service;

import com.udacity.jwdnd.c1.review.Model.ChatForm;
import com.udacity.jwdnd.c1.review.Model.ChatMessage;
import com.udacity.jwdnd.c1.review.Mapper.MessageMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    //private List<ChatMessage> messageList;
    private MessageMapper messageMapper;


    public MessageService(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }

    @PostConstruct
    public void postConstruct(){
        System.out.println("After MessageService constructor");
        //this.messageList = new ArrayList<>();
    }

    public void addChatMessage(ChatForm chatFormMessage){
        ChatMessage newMessage = new ChatMessage();
        newMessage.setUserName(chatFormMessage.getUserName());
        System.out.println("In MessageService.addChatMessage");

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
        System.out.println("After Switch the message text to be inserted" + newMessage.getChatMessage());

        messageMapper.addMessage(newMessage);
        System.out.println("In addChatMessage - This is what has been inserted so far:");

    }

    public List<ChatMessage> getMessageList() {
        System.out.println("IN getMessageList, length of message list" + messageMapper.getMessages().size());
        if(messageMapper.getMessages().size() > 0){
            System.out.println("IN getMessageList, text of first message" + messageMapper.getMessages().get(0).getChatMessage());
            System.out.println("IN getMessageList, username of first message" + messageMapper.getMessages().get(0).getUserName());
            System.out.println("IN getMessageList, messageID of first message" + messageMapper.getMessages().get(0).getMessageId());
        }

        return messageMapper.getMessages();
    }
}
