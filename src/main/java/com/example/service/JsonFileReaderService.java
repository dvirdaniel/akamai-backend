package com.example.service;

import com.example.model.FileModel;
import com.example.model.FileResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JsonFileReaderService {

    private Map<String, FileModel> data = new HashMap<>();

    @PostConstruct
    public void init() {
        this.loadTreeData();
    }

    private List<FileModel> readJsonFile() {
        ObjectMapper objectMapper = new ObjectMapper();

        // Read tree.json file from resources directory and convert it to list of FileModel
        try (InputStream inputStream = this.getClass().getResourceAsStream("/tree.json")) {
            if (inputStream != null) {
                TypeReference<List<FileModel>> typeReference = new TypeReference<>() {};
                return objectMapper.readValue(inputStream, typeReference);
            } else {
                throw new IOException("Resource file not found");
            }
        } catch (IOException e) {
            System.err.println("Exception occurred when reading json file: " + e.getMessage());
            return null;
        }
    }

    private void loadTreeData() {
        try {

            // If the data is empty then fill it
            if (this.data.isEmpty()) {

                // Read the tree.json file and get a list of objects
                List<FileModel> files = this.readJsonFile();

                if (files != null) {

                    // Fill data with first file
                    this.data = new HashMap<>();
                    FileModel first = files.get(0);
                    this.data.put(first.getName(), first);

                    // Fill data with all files by traversing the first file
                    files.forEach(file -> traverseDirectory(file, this.data, file.getName()));
                }
            }
        } catch (Exception e) {
            System.err.println("Exception occurred when loading tree data: " + e.getMessage());
        }
    }

    public List<FileResponse> getTreeData(String prefix) {
        List<FileResponse> result = new ArrayList<>();
        try {

            // If there is prefix then it on the data to filter files else return all files
            if (prefix != null) {
                List<Map.Entry<String, FileModel>> entries = this.data.entrySet().stream().filter(entry -> entry.getKey().substring(entry.getKey().lastIndexOf('/') + 1).startsWith(prefix)).toList();
                entries.forEach(entry -> result.add( new FileResponse(entry.getValue(), entry.getKey())) );
            } else {
                this.data.forEach((key, value) -> result.add( new FileResponse(value, key)) );
            }
        } catch (Exception e) {
            System.out.println("Exception occurred when getting tree data: " + e.getMessage());
        }
        return result;
    }

    private void traverseDirectory(FileModel fileModel, Map<String, FileModel> fileMap, String key) {

        // Get a list of all files and directories in the current directory
       List<List<FileModel>> filesAndDirs = fileModel.getDirectories();

        if (filesAndDirs != null && !filesAndDirs.isEmpty()) {

            // Loop through each file or directory in the current directory
            for (List<FileModel> filesOrDirs : filesAndDirs) {
                for (FileModel fileOrDir : filesOrDirs) {
                    if (fileOrDir.getDirectories() != null && !fileOrDir.getDirectories().isEmpty()) {

                        // If it's a directory, recursively traverse it
                        traverseDirectory(fileOrDir, fileMap, key + '/' + fileOrDir.getName());
                    }
                    fileMap.put(key + '/' + fileOrDir.getName(), fileOrDir);
                }
            }
        }
    }
}

