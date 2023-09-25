package com.example.controller;

import com.example.service.JsonFileReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AppController {

    @Autowired
    private JsonFileReaderService jsonFileReaderService;

    @GetMapping("/files")
    Object getFiles(@RequestParam(value = "q", required = false) String prefix) {
        return this.jsonFileReaderService.getTreeData(prefix);
    }
}
