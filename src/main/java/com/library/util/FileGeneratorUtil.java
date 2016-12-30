package com.library.util;

import com.library.entity.FileEntity;
import com.library.entity.MimeTypeEntity;
import com.library.entity.UserEntity;
import com.library.exception.MimeTypeNotAllowedException;
import com.library.repository.MimeTypeRepository;
import com.library.repository.UserRepository;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class FileGeneratorUtil {

    private Tika tika;

    @Value("${upload.folder}")
    private String uploadFolder;

    @Autowired
    private MimeTypeRepository mimeTypeRepository;

    private FileGeneratorUtil() {
        this.tika = new Tika();
    }

    /**
     * Create folder name based on today's date
     *
     * @return String today's date
     */
    private String todayFolderName() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
        Date now = new Date();

        return sdf.format(now);
    }

    /**
     * Create a parent directory
     *
     * @param userName username in string representation
     * @return File parent dirrectory
     */
    public File makeParentDir(String userName) {
        File topFolder = new File(uploadFolder);
        File userFolder = new File(topFolder, userName);

        File fullDir = new File(userFolder, this.todayFolderName());

        if (!fullDir.exists()) {
            fullDir.mkdirs();
        }

        return fullDir;
    }

    /**
     * Generate unique string
     *
     * @return String unique string
     */
    private String generateUniqueString() {
        Long systemMilis = System.currentTimeMillis();
        String randomString = RandomStringUtils.randomAlphanumeric(32);

        return systemMilis + "_" + randomString;
    }

    /**
     * Generate unique full anme with extension
     *
     * @param file uploaded Multipart file
     * @return generated full unique name
     */
    public String generateUniqueName(MultipartFile file) {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        return this.generateUniqueString() + "." + extension;
    }

    /**
     * Detect mimeType based on input stream
     *
     * @param inputStream based on this stream will be detected mimeType in string format
     * @return String mimeType
     * @throws IOException
     */
    public String detect(InputStream inputStream) throws IOException {
        String fileType;

        try {
            fileType = this.tika.detect(inputStream);
        } catch (Exception ex) {
            throw new IOException("Can not detect file type");
        }

        return fileType;
    }

    /**
     * Hash file to MD5
     *
     * @param bytes bytes of file
     * @return String hashText
     * @throws NoSuchAlgorithmException
     */
    public String encrypt(byte[] bytes) throws NoSuchAlgorithmException {
        String hashText = null;

        try {
            MessageDigest m = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);

            m.reset();
            m.update(bytes);

            byte[] digest = m.digest();
            BigInteger bigInt = new BigInteger(1,digest);
            hashText = bigInt.toString(16);

            // Now we need to zero pad it if you actually want the full 32 chars.
            while(hashText.length() < 32 ){
                hashText = "0" + hashText;
            }

        } catch (Exception e1) {
            throw new NoSuchAlgorithmException("Bad algorithm!");
        }

        return hashText;
    }

    /**
     * Save file
     *
     * @param multipartFile Uploaded file
     * @return full path to saved file
     * @throws IOException
     */
    public FileEntity create(MultipartFile multipartFile, UserEntity userEntity) throws
            IOException, NoSuchAlgorithmException, MimeTypeNotAllowedException {
        String aliasName = this.generateUniqueName(multipartFile); //make full unique name with extension
        File parent = this.makeParentDir(userEntity.getUsername()); //make parent folder

        // check for mimeType of file
        MimeTypeEntity mimeType = this.checkFileMimeType(multipartFile);

        // write file to disk
        File fileToCreate = this.write(multipartFile.getBytes(), parent, aliasName);

        // encrypt file and get hash
        String fileHash = this.encrypt(multipartFile.getBytes());

        return new FileEntity(
                multipartFile.getOriginalFilename(),
                aliasName,
                fileToCreate.getPath(),
                multipartFile.getSize(),
                mimeType,
                fileHash,
                userEntity);
    }

    /**
     * Check for MimeType of file
     *
     * @param file uploaded multipart file
     * @return MimeTypeEntity
     * @throws MimeTypeNotAllowedException
     * @throws IOException
     */
    private MimeTypeEntity checkFileMimeType(MultipartFile file) throws
            MimeTypeNotAllowedException, IOException {
        // detect mime type
        String detectedMimeType = this.detect(file.getInputStream());

        //find mime type in DB
        MimeTypeEntity fileMimeType = this.mimeTypeRepository.findByType(detectedMimeType);

        // check if this type is allowed
        if (fileMimeType == null || !fileMimeType.isAllowed()) {
            throw new MimeTypeNotAllowedException("This file is not allowed!");
        }

        return fileMimeType;
    }

    /**
     * Write file on disk based on bytes
     *
     * @param bytes this bytes will be compiled into file
     * @param parent parent folder of file
     * @param aliasName unique name of file
     * @return File created file
     * @throws IOException
     */
    public File write(byte[] bytes, File parent, String aliasName) throws IOException {
        File file = new File(parent, aliasName); //generate new file

        // write file on disk
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));
        stream.write(bytes);
        stream.close();

        return file;
    }
}
