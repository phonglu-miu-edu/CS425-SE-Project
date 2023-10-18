package com.swe.lms.admin.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.swe.lms.admin.api.controller.AdminController;
import com.swe.lms.admin.api.dto.ConfigDTO;
import com.swe.lms.admin.api.service.IConfigService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class ConfigServiceUnitTest {
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    ConfigDTO configDTO = new ConfigDTO(10, "Number of days is free to borrow","30");
    @Mock
    private IConfigService configService;

    @InjectMocks
    private AdminController adminController;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
    }
    @Test
    public void TestUnauthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/lms/admin/configs")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void getAllRecords_configsGet() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/lms/admin/configs/")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
}