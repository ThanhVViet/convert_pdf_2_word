package com.example.ltm.Model.Bean;

public class File {
    private int fileId;
    private int userId;
    private String fileName;
    private int fileStatus;

    public File() {
    }

    public File(int fileId, int userId, String fileName, int fileStatus) {
        this.fileId = fileId;
        this.userId = userId;
        this.fileName = fileName;
        this.fileStatus = fileStatus;
    }

    public File(int fileId, String fileName, int fileStatus) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.fileStatus = fileStatus;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getFileStatus() {
        return fileStatus;
    }

    public void setFileStatus(int fileStatus) {
        this.fileStatus = fileStatus;
    }
}
