package com.global.book.repository;

import com.global.book.entity.Author;
import com.global.book.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

//@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AuthorRepoTest {

    @Autowired
    private AuthorRepo authorRepo;


    @Test
    void findByEmailNotFoundTest() {
        Optional<Author> author= authorRepo.findByEmail("omima@gmail.com");
        assertNotNull(author);

//		"this email not found in database",false,author.isPresent()
        assertEquals(false,author.isPresent());
    }

    @Test
    void findByEmailFoundTest() {
        Optional<Author> author= authorRepo.findByEmail("ali@gmail.com");
//		"this email not found in database",false,author.isPresent()
        assertEquals(true,author.isPresent());
        assertEquals("ali@gmail.com",author.get().getEmail(),"this email is already in database");
    }



}
