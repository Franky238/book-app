package com.library.entity;

import com.library.entity.abstracts.AbstractEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "mime_type")
public class MimeTypeEntity extends AbstractEntity {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    @NotEmpty
    private String name;

    @Column(nullable = false, unique = true)
    @NotEmpty
    private String type;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "mime_type_extension",
            joinColumns = {@JoinColumn(name = "mime_type_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "extension_id", referencedColumnName = "id")})
    private List<ExtensionEntity> extensions;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "mimeType")
    private AllowedMimeTypeEntity allowedMimeType;

    @OneToMany(mappedBy = "mimeType", fetch = FetchType.LAZY)
    private List<FileEntity> files;

    public MimeTypeEntity() {} // for repository

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ExtensionEntity> getExtensions() {
        return extensions;
    }

    public void setExtensions(List<ExtensionEntity> extensions) {
        this.extensions = extensions;
    }

    public AllowedMimeTypeEntity getAllowedMimeType() {
        return allowedMimeType;
    }

    public void setAllowedMimeType(AllowedMimeTypeEntity allowedMimeType) {
        this.allowedMimeType = allowedMimeType;
    }

    public boolean isAllowed() {
        return allowedMimeType != null;
    }

    public List<FileEntity> getFiles() {
        return files;
    }

    public void setFiles(List<FileEntity> files) {
        this.files = files;
    }
}
