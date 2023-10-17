package com.swe.lms.admin.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.swe.lms.admin.api.controller.AdminController;
import com.swe.lms.admin.api.model.Book;
import com.swe.lms.admin.api.service.IBookCopyService;
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
public class BookCopyUnitTest {
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();
    @Mock
    private IBookCopyService bookCopyService;
    @InjectMocks
    private AdminController adminController;

    Book bookDTO1 = new Book(1, "title","isbn","authors",1,1,true);
    Book bookDTO2 = new Book(1, "title2","isbn","authors",1,1,true);
    Book bookDTO3 = new Book(1, "title3","isbn","authors",1,1,true);

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
    }
    @Test
    public void TestUnauthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/lms/admin/book_copies")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void TestBookCopiesPostWithBody() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                .post("/lms/admin/book_copies/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(bookDTO1))).andExpect(status().isOk());
    }
    @Test
    public void TestBookCopiesPutWithBody() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                .put("/lms/admin/book_copies/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(bookDTO1))).andExpect(status().isOk());
    }
    @Test
    public void TestBookCopiesGet() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                .get("/lms/admin/book_copies/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(bookDTO1))).andExpect(status().isOk());
    }
}
