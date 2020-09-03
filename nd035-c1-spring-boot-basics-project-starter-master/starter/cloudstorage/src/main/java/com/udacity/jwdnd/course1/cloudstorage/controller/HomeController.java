package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.List;

@Controller()
public class HomeController {

    private FileStorageService fileStorageService;
    private UserService userService;
    private NoteService noteService;
    private CredentialService credentialService;
    private EncryptionService encryptionService;

    public HomeController(FileStorageService fileStorageService, UserService userService, NoteService noteService, CredentialService credentialService, EncryptionService encryptionService) {
        this.fileStorageService = fileStorageService;
        this.userService = userService;
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
    }

    @RequestMapping("/home")
    @GetMapping
    public String getHomePage(@ModelAttribute("currentNote") Note currentNote, @ModelAttribute("currentCredential") Credential currentCredential, Authentication authentication, Model model){
        String userName = authentication.getName();
        User user = userService.getUser(userName);
        Integer userid = user.getUserId();
        List<File> fileList = fileStorageService.getFilesByUser(userid);
        List<Note> noteList = noteService.getNotesByUser(userid);
        List<Credential> credentialList = credentialService.getCredentialsByUser(userid);

        if(credentialList.size() > 0){
            System.out.println("first credential username is: "+credentialList.get(0).getUsername());
        }
        model.addAttribute("fileList", fileList);
        model.addAttribute("noteList", noteList);
        model.addAttribute("credentialList", credentialList);
        model.addAttribute("encryptionService", encryptionService);

        return "home";
    }

    @RequestMapping("/uploadFile")
    @PostMapping
    public String handleFileUpload(Authentication authentication, @RequestParam("fileUpload") MultipartFile multipartFile, Model model){
        System.out.println("in handleFileUpload");
        Integer insertSuccess = 0;
        String userName = authentication.getName();
        File file = new File();
        Integer userid =  userService.getUser(userName).getUserId();

        try{
            file.setFiledata(multipartFile.getBytes());
        }
        catch(IOException ioException){
            System.out.println("Problem reading InputStream");
        }

        //set fields in file object
        file.setFilename(multipartFile.getOriginalFilename());
        file.setFilesize(multipartFile.getSize());
        file.setContentType(multipartFile.getContentType());
        file.setUserId(userid);

        //check for duplicate file
        if (fileStorageService.isDuplicateFile(file)){
            model.addAttribute("fileDuplicateError", true);
            return "result.html";
        }
        //call file storage service to insert file in database
        insertSuccess = fileStorageService.insertFile(file);
        if (insertSuccess ==1){
            model.addAttribute("fileUploadSuccess", true);
        }
        else{
            model.addAttribute("fileUploadError", true);
        }

        return "result.html";
    }


    //Acknowledgement: https://knowledge.udacity.com/questions/271629
    @GetMapping("/file/view/{fileId}")
     public ResponseEntity getFileFromDB(@PathVariable Integer fileId, Authentication auth){
        System.out.println("User wants to view file with id" + fileId);
        File file = fileStorageService.getFileByID(fileId);
        String fileName = file.getFilename();
        byte[] fileData = file.getFiledata();
        String contentType = file.getContentType();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType)).header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + fileName + "\"").body(fileData);

    }

    @GetMapping("/file/delete/{fileId}")
    public String deleteFile(@PathVariable Integer fileId, Authentication auth, Model model){
        System.out.println("User wants to delete file with id" + fileId);
        Integer deleteResult = fileStorageService.deleteFileByID(fileId);

        if (deleteResult ==1){
            model.addAttribute("fileDeleteSuccess", true);
        }
        else{
            model.addAttribute("fileDeleteError", false);
        }

        return "result.html";
    }

    @PostMapping("/addNote")
    public String addNote(@ModelAttribute("currentNote") Note currentNote, Authentication auth, Model model){
        if(currentNote.getNoteId() == null){
            System.out.println("In add Note function, user wants to add note");
            String userName = auth.getName();
            Integer userid =  userService.getUser(userName).getUserId();
            currentNote.setUserId(userid);
            int insertResult = noteService.insertNote(currentNote);

            if (insertResult ==1){
                model.addAttribute("noteCreationSuccess", true);
            }
            else{
                model.addAttribute("noteCreationError", true);
            }
        }
        else{
            System.out.println("In add Note function, user wants to update note");
            System.out.println("User sent the note title: " + currentNote.getNoteTitle());
            System.out.println("User sent the note description : " + currentNote.getNoteDescription());
            System.out.println("User sent the note id: " + currentNote.getNoteId());

            int updateResult = noteService.updateNote(currentNote);

            //System.out.println("Insertion of note has returned: "+insertResult);
            if (updateResult ==1){
                model.addAttribute("noteUpdateSuccess", true);
            }
            else{
                model.addAttribute("noteUpdateError", true);
            }
        }
        return "result.html";
    }

    @GetMapping("/note/delete/{noteId}")
    public String deleteNote(@PathVariable Integer noteId, Authentication auth, Model model){
        System.out.println("User wants to delete note with id" + noteId);
        Integer deleteResult = noteService.deleteNoteByID(noteId);

        if (deleteResult ==1){
            model.addAttribute("noteDeletionSuccess", true);
        }
        else{
            model.addAttribute("noteDeletionError", false);
        }

        return "result.html";
    }

    @PostMapping("/addCredential")
    public String addCredential(@ModelAttribute("currentCredential") Credential currentCredential, Authentication auth, Model model){
        if(currentCredential.getCredentialId() == null){
            System.out.println("In add Credential function, user wants to add credential");
            String userName = auth.getName();
            Integer userid =  userService.getUser(userName).getUserId();
            currentCredential.setUserId(userid);
            int insertResult = credentialService.insertCredential(currentCredential);

            if (insertResult ==1){
                model.addAttribute("credentialCreationSuccess", true);
            }
            else{
                model.addAttribute("credentialCreationError", true);
            }
        }
        else{
            System.out.println("In add Credential function, user wants to update note");
            System.out.println("User sent the credential url: " + currentCredential.getUrl());
            System.out.println("User sent the credential Username : " + currentCredential.getUsername());
            System.out.println("User sent the credential id: " + currentCredential.getCredentialId());

            int updateResult = credentialService.updateCredential(currentCredential);

            //System.out.println("Insertion of note has returned: "+insertResult);
            if (updateResult ==1){
                model.addAttribute("credentialUpdateSuccess", true);
            }
            else{
                model.addAttribute("credentialUpdateError", true);
            }
        }
        return "result.html";
    }

    @GetMapping("/credential/delete/{credentialId}")
    public String deleteCredential(@PathVariable Integer credentialId, Authentication auth, Model model){
        System.out.println("User wants to delete credential with id" + credentialId);
        Integer deleteResult = credentialService.deleteCredentialByID(credentialId);

        if (deleteResult ==1){
            model.addAttribute("credentialDeletionSuccess", true);
        }
        else{
            model.addAttribute("credentialDeletionError", false);
        }

        return "result.html";
    }
}
