package com.swe.lms.admin.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.swe.lms.admin.api.controller.AdminController;
import com.swe.lms.admin.api.dto.BookCategoryDTO;
import com.swe.lms.admin.api.repository.BookCategoryRepository;
import com.swe.lms.admin.api.service.IBookCategoryService;
import com.swe.lms.admin.api.service.impl.BookServiceImpl;
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
public class BookCategoryServiceUnitTest{
    private MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private IBookCategoryService bookCategoryService;
    @Mock
    private BookCategoryRepository bookCategoryRepository;

    @Mock
    private BookServiceImpl bookServiceImpl;

    @InjectMocks
    private AdminController adminController;

    BookCategoryDTO categoryDTO = new BookCategoryDTO(1, "Java","Java technology related books");

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
    }
    @Test
    public void TestBookCategoriesMethodNotAllowed() throws Exception {
        BookCategoryDTO configDTO = new BookCategoryDTO(10, "Number of days is free to borrow","30");
        mockMvc.perform(MockMvcRequestBuilders
                .get("/lms/admin/book_categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(configDTO)))
                .andExpect(status().isMethodNotAllowed());
    }
    @Test
    public void getAllRecords_NoSuccess12() throws Exception {
        BookCategoryDTO configDTO = new BookCategoryDTO(10, "Number of days is free to borrow","30");
        String requestBody = objectMapper.writeValueAsString(configDTO);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/lms/admin/book_categories/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());
    }
}
