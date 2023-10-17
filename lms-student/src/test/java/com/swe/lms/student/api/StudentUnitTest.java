package com.swe.lms.student.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.swe.lms.admin.api.constant.UserStatus;
import com.swe.lms.admin.api.model.User;
import com.swe.lms.student.api.controller.StudentController;
import com.swe.lms.student.api.dto.UserDTO;
import com.swe.lms.student.api.service.IStudentService;
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
public class StudentUnitTest {
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private IStudentService studentService;
    @InjectMocks
    private StudentController studentController;

    UserDTO user = UserDTO.builder().userName("bao").firstName("Bao Quoc").lastName("Nguyen").roleCd("Admin")
            .password("123").email("baonguyen@miu.edu").phoneNumber("6418191119")
            .address("123 Chellen Dr, Dallas, TX 73456, US")
            .status(UserStatus.ACTIVE.getValue()).numOfOverdues(0).build();

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
    }

    @Test
    public void getAllRecords_NoSuccess5() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/lms/student/register/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(user))).andExpect(status().isOk());
    }
    @Test
    public void getAllRecords_NoSuccess8() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/lms/student/students/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(user))).andExpect(status().isOk());
    }

    @Test
    public void getAllRecords_NoSuccess10() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/lms/student/checkout_records/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(user))).andExpect(status().isOk());
    }
}
