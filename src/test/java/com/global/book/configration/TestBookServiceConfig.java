package com.global.book.configration;

import com.global.book.repository.BookRepo;
import com.global.book.service.BookService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestBookServiceConfig {
    @Bean
    public BookService bookService2(BookRepo repo){
        return new BookService(repo);
    }
}
