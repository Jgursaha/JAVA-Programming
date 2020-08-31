package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileStorageService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;

@Controller()

public class HomeController {

    private FileStorageService fileStorageService;
    private UserService userService;

    public HomeController(FileStorageService fileStorageService, UserService userService) {
        this.fileStorageService = fileStorageService;
        this.userService = userService;
    }

    @RequestMapping("/home")
    public String getHomePage(){
        return "home";
    }

    @RequestMapping("/uploadFile")
    public String handleFileUpload(Authentication authentication, @RequestParam("fileUpload") MultipartFile multipartFile, Model model){
        System.out.println("in handleFileUpload");

        String userName = authentication.getName();
        File file = new File();
        User user =  userService.getUser(userName);

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
        file.setUserId(user.getUserId());

        //call file storage service to insert file in database
        fileStorageService.insertFile(file);

        return "redirect:/result?file";
    }
}
