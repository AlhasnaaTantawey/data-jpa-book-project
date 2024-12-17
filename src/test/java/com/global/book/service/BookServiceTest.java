package com.global.book.service;

import com.global.book.configration.TestBookServiceConfig;
import com.global.book.entity.Book;
import com.global.book.repository.BookRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.util.List;

@SpringBootTest
@Import(TestBookServiceConfig.class)
public class BookServiceTest {

    @Autowired
    private BookService bookService2;

//    @TestConfiguration
//    public static class TestBookServiceConfig{
//
//        @Bean
//        public BookService  bookService2(BookRepo repo){
//            return new BookService(repo);
//        }
//    }

    @Test
    void findAllTest(){
        List<Book> bookList  = bookService2.findAll();
        Assertions.assertEquals(15l,bookList.stream().count());
    }
}
