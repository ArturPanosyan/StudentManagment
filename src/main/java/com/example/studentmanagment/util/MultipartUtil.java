package com.example.studentmanagment.util;

import com.example.studentmanagment.entity.User;
import lombok.experimental.UtilityClass;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@UtilityClass
public class MultipartUtil {

    public static void processImageUpload(User user, MultipartFile multipartFile, String uploadDirectory) throws IOException, IOException {
        if (multipartFile != null && !multipartFile.isEmpty()) {
            String picName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            File file = new File(uploadDirectory, picName);
            multipartFile.transferTo(file);
            user.setPicName(picName);
        }
    }
}
