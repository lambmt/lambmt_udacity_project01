package com.udacity.jwdnd.course1.cloudstorage.services.impl;

import com.udacity.jwdnd.course1.cloudstorage.constants.CommonMsg;
import com.udacity.jwdnd.course1.cloudstorage.entity.File;
import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {

    private final UserService userService;
    private final FileMapper fileMapper;

    public FileServiceImpl(UserService userService, FileMapper fileMapper) {
        this.userService = userService;
        this.fileMapper = fileMapper;
    }

    @Override
    public String uploadFile(MultipartFile file, String username) throws IOException {

        Integer userId = userService.getUser(username).getUserId();
        if(file.isEmpty()) {
            return CommonMsg.EMPTY_ENTITY;
        } else if (file.getSize() > CommonMsg.FILE_SIZE) {
            return CommonMsg.FILE_TOO_LARGE;
        } else if (null != fileMapper.getFile(userId, file.getOriginalFilename())) {
            return CommonMsg.EXISTED_FILE;
        } else {
            File newFile = new File(
                    null,
                    file.getOriginalFilename(),
                    file.getContentType(),
                    Long.toString(file.getSize()),
                    userId,
                    file.getBytes()
            );
            if (0 > fileMapper.insertFile(newFile)) {
                return CommonMsg.ACTION_FAIL;
            } else return CommonMsg.ACTION_SUCCESS;
        }
    }

    @Override
    public File getFile(String fileName, String username) {
        return fileMapper.getFile(userService.getUser(username).getUserId(), fileName);
    }

    @Override
    public String deleteFile(Integer fileId) {
        if(0 > fileMapper.deleteFile(fileId)) {
            return CommonMsg.ACTION_FAIL;
        } else return CommonMsg.ACTION_SUCCESS;
    }
}
