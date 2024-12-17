package com.global.book.AuthorController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.global.book.entity.Author;
import com.global.book.service.AuthorService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Optional;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Log4j2
public class AuthorControllerTest {

//    @Autowired
//    TestRestTemplate testRestTemplate;
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    AuthorService authorService;

        @Test
    void findByEmailFound() throws Exception {
        Optional<Author> param=  Optional.of(new Author("aysin","ali@gmail.com","192.168.1.1",null,null));
        Mockito.when(authorService.findByEmail(Mockito.anyString())).thenReturn(param);
            String email="mmmm@gmail.com";
            mockMvc.perform(MockMvcRequestBuilders.get("/author/email/{email}",email)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
    }

    @Test
    void insertTest() throws Exception {
       Author param=  new Author("aysin","ali@gmail.com","192.168.1.1",null,null);
        Mockito.when(authorService.insert(Mockito.any(Author.class))).thenReturn(param);
        mockMvc.perform(MockMvcRequestBuilders.post("/author")
                        .contentType(MediaType.APPLICATION_JSON).
                        content(objectMapper.writeValueAsString( new Author("aysin","ali@gmail.com","192.168.1.1",null,null))))
                .andExpect(status().isOk());
    }

    @BeforeAll
    static void initmethod(){
            log.info("========================= before all");
    }

    @AfterAll
    static void destroy(){
        log.info("========================= in destroy method");
    }

//    @Test
//    void findByEmailFound(){
//        Optional<Author> param=  Optional.of(new Author("aysin","ali@gmail.com","192.168.1.1",null,null));
//        Mockito.when(authorService.findByEmail(Mockito.anyString())).thenReturn(param);
//        String email="mmmm@gmail.com";
//        ResponseEntity<AuthorDto> response= testRestTemplate.getForEntity("/author/email/"+email, AuthorDto.class);
//        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        org.junit.jupiter.api.Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
//        Assertions.assertThat(response.getBody().getEmail()).isEqualTo("ali@gmail.com");
//    }

//    @Test
//    void findByEmailNotFound(){
//        Optional<Author> param=  Optional.of(new Author("aysin","aysin@gmail.com","192.168.1.1",null,null));
//        Mockito.when(authorService.findByEmail(Mockito.anyString())).thenReturn(param);
//        String email="aysin@gmail.com";
//        ResponseEntity<AuthorDto> response= testRestTemplate.getForEntity("/author/email/"+email, AuthorDto.class);
//        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.EXPECTATION_FAILED);
//        org.junit.jupiter.api.Assertions.assertEquals(HttpStatus.EXPECTATION_FAILED,response.getStatusCode());
//    }
}
