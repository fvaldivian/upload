package com.fvaldivia.upload.rest;

import com.fvaldivia.upload.storages.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class RestUpload {

    @Autowired
    Storage storage;


    @RequestMapping(value="/api/file/upload", method= RequestMethod.POST)
    public boolean UploadFiles(@RequestParam("file") MultipartFile file){
      if(!file.isEmpty()){
        return storage.Upload(file);}
        else{
          return false;
      }

    }

}
