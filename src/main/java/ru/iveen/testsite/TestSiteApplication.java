package ru.iveen.testsite;

import ru.iveen.testsite.config.StorageConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageConfig.class)
public class TestSiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestSiteApplication.class, args);
    }

}
