package com.fvaldivia.upload.storages;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class StorageFileSystem extends Storage {
    Map<String,String> properties;

    public StorageFileSystem(Map<String,String> properties){
        this.properties=properties;
    }

    @Override
    public boolean Upload(MultipartFile file) {
        if(!file.isEmpty()){
            try{
            byte[] bytes = file.getBytes();
            Path path = Paths.get(properties.get("path")+ file.getOriginalFilename());
            Files.write(path, bytes);

            return true;

            }
            catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        else
            return false;
    }




}
