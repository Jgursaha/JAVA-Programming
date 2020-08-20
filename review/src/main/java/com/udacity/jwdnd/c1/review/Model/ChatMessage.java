package com.udacity.jwdnd.c1.review.Model;

public class ChatMessage {

    private String chatMessage;
    private String userName;

    public void setChatMessage(String chatMessage) {
        this.chatMessage = chatMessage;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getChatMessage() {
        return chatMessage;
    }

    public String getUserName() {
        return userName;
    }
}
