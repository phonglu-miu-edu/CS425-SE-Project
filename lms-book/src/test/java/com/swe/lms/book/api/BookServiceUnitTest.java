package com.swe.lms.book.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.swe.lms.book.api.controller.BookController;
import com.swe.lms.book.api.dto.CheckoutRecordDTO;
import com.swe.lms.book.api.service.IBookService;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceUnitTest {
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();
    @Mock
    private IBookService bookService;
    @InjectMocks
    private BookController bookController;

    CheckoutRecordDTO checkoutRecordDTO = new CheckoutRecordDTO(1,1,1,1,"Checkout record", LocalDate.now(),LocalDate.of(2023,12,10));
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }
    @Test
    public void TestUnauthorized() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/lms/book/search")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }
//    @Test
//    public void TestBookCopiesPostWithBody() throws Exception {
//        List<Integer> bookId = new ArrayList<Integer>(Arrays.asList(1,2,3));
//        mockMvc.perform(MockMvcRequestBuilders
//                .post("/lms/book/checkout/")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(this.objectMapper.writeValueAsString(checkoutRecordDTO))).andExpect(status().isOk());
//    }
    @Test
    public void TestBookCopiesPutWithBody() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                .get("/lms/book/checkout/records/1")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
    @Test
    public void TestBookCopiesGet() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                .get("/lms/book/search?q=Java")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
}