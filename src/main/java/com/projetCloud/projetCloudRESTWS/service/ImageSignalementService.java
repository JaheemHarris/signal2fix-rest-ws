package com.projetCloud.projetCloudRESTWS.service;

import com.projetCloud.projetCloudRESTWS.model.ImageSignalement;
import com.projetCloud.projetCloudRESTWS.repository.ImageSignalementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@Transactional
public class ImageSignalementService {

    @Autowired
    private ImageSignalementRepo imageRepo;

    public ImageSignalement saveImage(ImageSignalement imageSignalement){
        return imageRepo.save(imageSignalement);
    }
}
