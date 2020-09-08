package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface CredentialMapper {

    @Insert("INSERT INTO CREDENTIALS (url, username, password, key, userid) VALUES(#{url}, #{username}, #{password}, #{key}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int insertCredential(Credential credential);

    @Select("SELECT * FROM CREDENTIALS WHERE USERID = #{userid}")
    List<Credential> getCredentialsByUser(Integer userid);

    @Delete("Delete FROM CREDENTIALS WHERE credentialId = #{credentialId}")
    int deleteCredentialByID(Integer credentialId);

    @Update("UPDATE CREDENTIALS SET URL = #{url}, username = #{username}, password=#{password}, key=#{key} WHERE credentialId = #{credentialId}")
    int updateCredential(Credential credential);
}