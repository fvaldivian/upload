package com.fvaldivia.upload.storages;

import org.apache.commons.io.FilenameUtils;
import org.json.simple.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.FileTypeMap;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StorageMetadata extends Storage {

    String path;
    String format;

    public StorageMetadata(Map<String,String> properties){
        this.path=properties.get("path");
        this.format=properties.get("format");
    }

    @Override
    public boolean Upload(MultipartFile file) {



        try {
            FileWriter fil = new FileWriter(getPath()+file.getName()+"."+getFormat());

            if(getFormat().equals("json")) {
                JSONObject obj = new JSONObject();
                obj.put("Nombre", file.getName());
                obj.put("Tamaño", file.getSize() + "bytes");
                obj.put("Extension", FilenameUtils.getExtension(file.getOriginalFilename()));
                fil.write(obj.toJSONString());
            }
            else{
                fil.write("Nombre "+file.getName()+System.getProperty( "line.separator" ));
                fil.write("Tamaño "+file.getSize() + " bytes"+System.getProperty( "line.separator" ));
                fil.write("Extension "+FilenameUtils.getExtension(file.getOriginalFilename()));
            }


            fil.flush();
            fil.close();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }



    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }



}
