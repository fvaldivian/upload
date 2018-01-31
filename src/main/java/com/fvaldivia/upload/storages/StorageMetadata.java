package com.fvaldivia.upload.storages;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public class StorageMetadata extends Storage {
    Map<String,String> properties;

    public StorageMetadata(Map<String,String> properties){

        this.properties=properties;
    }

    @Override
    public boolean Upload(MultipartFile file) {

        return false;
    }



}
