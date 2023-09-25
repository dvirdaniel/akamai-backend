package com.example.model;

public class FileResponse extends FileModel {
    private String path;

    public FileResponse(FileModel fileModel, String path) {
        super(fileModel);
        this.setPath(path);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
