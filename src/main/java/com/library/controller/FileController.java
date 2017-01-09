package com.library.controller;

import com.library.entity.FileEntity;
import com.library.entity.MimeTypeEntity;
import com.library.entity.UserEntity;
import com.library.exception.MimeTypeNotAllowedException;
import com.library.repository.FileRepository;
import com.library.repository.MimeTypeRepository;
import com.library.repository.UserRepository;
import com.library.security.JwtTokenUtil;
import com.library.util.FileGeneratorUtil;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RestController
public class FileController {

    @Autowired
    private FileGeneratorUtil fileGeneratorUtil;

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.authorization.header.key}")
    private String headerKey;

    @RequestMapping(value = "/upload/file")
    public void uploadFile(@RequestParam MultipartFile uploadedFile, HttpServletRequest request)
            throws IOException, NoSuchAlgorithmException, MimeTypeNotAllowedException {
        String token = request.getHeader(this.headerKey);
        String username = this.jwtTokenUtil.getUsernameFromToken(token);

        UserEntity userEntity = this.userRepository.findByUsername(username);

        FileEntity fileEntity = this.fileGeneratorUtil.create(uploadedFile, userEntity);

        this.fileRepository.save(fileEntity);
    }

    @RequestMapping(value = "/upload/file-content")
    public void getFileContent(@RequestParam MultipartFile uploadedFile) throws IOException {

    }
}
