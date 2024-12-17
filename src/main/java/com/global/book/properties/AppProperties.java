package com.global.book.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "app")
@Getter
@Setter
@Component
public class AppProperties {

    private String appName;

    private String appVersion;

}
