package com.global.book.service;

import com.global.book.entity.Author;
import com.global.book.repository.AuthorRepo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class AuthorServiceTest {

//    @Autowired
    @InjectMocks
    private AuthorService authorService;

//    @MockBean
    @Mock
    private AuthorRepo authorRepo;

    @Test
    void findByEmailFound(){
    Optional<Author> param=  Optional.of(new Author("aysin","aysin@gmail.com","192.168.1.1",null,null));
        Mockito.when(authorRepo.findByEmail(Mockito.anyString())).thenReturn(param);
         Optional<Author> author= authorService.findByEmail("ali@gmail.com");
        assertEquals(true,author.isPresent());
      //  assertEquals("emaila@gmail.com",author.get().getEmail());
    }

    @Test
    void findByEmailFoundIntegrrationTest(){
        Optional<Author> author= authorService.findByEmail("ali@gmail.com");
        assertEquals(false,author.isPresent());
        //  assertEquals("emaila@gmail.com",author.get().getEmail());
    }

    @Test
    void findByEmailNotFound() {

        Mockito.when(authorRepo.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());
        Optional<Author> author = authorService.findByEmail("ali@gmail.com");

        assertEquals(false, author.isPresent());
        // Alternatively, use assertTrue or assertFalse for better readability
        assertFalse(author.isPresent(), "Expected author to be absent");
//        assertTrue(author.isPresent(), "Expected author to be present");

        // Optionally, verify interaction with the mock
        Mockito.verify(authorRepo).findByEmail("ali@gmail.com");
    }
}
