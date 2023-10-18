package com.swe.lms.admin.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.swe.lms.admin.api.constant.UserStatus;
import com.swe.lms.admin.api.controller.AdminController;
import com.swe.lms.admin.api.model.User;
import com.swe.lms.admin.api.service.IUserService;
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
public class UserUnitTest {
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private IUserService userService;
    @InjectMocks
    private AdminController adminController;

    User user = User.builder().userName("bao").firstName("Bao Quoc").lastName("Nguyen").roleCd("Admin")
            .password("123").email("baonguyen@miu.edu").phoneNumber("6418191119")
            .address("123 Chellen Dr, Dallas, TX 73456, US")
            .status(UserStatus.ACTIVE.getValue()).numOfOverdues(0).build();

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
    }

    @Test
    public void getAllRecords_NoSuccess5() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/lms/admin/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(user))).andExpect(status().isOk());
    }
    @Test
    public void getAllRecords_NoSuccess6() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .put("/lms/admin/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(user))).andExpect(status().isOk());
    }
    @Test
    public void getAllRecords_NoSuccess7() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/lms/admin/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(user))).andExpect(status().isOk());
    }
    @Test
    public void getAllRecords_NoSuccess8() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/lms/admin/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(user))).andExpect(status().isOk());
    }
    @Test
    public void getAllRecords_NoSuccess9() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/lms/admin/users/info")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(user))).andExpect(status().isOk());
    }
    @Test
    public void getAllRecords_NoSuccess10() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/lms/admin/users/search?q=bao")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(user))).andExpect(status().isOk());
    }
}
