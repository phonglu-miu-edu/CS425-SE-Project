package com.swe.lms.book.api.service.impl;

import com.swe.lms.book.api.dto.BookDTO;
import com.swe.lms.book.api.model.Book;
import com.swe.lms.book.api.repository.BookRepository;
import com.swe.lms.book.api.feign.IAdminFeignClient;
import com.swe.lms.book.api.service.IBookService;
import com.swe.lms.book.api.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;


@Service("BookService")
public class BookServiceImpl implements IBookService {
    @Autowired
    private IAdminFeignClient adminFeignClient;


    @Override
    public ResponseEntity<?> searchBooks(String keyword) {
        return adminFeignClient.searchBooks(keyword);
    }

    @Override
    public ResponseEntity<?> checkin(List<Integer> bookIds) {
//        List<Book> booksCheckin = new ArrayList<Book>();
//        for(Integer id : bookIds){
//            Optional<Book> optBook = adminFeignClient.(id);
//            if (optBook.isPresent()) {
//                Book b= optBook.get();
//                b.setAvailable(true);
//                booksCheckin.add(b);
//                bookRepository.save(b);
//            }
//        }
        return ResponseUtil.createOK( "Checkin successfully !");
    }

    @Override
    public ResponseEntity<?> checkout(List<Integer> bookId) {
//        List<Book> booksCheckout = new ArrayList<Book>();
//        for (Integer id : bookId) {
//            Optional<Book> optBook = bookRepository.findById(id);
//            if (optBook.isPresent() && optBook.get().isAvailable()) {
//                Book b = optBook.get();
//                b.setAvailable(false);
//                bookRepository.save(b);
//                booksCheckout.add(optBook.get());
//            }
//        }
//
//        if (!booksCheckout.isEmpty()) {
//            return ResponseUtil.createOK(booksCheckout, String.format("Books are updated"));
//        } else
            return ResponseUtil.createNotFound(String.format("Books not found or not available to checkout."));
    }

    @Override
    public ResponseEntity<?> getCheckoutRecords(Integer userId) {
        return null;
    }
}
