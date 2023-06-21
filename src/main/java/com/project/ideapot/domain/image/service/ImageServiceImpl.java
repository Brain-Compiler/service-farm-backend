package com.project.ideapot.domain.image.service;

import com.project.ideapot.domain.image.domain.Image;
import com.project.ideapot.domain.image.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    private final String path = "image" + File.separator;

    private final String absolutePath = new File("./image").getAbsolutePath() + File.separator;

    @Override
    public List<Image> saveImages(List<MultipartFile> multipartFiles, String type) throws IOException {
        List<Image> images = new ArrayList<>();

        if (multipartFiles != null) {
            File file = new File(path);

            if (!file.exists()) {
                if (!file.mkdir()) {
                    log.info("file was not successful");
                }
            }

            for (MultipartFile multipartFile : multipartFiles) {
                log.info("fileName: {}", multipartFile.getOriginalFilename());

                String fileExtension;
                String contentType = multipartFile.getContentType();

                if (ObjectUtils.isEmpty(contentType)) {
                    break;
                } else {
                    if(contentType.contains("image/jpeg")) {
                        fileExtension = ".jpg";
                    } else if(contentType.contains("image/png")) {
                        fileExtension = ".png";
                    } else {
                        break;
                    }
                }

                String fileSaveName = UUID.randomUUID() + "_" + multipartFile.getOriginalFilename() + fileExtension;
                Image image = Image.builder()
                        .name(multipartFile.getOriginalFilename())
                        .saveName(fileSaveName)
                        .path(path + fileSaveName + File.separator)
                        .type(type)
                        .build();

                images.add(image);
                imageRepository.save(image);

                file = new File(absolutePath + File.separator + fileSaveName);

                multipartFile.transferTo(file);
            }
        } else {
            log.info("MultipartFiles Not Found");
        }

        return images;
    }

}
