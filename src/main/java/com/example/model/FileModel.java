package com.example.model;

import java.util.List;

public class FileModel {
    private String name;
    private List<String> files;
    private List<List<FileModel>> directories;

    public FileModel() {

    }

    public FileModel(FileModel fileModel) {
        this.name = fileModel.name;
        this.files = fileModel.files;
        this.directories = fileModel.directories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }

    public List<List<FileModel>> getDirectories() {
        return directories;
    }

    public void setDirectories(List<List<FileModel>> directories) {
        this.directories = directories;
    }
}
