package com.example;

import com.example.controller.AppController;
import com.example.model.FileResponse;
import com.example.service.JsonFileReaderService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AppController.class)
public class AppControllerTest {

    private MockMvc mockMvc;

    @Mock
    private JsonFileReaderService jsonFileReaderService;

    @InjectMocks
    private AppController appController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(appController).build();
    }

    @Test
    public void testGetFilesWithPrefix() throws Exception {
        String prefix = "filters";
        List<FileResponse> responseList = new ArrayList<>();
        when(jsonFileReaderService.getTreeData(prefix)).thenReturn(responseList);
        mockMvc.perform(get("/files")
                        .param("q", prefix)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(responseList.toString()));
    }

    @Test
    public void testGetFilesWithoutPrefix() throws Exception {
        List<FileResponse> responseList = new ArrayList<>();
        when(jsonFileReaderService.getTreeData(null)).thenReturn(responseList);
        mockMvc.perform(get("/files")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(responseList.toString()));
    }
}