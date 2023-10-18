package com.swe.lms.auth.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.swe.lms.auth.api.controller.AuthController;
import com.swe.lms.auth.api.dto.UserDTO;
import com.swe.lms.auth.api.service.IAuthService;
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
public class AuthUnitTest {
    private MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();
    UserDTO user = UserDTO.builder().userName("bao").password("123").roleCd("Admin").build();

    @Mock
    private IAuthService authService;
    @InjectMocks
    private AuthController authController;
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }
    @Test
    public void getAllRecords_NoSuccess6() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/lms/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(user))).andExpect(status().isOk());
    }
    @Test
    public void getAllRecords_NoSuccess7() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/lms/auth/logout")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(user))).andExpect(status().isOk());
    }
    @Test
    public void getAllRecords_NoSuccess8() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/lms/auth/verify")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(user))).andExpect(status().isOk());
    }

}
