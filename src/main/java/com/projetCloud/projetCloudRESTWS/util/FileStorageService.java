package com.projetCloud.projetCloudRESTWS.util;

import com.projetCloud.projetCloudRESTWS.model.ImageSignalement;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface FileStorageService {
    public void init();

    public ImageSignalement save(MultipartFile file);

    public Resource load(String filename);

    public void deleteAll();

    public Stream<Path> loadAll();
}