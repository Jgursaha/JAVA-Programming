package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {

    private CredentialMapper credentialMapper;
    private EncryptionService encryptionService;


    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    public int insertCredential(Credential credential){
        System.out.println("In message insertCredential, in Credential Service");
        SecureRandom random = new SecureRandom();
        String password = credential.getPassword();
        byte[] key = new byte[16];

        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(password, encodedKey);

        credential.setPassword(encryptedPassword);
        credential.setKey(encodedKey);

        return credentialMapper.insertCredential(credential);
    }

    public List<Credential> getCredentialsByUser(Integer userid){
        return credentialMapper.getCredentialsByUser(userid);
    }

    public Integer deleteCredentialByID(Integer credentialId){
        return credentialMapper.deleteCredentialByID(credentialId);
    }

    public Integer updateCredential(Credential credential){
        System.out.println("In function updateCredential, in Credential Service");
        SecureRandom random = new SecureRandom();
        String password = credential.getPassword();
        byte[] key = new byte[16];

        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(password, encodedKey);

        credential.setPassword(encryptedPassword);
        credential.setKey(encodedKey);

        return credentialMapper.updateCredential(credential);}

}

