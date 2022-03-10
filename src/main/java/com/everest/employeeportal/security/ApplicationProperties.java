package com.everest.employeeportal.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "app")
@Data
public class ApplicationProperties {
    private String jwtSecret;
}
