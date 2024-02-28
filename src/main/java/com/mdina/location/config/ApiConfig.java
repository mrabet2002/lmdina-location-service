package com.mdina.location.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/***
 * Cette classe represente les valeurs definie dans les fichiers "api.config.properties" et "urls.properties"
 * C'est utiliser principalement dans SpringConfig dans requestMatchers()
 *
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "api")
public class ApiConfig {
    private String version;
}
