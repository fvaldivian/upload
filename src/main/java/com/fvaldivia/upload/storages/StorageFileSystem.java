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

    String path;

    public StorageFileSystem(Map<String,String> properties){
        this.path=properties.get("path");
    }

    @Override
    public boolean Upload(MultipartFile file) {

            try{
            byte[] bytes = file.getBytes();
            Path path = Paths.get(getPath()+ file.getOriginalFilename());
            Files.write(path, bytes);

            return true;

            }
            catch (IOException e) {
                e.printStackTrace();
                return false;
            }

    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }





}
