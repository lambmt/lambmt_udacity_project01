package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.constants.CommonMsg;
import com.udacity.jwdnd.course1.cloudstorage.entity.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Controller
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/file")
    public String uploadFile(@RequestParam("fileUpload") MultipartFile file, Principal principal, Model model) throws IOException {
        String username = principal.getName();
        String result = fileService.uploadFile(file, username);
        if(CommonMsg.ACTION_SUCCESS.equals(result)) {
            model.addAttribute("success", result);
        } else model.addAttribute("error", result);
        return "result";
    }

    @GetMapping("/file")
    public ResponseEntity<?> downloadFile(@RequestParam String fileName, Principal principal) {
        String username = principal.getName();
        File file = fileService.getFile(fileName, username);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
                .body(file.getFileData());
    }

    @GetMapping("/file/delete")
    public String deleteFile(@RequestParam Integer fileId, Model model) {
        String result = fileService.deleteFile(fileId);
        if(CommonMsg.ACTION_SUCCESS.equals(result)) {
            model.addAttribute("success", result);
        } else model.addAttribute("error", result);
        return "result";
    }
}
