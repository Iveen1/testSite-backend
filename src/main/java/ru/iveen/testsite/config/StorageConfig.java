package ru.iveen.testsite.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Polyakov Anton
 * @created 10.05.2022 5:31
 * @project testSite
 */

@ConfigurationProperties(prefix = "storage")
public class StorageConfig {
    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
