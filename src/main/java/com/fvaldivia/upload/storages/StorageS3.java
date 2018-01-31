package com.fvaldivia.upload.storages;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Map;

public class StorageS3 extends Storage {


    String accessKey;
    String secretKey;
    String bucketName;

    public StorageS3(Map<String,String> properties){
        this.accessKey=properties.get("accessKey");
        this.secretKey=properties.get("secretKey");
        this.bucketName=properties.get("bucketName");

    }

    @Override
    public boolean Upload(MultipartFile file) {

        try {
        InputStream fil = convert(file);
        String fileName = file.getOriginalFilename();

        ObjectMetadata obMetadata = new ObjectMetadata();
        obMetadata.setContentType(FilenameUtils.getExtension(file.getOriginalFilename()));
        obMetadata.setContentLength(file.getSize());

        com.amazonaws.services.s3.AmazonS3 s3Client = new AmazonS3Client(new BasicAWSCredentials(getAccessKey(), getSecretKey()));
        s3Client.putObject(new PutObjectRequest(getBucketName(), fileName, fil,obMetadata));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public InputStream convert(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        ByteArrayInputStream convFile = new ByteArrayInputStream(bytes);
        return convFile;

    }
    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }
}
