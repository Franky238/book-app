package com.library.entity;

import com.library.entity.abstracts.AbstractEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "extension")
public class ExtensionEntity extends AbstractEntity {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    @NotEmpty
    private String name;

    @ManyToMany(mappedBy = "extensions", fetch = FetchType.LAZY)
    private List<MimeTypeEntity> mimeTypes;

    public ExtensionEntity() {} // for repository

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MimeTypeEntity> getMimeTypes() {
        return mimeTypes;
    }

    public void setMimeTypes(List<MimeTypeEntity> mimeTypes) {
        this.mimeTypes = mimeTypes;
    }
}
