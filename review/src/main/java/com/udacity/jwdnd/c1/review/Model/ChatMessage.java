package com.udacity.jwdnd.c1.review.Model;



public class ChatMessage {

    private Integer messageId;
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

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }
}
