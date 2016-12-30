package com.library.entity;

import com.library.entity.abstracts.AbstractEntity;

import javax.persistence.*;

@Entity
@Table(name = "allowed_mime_type")
public class AllowedMimeTypeEntity extends AbstractEntity {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mime_type_id", nullable = false)
    private MimeTypeEntity mimeType;

    public AllowedMimeTypeEntity() {} // for repository

    public Long getId() {
        return id;
    }

    public MimeTypeEntity getMimeType() {
        return mimeType;
    }

    public void setMimeType(MimeTypeEntity mimeType) {
        this.mimeType = mimeType;
    }
}
