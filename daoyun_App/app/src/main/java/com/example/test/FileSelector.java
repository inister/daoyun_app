package com.example.test;

public class FileSelector {
    private int imageId;
    private String fileName;
    public boolean dirOrNot;

    public FileSelector(int imageId, String fileName, boolean dirOrNot){
        this.imageId = imageId;
        this.fileName = fileName;
        this.dirOrNot = dirOrNot;
    }

    public int getImageId() {
        return imageId;
    }

    public String getFileName() {
        return fileName;
    }

    public boolean getDirOrNot(){
        return dirOrNot;
    }
}
