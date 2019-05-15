package com.istimeless.securitydemo.controller;

import com.istimeless.securitydemo.SecurityDemoApplicationTests;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.Assert.*;

public class UserControllerTest extends SecurityDemoApplicationTests {

    @Autowired
    private WebApplicationContext wac;
    
    private MockMvc mockMvc;
    
    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    
    @Test
    public void whenQuerySuccess() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/user")
                .param("username","aaa")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void whenGetInfoSuccess() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("tom"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void whenGetInfoFail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/a")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void whenCreateSuccess() throws Exception {
        Date date = new Date();
        System.out.println(date.getTime());
        String context = "{\"username\":\"tom\",\"password\":\"123456\",\"birthday\":"+ date.getTime() +"}";
        String result = mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(context))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void whenUpdateSuccess() throws Exception {
        Date date = new Date(LocalDateTime.now().plusYears(1L).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        System.out.println(date.getTime());
        String context = "{\"username\":\"tom\",\"password\":\"123456\",\"birthday\":"+ date.getTime() +"}";
        String result = mockMvc.perform(MockMvcRequestBuilders.put("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(context))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void whenDeleteSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenUploadSuccess() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders.multipart("/user/file")
                .file(new MockMultipartFile("file", "test.txt", "multipart/form-data", "hello".getBytes(StandardCharsets.UTF_8))))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
}