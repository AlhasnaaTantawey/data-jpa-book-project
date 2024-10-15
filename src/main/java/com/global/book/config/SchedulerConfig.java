package com.global.book.config;

import java.util.concurrent.Executor;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;

@Configuration
@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor = "10m")
@ConditionalOnProperty(name = "scheduler.enable" , matchIfMissing = true)
@EnableAsync
public class SchedulerConfig implements AsyncConfigurer {

	 @Bean
	  public LockProvider lockProvider(DataSource dataSource) {
	    return new JdbcTemplateLockProvider(
	        JdbcTemplateLockProvider.Configuration.builder()
	        .withJdbcTemplate(new JdbcTemplate(dataSource))
	        .usingDbTime() // Works on Postgres, MySQL, MariaDb, MS SQL, Oracle, DB2, HSQL and H2
	        .build()
	    );
	  }
	
	// handle async at method level 
	 
	 @Bean(name = "taskExecutorDefault")
	 public ThreadPoolTaskExecutor taskExecutor() {
	  ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	  executor.setCorePoolSize(2);
	  executor.setMaxPoolSize(2);
	  executor.setQueueCapacity(500);
	  executor.setThreadNamePrefix("MyAsyncThread-");
	  executor.setRejectedExecutionHandler((r, executor1) -> LoggerFactory.getLogger(SchedulerConfig.class).warn("Task rejected, thread pool is full and queue is also full"));
	  executor.initialize();
	  return executor;
	 }
	 
	 // handle async at application level 
	 @Override
	public Executor getAsyncExecutor() {
		  ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		  executor.setCorePoolSize(2);
		  executor.setMaxPoolSize(2);
		  executor.setQueueCapacity(500);
		  executor.setThreadNamePrefix("MyAsyncThread-");
		  executor.setRejectedExecutionHandler((r, executor1) -> LoggerFactory.getLogger(SchedulerConfig.class).warn("Task rejected, thread pool is full and queue is also full"));
		  executor.initialize();
		  return executor;
	}
	
}
