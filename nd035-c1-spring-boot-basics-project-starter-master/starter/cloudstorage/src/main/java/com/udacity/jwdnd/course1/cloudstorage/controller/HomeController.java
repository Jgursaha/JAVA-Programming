package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileStorageService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
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

    public HomeController(FileStorageService fileStorageService, UserService userService) {
        this.fileStorageService = fileStorageService;
        this.userService = userService;
    }


    @RequestMapping("/home")
    public String getHomePage(Authentication authentication, Model model){
        String userName = authentication.getName();
        User user = userService.getUser(userName);
        Integer userid = user.getUserId();
        List<File> fileList = fileStorageService.getFilesByUser(userid);
        model.addAttribute("fileList", fileList);
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
    public String addNote(@ModelAttribute("currentNote") Note currentNote, Authentication auth){
        System.out.println("In add Note function, user wants to add note");
        return "home";
    }
}
