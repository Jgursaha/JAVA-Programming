package com.udacity.jwdnd.c1.review.Mapper;

import com.udacity.jwdnd.c1.review.Model.ChatMessage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MessageMapper {
    @Select("SELECT * FROM MESSAGES")
    List<ChatMessage> getMessages();

    //@Insert("INSERT INTO MESSAGES (username, messagetext) VALUES (#{userName}, #{chatMessage})")
    //@Options(useGeneratedKeys = true, keyProperty = "messageId")
    //int insert(ChatMessage chatMessage);

    @Insert("INSERT INTO MESSAGES (username, messagetext) VALUES (#{userName}, #{chatMessage})")
    @Options(useGeneratedKeys = true, keyProperty = "messageId")
    int insert(ChatMessage chatMessage);
}
