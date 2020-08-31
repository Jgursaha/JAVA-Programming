package com.udacity.jwdnd.course1.cloudstorage.model;

import java.sql.Blob;

public class File {

    private Integer fileId;
    private String filename;
    private String contentType;
    private Long filesize;
    private Integer userId;
    private byte[] filedata;
    private Integer key;

    public Integer getFileId() {
        return fileId;
    }

    public String getFilename() {
        return filename;
    }

    public String getContentType() {
        return contentType;
    }

    public Long getFilesize() {
        return filesize;
    }

    public Integer getUserId() {
        return userId;
    }

    public byte[] getFiledata() {
        return filedata;
    }

    public Integer getKey() {
        return key;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setFilesize(Long filesize) {
        this.filesize = filesize;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setFiledata(byte[] filedata) {
        this.filedata = filedata;
    }

    public void setKey(Integer key) {
        this.key = key;
    }
}
