package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) VALUES(#{filename}, #{contentType}, #{filesize}, #{userId}, #{filedata})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insert(File file);

    @Select("SELECT * FROM FILES WHERE USERID = #{userid}")
    List<File> getFilesByUser(Integer userid);

    @Select("SELECT * FROM FILES WHERE FILEID = #{fileId}")
    File getFileByID(Integer fileId);

    @Delete("Delete FROM FILES WHERE FILEID = #{fileId}")
    int deleteFileByID(Integer fileId);

    @Select("SELECT * FROM FILES WHERE USERID = #{userId} AND FILENAME = #{filename} ")
    String checkDuplicateFile(File file);
}
