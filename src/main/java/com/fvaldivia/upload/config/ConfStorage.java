package com.fvaldivia.upload.config;

import com.fvaldivia.upload.storages.Storage;
import com.fvaldivia.upload.storages.StorageFileSystem;
import com.fvaldivia.upload.storages.StorageMetadata;
import com.fvaldivia.upload.storages.StorageS3;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties("storage")
public class ConfStorage {
    String type;
    Map<String,String> properties;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    @Bean
    public Storage storages(){
        if(type.equals("fileSystem")){
            return new StorageFileSystem(properties);
        }
        else if(type.equals("metadata")){
            return new StorageMetadata(properties);
        }
        return new StorageS3(properties);
    }



}
