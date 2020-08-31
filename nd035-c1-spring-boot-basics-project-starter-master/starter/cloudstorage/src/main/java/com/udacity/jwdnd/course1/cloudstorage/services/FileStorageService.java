package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

@Service
public class FileStorageService {
    private FileMapper fileMapper;

    //uploadFile

    public FileStorageService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public int insertFile(File file){
        System.out.println("In message insertFile, in FileStorage Service");

        int insertResult;
        insertResult = fileMapper.insert(file);

        System.out.println("fileMapper insert method has returned: " + insertResult);
        return insertResult;
    }
}
