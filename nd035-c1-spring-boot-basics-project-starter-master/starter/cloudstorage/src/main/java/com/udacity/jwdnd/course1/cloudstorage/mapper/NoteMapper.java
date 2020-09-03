package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES(#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int insertNote(Note note);

    @Select("SELECT * FROM NOTES WHERE USERID = #{userid}")
    List<Note> getNotesByUser(Integer userid);

    @Delete("Delete FROM NOTES WHERE NOTEID = #{noteId}")
    int deleteNoteByID(Integer noteId);

    @Update("UPDATE NOTES SET noteTitle = #{noteTitle}, noteDescription = #{noteDescription} WHERE NOTEID = #{noteId}")
    int updateNote(Note note);
}


