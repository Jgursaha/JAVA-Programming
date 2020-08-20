package com.udacity.jwdnd.c1.review.Model;

public class ChatForm {

    private String userName;
    private String messageText;
    private String messageType;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getUserName() {
        return userName;
    }

    public String getMessageText() {
        return messageText;
    }

    public String getMessageType() {
        return messageType;
    }
}
