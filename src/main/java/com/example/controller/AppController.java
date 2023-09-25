package com.example.controller;

import com.example.model.FileResponse;
import com.example.service.JsonFileReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class AppController {

    @Autowired
    private JsonFileReaderService jsonFileReaderService;

    @GetMapping(path="/files", produces = "application/json")
    List<FileResponse> getFiles(@RequestParam(value = "q", required = false) String prefix) {
        return this.jsonFileReaderService.getTreeData(prefix);
    }

    @GetMapping(path="/hello", produces = "application/json")
    public String sayHello() {
        return "Hello, World!";
    }
}
