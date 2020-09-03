package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public int insertNote(Note note){
        System.out.println("In message insertNote, in Note Service");
        return noteMapper.insertNote(note);
    }

    public List<Note> getNotesByUser(Integer userid){
        return noteMapper.getNotesByUser(userid);
    }

    public Integer deleteNoteByID(Integer noteId){
        return noteMapper.deleteNoteByID(noteId);
    }

    public Integer updateNote(Note note){return noteMapper.updateNote(note);}
}
