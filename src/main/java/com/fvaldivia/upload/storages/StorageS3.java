package com.fvaldivia.upload.storages;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public class StorageS3 extends Storage {
    Map<String,String> properties;

    public StorageS3(Map<String,String> properties){

        this.properties=properties;
    }

    @Override
    public boolean Upload(MultipartFile file) {

        try {
        File fil = convert(file);
        String fileName = file.getOriginalFilename();

        com.amazonaws.services.s3.AmazonS3 s3Client = new AmazonS3Client(new BasicAWSCredentials(properties.get("accessKey"), properties.get("secretKey")));
        s3Client.putObject(new PutObjectRequest(properties.get("bucketName"), fileName, fil).withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getName());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;

    }
}
