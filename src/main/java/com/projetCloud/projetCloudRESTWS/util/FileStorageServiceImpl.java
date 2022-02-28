package com.projetCloud.projetCloudRESTWS.util;

import com.projetCloud.projetCloudRESTWS.model.ImageSignalement;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final Path root = Paths.get("src/main/resources/uploads/");

    @Override
    public void init() {
        try {
            if(!Files.exists(root))
                Files.createDirectory(root);
        } catch (IOException e) {
            System.out.println(e.toString());
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public ImageSignalement save(MultipartFile file) {
        try {
            init();
            Files.copy(file.getInputStream(),this.root.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            String fileName = file.getOriginalFilename();
            String fileType = file.getContentType();
            String fileUrl = String.valueOf(this.root.resolve(fileName));
            ImageSignalement imageSignalement = new ImageSignalement();
            imageSignalement.setImageName(fileName);
            imageSignalement.setImageType(fileType);
            imageSignalement.setImageUrl(fileUrl);
            return imageSignalement;
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    @Override
    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            System.out.println(file.toString());
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    @Override
    public Stream<Path> loadAll() {
        // TODO Auto-generated method stub
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }
}