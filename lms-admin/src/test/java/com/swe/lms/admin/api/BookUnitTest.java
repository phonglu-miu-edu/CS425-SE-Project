package com.swe.lms.admin.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.swe.lms.admin.api.controller.AdminController;
import com.swe.lms.admin.api.model.Book;
import com.swe.lms.admin.api.service.IBookService;
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
public class BookUnitTest {
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private IBookService bookService;

    @InjectMocks
    private AdminController adminController;

    Book bookDTO = new Book(1, "title","isbn","authors",1,1,true);
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
    }

    @Test
    public void TestUnauthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/lms/admin/books")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void TestBooksGetWithBody() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/lms/admin/books/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(bookDTO))).andExpect(status().isOk());
    }
    @Test
    public void TestBooksGet() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/lms/admin/books/7")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void TestBooksDelete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/lms/admin/books/7")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void TestBooksSearch() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/lms/admin/books/search?q=Joshua")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void TestBooksPost() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/lms/admin/books/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(bookDTO))).andExpect(status().isOk());
    }

}
