package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileStorageService {
    private FileMapper fileMapper;

    //Constructor
    public FileStorageService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    //Insert uploaded file into the database
    public int insertFile(File file){
        System.out.println("In message insertFile, in FileStorage Service");

        int insertResult;
        insertResult = fileMapper.insert(file);

        System.out.println("fileMapper insert method has returned: " + insertResult);
        return insertResult;
    }

    //Retrieve all uploaded files for a user
    public List<File> getFilesByUser(Integer userid){
        return fileMapper.getFilesByUser(userid);
    }

    //Retrieve file by file id
    public File getFileByID(Integer fileId){
        return fileMapper.getFileByID(fileId);
    }

    //Delete file by file id
    public Integer deleteFileByID(Integer fileId){
        return fileMapper.deleteFileByID(fileId);
    }

    public Boolean isDuplicateFile(File file){
        String duplicateFlag = fileMapper.checkDuplicateFile(file);
        System.out.println("In fileMapper function isDuplicate File, mapper has returned: "+duplicateFlag);
        return (duplicateFlag != null);
    }


}
