package com.library.entity;

import com.library.entity.abstracts.AbstractEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "file")
public class FileEntity extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name = "original_name")
    @NotEmpty
    private String originalName;

    @Column(nullable = false, name = "alias_name")
    @NotEmpty
    private String aliasName;

    @Column(nullable = false, name = "full_path")
    @NotEmpty
    private String fullPath;

    @Column(nullable = false, name = "file_size")
    private Long fileSize;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "mime_type_id", referencedColumnName = "id")
    private MimeTypeEntity mimeType;

    @Column(nullable = false, name = "file_hash")
    @NotEmpty
    private String fileHash;

    @Column(nullable = false, name = "created_at", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    public FileEntity() {} //for repository

    public FileEntity(String originalName,
                      String aliasName,
                      String fullPath,
                      Long fileSize,
                      MimeTypeEntity mimeType,
                      String fileHash,
                      UserEntity user) {
        this.originalName = originalName;
        this.fullPath = fullPath;
        this.fileSize = fileSize;
        this.mimeType = mimeType;
        this.fileHash = fileHash;
        this.user = user;
        this.aliasName = aliasName;
    }

    public Long getId() {
        return id;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public MimeTypeEntity getMimeType() {
        return mimeType;
    }

    public void setMimeType(MimeTypeEntity mimeType) {
        this.mimeType = mimeType;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public String getFileHash() {
        return fileHash;
    }

    public void setFileHash(String fileHash) {
        this.fileHash = fileHash;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
