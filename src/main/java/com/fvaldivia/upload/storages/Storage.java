package com.fvaldivia.upload.storages;

import org.springframework.web.multipart.MultipartFile;

public abstract class Storage {
    public abstract boolean Upload(MultipartFile file);
}
