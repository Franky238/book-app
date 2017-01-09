package com.library.controller;

import com.library.entity.ExtensionEntity;
import com.library.entity.FileEntity;
import com.library.entity.MimeTypeEntity;
import com.library.repository.*;
import com.library.security.JwtTokenUtil;
import com.library.security.JwtUser;
import com.library.security.entity.Authority;
import com.library.domain.request.UserRegister;
import com.library.entity.UserEntity;
import com.library.exception.PasswordNotMatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.util.List;

@RestController
public class UserController {

    @Value("${jwt.authorization.header.key}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private MimeTypeRepository mimeTypeRepository;

    @Autowired
    private ExtensionRepository extensionRepository;

    @Autowired
    private FileRepository fileRepository;

    @RequestMapping(value = "/auth/register", method = RequestMethod.POST)
    public void registerUser(@Valid @RequestBody UserRegister userDomain)
    throws PasswordNotMatchException {
        String username = userDomain.getUsername();
        String password = userDomain.getPassword();
        String repeatPass = userDomain.getRepeatPassword();
        String email = userDomain.getEmail();

        if (!password.equals(repeatPass)) {
            throw new PasswordNotMatchException("Password not match with your repeated password");
        }

        String hashedPass = this.passwordEncoder.encode(password);
        UserEntity user = new UserEntity(username, hashedPass, email);

        List<Authority> authorities = this.authorityRepository.findByDefaultAuthority(true);

        user.setDefaultAuthority(authorities);

        this.userRepository.save(user);
    }

    @RequestMapping(value = "/auth/check/email", method = RequestMethod.POST)
    public boolean checkForUniqueEmail(@RequestBody String value) {
        UserEntity user = this.userRepository.findByEmail(value);

        return user == null;
    }

    @RequestMapping(value = "/auth/check/username", method = RequestMethod.POST)
    public boolean checkForUniqueUsername(@RequestBody String value) {
        UserEntity user = this.userRepository.findByUsername(value);

        return user == null;
    }

    // TEST purposes
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public JwtUser getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        return (JwtUser) userDetailsService.loadUserByUsername(username);
    }

    @RequestMapping(value = "/file/type/{id}", method = RequestMethod.GET)
    public MimeTypeEntity getMimeType(@PathVariable Long id) {
        return this.mimeTypeRepository.findOne(id);
    }

    @RequestMapping(value = "get/file/{id}")
    public FileEntity getFile(@PathVariable Long id) {
        return this.fileRepository.findOne(id);
    }

    @RequestMapping(value = "/file/extension/{id}", method = RequestMethod.GET)
    public ExtensionEntity getExtension(@PathVariable Long id) {
        return this.extensionRepository.findOne(id);
    }

    @RequestMapping(value = "/file/detect-types/{id}", method = RequestMethod.GET)
    public List<MimeTypeEntity> getMimeTypesFromExtension(@PathVariable Long id) {
        return this.extensionRepository.findOne(id).getMimeTypes();
    }

    @RequestMapping(value = "/file/type-files/{id}", method = RequestMethod.GET)
    public List<FileEntity> getMimeTypeFiles(@PathVariable Long id) {
        return this.mimeTypeRepository.findOne(id).getFiles();
    }

}
