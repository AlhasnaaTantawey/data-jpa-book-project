package com.global.book.configration;

import com.global.book.entity.Application;
import com.global.book.properties.AppProperties;
import com.global.book.service.ApplicationService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Log4j2
public class ApplicationServiceTest {

    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private AppProperties properties;
//
//    @Test
//    public void testGetDetailsByName() {
//        // Assuming there is a default application with the name 'MyApp' in your DB
//        Application app = applicationService.getDetailsByName("MyApp");
//
//        // Assert that the application is found and not null
//        assertNotNull(app);
//        assertEquals("myapp", app.getName());  // You can add more assertions here as needed
//    }


//    @RepeatedTest(2)
    @Test
        public void getDetailsByNameTest() {
            Application app = applicationService.getDetailsByName(properties.getAppName());
            assertEquals("data-jpa-books-project", app.getName());
            assertNotNull(app);
        }
    }

