package com.global.book.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditAware")
public class WebConfig implements WebMvcConfigurer {

	
	@Bean
	  public AuditorAware<String>  auditAware(){
		  return new AuditorAwareImpl();
	  }

	
	
	@Bean
	public MessageSource messageSource() {
	    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	    messageSource.setBasenames("bundle/messages");  // Specify the base name without locale suffix
	    messageSource.setDefaultEncoding("UTF-8");  // Ensure UTF-8 encoding is used
	    return messageSource;
	}
	
	
	@Override
	@Bean
	public Validator getValidator() {
		LocalValidatorFactoryBean bean =new LocalValidatorFactoryBean();
		bean.setValidationMessageSource( messageSource());
		return bean;
	}

	

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/cdn/**")
                .addResourceLocations("file:///D:/global/book/");
    }
}