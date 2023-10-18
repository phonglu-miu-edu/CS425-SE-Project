package com.swe.lms.admin.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.swe.lms.admin.api.controller.AdminController;
import com.swe.lms.admin.api.dto.BookCategoryDTO;
import com.swe.lms.admin.api.dto.ConfigDTO;
import com.swe.lms.admin.api.service.IBookCategoryService;
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
public class BookCategoryUnitTest {
    private MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private IBookCategoryService bookCategoryService;

    @InjectMocks
    private AdminController adminController;

    BookCategoryDTO categoryDTO = new BookCategoryDTO(1, "Java","Java technology related books");

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
    }
    @Test
    public void getAllRecords_NoSuccess12() throws Exception {
        ConfigDTO configDTO = new ConfigDTO(10, "Number of days is free to borrow","30");

        mockMvc.perform(MockMvcRequestBuilders
                .post("/lms/admin/book_categories/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(configDTO))).andExpect(status().isOk());
    }
    @Test
    public void getAllRecords_NoSuccess13() throws Exception {
        ConfigDTO configDTO = new ConfigDTO(10, "Number of days is free to borrow","30");

        mockMvc.perform(MockMvcRequestBuilders
                .post("/lms/admin/book_categories/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(configDTO))).andExpect(status().isOk());
    }
    @Test
    public void getAllRecords_NoSuccess10() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/lms/admin/book_categories/search?q=Java")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void getAllRecords_NoSuccess8() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/lms/admin/book_categories/2")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void getAllRecords_NoSuccess9() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/lms/admin/book_categories/7")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void getAllRecords_NoSuccess5() throws Exception {


        mockMvc.perform(MockMvcRequestBuilders
                .post("/lms/admin/book_categories/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(categoryDTO))).andExpect(status().isOk());
    }
}
