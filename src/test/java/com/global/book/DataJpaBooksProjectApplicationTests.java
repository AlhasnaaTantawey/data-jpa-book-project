package com.global.book;

import com.global.book.entity.Author;
import com.global.book.service.AuthorService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class DataJpaBooksProjectApplicationTests {

	@Autowired
	AuthorService authorService;



//	@Test
//	@Disabled("Temporarily disabled until bug XYZ is fixed")
//	@RepeatedTest(2)
//	@Sql(scripts = { "/init-database.sql", "/populate-data.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//	public void testWithSqlScripts() {
//		// Sql scripts get executed before the execution of this block
//	}

//	@Test
//	@Sql(scripts = "/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
//	public void testWithCleanupSql() {
//		// Sql scripts get executed after the execution of this block
//	}
}
