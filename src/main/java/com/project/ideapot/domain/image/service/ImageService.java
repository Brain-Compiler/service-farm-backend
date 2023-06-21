package com.project.ideapot.domain.image.service;

import com.project.ideapot.domain.image.domain.Image;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface ImageService {

    List<Image> saveImages(List<MultipartFile> multipartFiles, String type) throws IOException;

}
